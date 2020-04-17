/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.sheep;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Sheep;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import static stealmysheep.common.game.Input.UP;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;

/**
 *
 * @author NidaBasaran
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})
public class SheepControlSystem implements IUpdate {

    private Random random = new Random();

    @Override
    public void update(GameData gameData, World world) {
        for (Entity sheep : world.getEntities(Sheep.class)) {
            Position position = sheep.getComponent(Position.class);
            Movement movement = sheep.getComponent(Movement.class);
            Sheep currentSheep = (Sheep) sheep;
            if (currentSheep.isMoving() == false) {
                if (random.nextInt(100) + 1 <= 2) {
                    currentSheep.setMoving(true);
                    float a = (float) (Math.random() * 2 * Math.PI);
                    float r = (float) (currentSheep.getRadius() * sqrt(Math.random()));
                    float x = (float) (r * cos(a));
                    float y = (float) (r * sin(a));
                    currentSheep.setX(x);
                    currentSheep.setY(y);

                }
            } else if (currentSheep.isMoving() == true) {
                Node goal = new Node(currentSheep.getX(), currentSheep.getY());
                ArrayList<Node> path = greedyBestFirstSearch(sheep, goal, world);
                float x = path.get(path.size() - 1).getX();
                float y = path.get(path.size() - 1).getY();
                position.setRadians((float) Math.atan2(x, y));
                position.setX(position.getX() + (float) cos(position.getRadians()) * movement.getSpeed() * gameData.getDeltaTime());
                position.setY(position.getY() + (float) sin(position.getRadians()) * movement.getSpeed() * gameData.getDeltaTime());
                if (destinationCheck(sheep, path.get(0))) {
                    currentSheep.setMoving(false);
                }

            }
        }
    }

    private void insertAll(ArrayList<Node> children, ArrayList<Node> fringe) {
        for (Node node : children) {
            fringe.add(node);
        }
    }

    private Node remove(ArrayList<Node> fringe, Node goal) {
        Node lowest = fringe.get(0);
        for (Node node : fringe) {
            if (heuristic(node, goal) < heuristic(lowest, goal)) {
                lowest = node;
            }
        }
        fringe.remove(lowest);
        return lowest;
    }

    private ArrayList expand(Node node, World world) {
        ArrayList<Node> neighbours = new ArrayList<>();
        float x = node.getX();
        float y = node.getY();
        float[][] successors = {{x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1},
        {x - 1, y}, {x + 1, y}, {x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}};
        for (float[] element : successors) {
            Node neighbour = new Node(element[0], element[1]);
            if (nodeRestriction(neighbour, world) != true) {
                neighbour.setParent(node);
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

    private ArrayList<Node> greedyBestFirstSearch(Entity entity, Node goal, World world) {
        ArrayList<Node> fringe = new ArrayList<>();
        Position position = entity.getComponent(Position.class);
        Node node = new Node(position.getX(), position.getY());
        fringe.add(node);
        while (fringe != null) {
            Node lowestNode = remove(fringe, goal);
            if (greedyBestFirstSearchCheck(entity, goal, lowestNode)) {
                return lowestNode.getPath();
            }
            // if the target of the sheep is within its collider then return node.path. 
            ArrayList<Node> children = expand(lowestNode, world);
            insertAll(children, fringe);
        }
        return null;
    }

    private float heuristic(Node node, Node goal) {
        float x1 = node.getX();
        float y1 = node.getY();
        float x2 = goal.getX();
        float y2 = goal.getY();
        float vec = (float) Math.sqrt(Math.pow((double) (x2 - x1), 2) + Math.pow((double) (y2 - y1), 2));
        return vec;
    }

    private boolean destinationCheck(Entity entity, Node goal) {
        BoxCollider collider = entity.getComponent(BoxCollider.class);
        Position position = entity.getComponent(Position.class);
        float x = goal.getX();
        float y = goal.getY();

        float x1 = position.getX() - collider.getWidth() / 2;
        float x2 = position.getX() + collider.getWidth() / 2;
        float y1 = position.getY() - collider.getHeight() / 2;
        float y2 = position.getY() + collider.getHeight() / 2;

        if (x > x1 && x < x2 && y > y1 && y < y2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean greedyBestFirstSearchCheck(Entity entity, Node goal, Node node) {
        BoxCollider collider = entity.getComponent(BoxCollider.class);

        float x = goal.getX();
        float y = goal.getY();

        float x1 = node.getX() - collider.getWidth() / 2;
        float x2 = node.getX() + collider.getWidth() / 2;
        float y1 = node.getY() - collider.getHeight() / 2;
        float y2 = node.getY() + collider.getHeight() / 2;

        if (x > x1 && x < x2 && y > y1 && y < y2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean nodeRestriction(Node node, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity.hasComponent(BoxCollider.class) && entity.hasComponent(Position.class)) {
                Position position = entity.getComponent(Position.class);
                BoxCollider collider = entity.getComponent(BoxCollider.class);
                float x1 = position.getX() - collider.getWidth() / 2;
                float x2 = position.getX() + collider.getWidth() / 2;
                float y1 = position.getY() - collider.getHeight() / 2;
                float y2 = position.getY() + collider.getHeight() / 2;
                float x = node.getX();
                float y = node.getY();
                if (x > x1 && x < x2 && y > y1 && y < y2) {
                    return true;
                }
            }

        }
        return false;
    }
}
