/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.sheep;

import java.util.ArrayList;

/**
 *
 * @author NidaBasaran
 */
public class Node {

    float x;
    float y;
    Node parent;

    public Node(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getPath() {
        ArrayList<Node> path = new ArrayList<Node>();
        Node currentNode = this;
        path.add(currentNode);
        while (currentNode.getParent() != null) {
            currentNode = currentNode.parent;
            path.add(currentNode);
        }
        if (path.get(path.size() - 1) != null) {
            path.remove(path.size() - 1);
        }

        return path;

    }

}
