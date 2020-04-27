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
    SAND(0, "sand.png", false),
    WATERLEFT(1, "waterLeft.png", false),
    WATERRIGHT(2, "waterRight.png", false),
    WATERTOP(3, "waterTop.png", false),
    WATERBOTTOM(4, "waterBottom.png", false),
    WATERBOTTOMRIGHT(5, "waterBottomRight.png", false),
    WATERBOTTOMLEFT(6, "waterBottomLeft.png", false),
    WATERTOPLEFT(7, "waterTopLeft.png", false),
    WATERTOPRIGHT(8, "waterTopRight.png", false),
    ROCK(9,"tileRock.png",true),
    TILEBUSH(10 ,"tileBush.png", true);

    private int numbervalue;
    private String image;
    private boolean hascollider; 

    public void setHascollider(boolean hascollider) {
        this.hascollider = hascollider;
    }

    public boolean isHascollider() {
        return hascollider;
    }

    private Tiletype(int numbervalue, String image, Boolean hascollider) {
        this.numbervalue = numbervalue;
        this.image = image;
        this.hascollider = hascollider;
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
