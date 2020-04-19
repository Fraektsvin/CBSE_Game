/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.AI;

import java.util.ArrayList;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.game.World;

/**
 *
 * @author NidaBasaran
 */
public interface IAI {
    
    public ArrayList<Node> greedyBestFirstSearch(Entity entity, Node goal, World world);
    
    public boolean checkPointCollider(float nodeX, float nodeY, float positionX, float positionY, BoxCollider collider);
    
}
