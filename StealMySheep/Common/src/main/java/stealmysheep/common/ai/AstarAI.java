/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.ai;
import java.util.ArrayList;
import stealmysheep.common.ai.Node;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.World;

/**
 *
 * @author oscar
 */
public class AstarAI {

    public int cost = 1;

    public ArrayList<Node> findAstarPath(Entity entity, Node goal, World world) {
        Position position = entity.getComponent(Position.class);
        BoxCollider collider = entity.getComponent(BoxCollider.class);

        ArrayList<Node> fringe = new ArrayList<>();
        Node node = new Node(position.getX(), position.getY());
        fringe.add(node);
        while (!fringe.isEmpty()) {
            Node lowestNode = remove(fringe, goal);
            if (collider.checkPointCollider(goal.getX(), goal.getY(), lowestNode.getX(), lowestNode.getY())) {
                return lowestNode.getPath();
            }
            // if the target of the sheep is within its collider then return node.path. 
            ArrayList<Node> children = expand(lowestNode, world, entity);
            insertAll(children, fringe);
        }
        return new ArrayList<Node>();
    }

    private void insertAll(ArrayList<Node> children, ArrayList<Node> fringe) {
        for (Node node : children) {
            fringe.add(node);
        }
    }

    private Node remove(ArrayList<Node> fringe, Node goal) {
        Node lowest = fringe.get(0);
        for (Node node : fringe) {
            if (evaulationFunction(node, goal) < evaulationFunction(lowest, goal)) {
                lowest = node;
            }
        }
        fringe.remove(lowest);
        return lowest;
    }

    private ArrayList<Node> expand(Node node, World world, Entity entity) {
        ArrayList<Node> neighbours = new ArrayList<>();
        float x = node.getX();
        float y = node.getY();
        float[][] successors = {{x - 10, y + 10}, {x, y + 10}, {x + 10, y + 10},
        {x - 10, y}, {x + 10, y}, {x - 10, y - 10}, {x, y - 10}, {x + 10, y - 10}};

        for (float[] element : successors) {
            Node neighbour = new Node(element[0], element[1]);
            if (nodeRestriction(neighbour, world, entity) != true) {
                neighbour.setParent(node);
                neighbours.add(neighbour);
            }
        }

        return neighbours;
    }

    private float evaulationFunction(Node node, Node goal) {
        return pathCost(node) + heuristic(node, goal);
    }

    private float pathCost(Node node) {
        float pathCost = node.getPath().size() * this.cost;
        return pathCost;
    }

    private float heuristic(Node node, Node goal) {
        float x1 = node.getX();
        float y1 = node.getY();
        float x2 = goal.getX();
        float y2 = goal.getY();
        float distance = (float) Math.sqrt(Math.pow((double) (x2 - x1), 2) + Math.pow((double) (y2 - y1), 2));
        return distance;
    }

    private boolean nodeRestriction(Node node, World world, Entity entity) {
        for (Entity entity2 : world.getEntities()) {
            if (entity2.equals(entity)) {
                continue;
            }

            if (entity.hasComponent(Movement.class)) {
                continue;
            }
            if (entity2.hasComponent(BoxCollider.class) && entity2.hasComponent(Position.class)) {
                Position position = entity2.getComponent(Position.class);
                BoxCollider collider = entity2.getComponent(BoxCollider.class);

                boolean hit = collider.checkPointCollider(node.getX(), node.getY(), position.getX(), position.getY());
                if (hit) {
                    return true;
                }
            }
        }
        return false;
    }
}
