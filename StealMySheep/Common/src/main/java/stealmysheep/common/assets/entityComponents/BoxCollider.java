/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets.entityComponents;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.game.GameData;

/**
 *
 * @author oscar
 */
public class BoxCollider implements Component {

    private float height, width;

    public BoxCollider(float height, float width) {
        this.height = height;
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
    }

    public boolean checkPointCollider(float nodeX, float nodeY, float positionX, float positionY) {

        float x1 = positionX - this.width / 2;
        float x2 = positionX + this.width / 2;
        float y1 = positionY - this.height / 2;
        float y2 = positionY + this.height / 2;

        if (nodeX > x1 && nodeX < x2 && nodeY > y1 && nodeY < y2) {
            return true;
        } else {
            return false;
        }
    }

}

