/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.ai;

import java.util.ArrayList;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.ai.IAI;
import stealmysheep.common.ai.Node;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.World;

/**
 *
 * @author NidaBasaran
 *
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IAI.class),})

public class AIController implements IAI {

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
        float[][] successors = {{x - 30, y + 30}, {x, y + 30}, {x + 30, y + 30},
        {x - 30, y}, {x + 30, y}, {x - 30, y - 30}, {x, y - 30}, {x + 30, y - 30}};
        for (float[] element : successors) {
            Node neighbour = new Node(element[0], element[1]);
            if (nodeRestriction(neighbour, world) != true) {
                neighbour.setParent(node);
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

    public ArrayList<Node> greedyBestFirstSearch(Entity entity, Node goal, World world) {
        Position position = entity.getComponent(Position.class);
        BoxCollider collider = entity.getComponent(BoxCollider.class);

        ArrayList<Node> fringe = new ArrayList<>();
        Node node = new Node(position.getX(), position.getY());
        fringe.add(node);
        while (fringe != null) {
            Node lowestNode = remove(fringe, goal);
            if (checkPointCollider(goal.getX(), goal.getY(), lowestNode.getX(), lowestNode.getY(), collider)) {
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

    public boolean checkPointCollider(float nodeX, float nodeY, float positionX, float positionY, BoxCollider collider) {

        float x1 = positionX - collider.getWidth() / 2;
        float x2 = positionX + collider.getWidth() / 2;
        float y1 = positionY - collider.getHeight() / 2;
        float y2 = positionY + collider.getHeight() / 2;

        if (nodeX > x1 && nodeX < x2 && nodeY > y1 && nodeY < y2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean nodeRestriction(Node node, World world) {
        for (Entity entity : world.getEntities()) {
     
            if (entity.hasComponent(Movement.class)) {
                continue;
            }
            if (entity.hasComponent(BoxCollider.class) && entity.hasComponent(Position.class)) {
                Position position = entity.getComponent(Position.class);
                BoxCollider collider = entity.getComponent(BoxCollider.class);

                boolean hit = checkPointCollider(node.getX(), node.getY(), position.getX(), position.getY(), collider);
                if (hit) {
                    return true;
                }

            }

        }
        return false;
    }
}
