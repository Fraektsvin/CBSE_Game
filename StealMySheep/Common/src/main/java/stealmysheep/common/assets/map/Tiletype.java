/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets.map;

/**
 *
 * @author Antonio
 */
public enum Tiletype {
SAND(1, "thief.png"),
    
WATER(0, "sheep.png");
 
private int numbervalue;
private String image; 

    private Tiletype(int numbervalue, String image) {
        this.numbervalue = numbervalue;
        this.image = image;
    }

    public void setNumbervalue(int numbervalue) {
        this.numbervalue = numbervalue;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Tiletype getSAND() {
        return SAND;
    }

    public int getNumbervalue() {
        return numbervalue;
    }

    public String getImage() {
        return image;
    }

}
