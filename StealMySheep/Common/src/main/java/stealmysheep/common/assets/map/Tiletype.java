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
    SAND(0, "sand.png"),
    WATERLEFT(1, "waterLeft.png"),
    WATERRIGHT(2, "waterRight.png"),
    WATERTOP(3, "waterTop.png"),
    WATERBOTTOM(4, "waterBottom.png"),
    WATERBOTTOMRIGHT(5, "waterBottomRight.png"),
    WATERBOTTOMLEFT(6, "waterBottomLeft.png"),
    WATERTOPLEFT(7, "waterTopLeft.png"),
    WATERTOPRIGHT(8, "waterTopRight.png");

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
