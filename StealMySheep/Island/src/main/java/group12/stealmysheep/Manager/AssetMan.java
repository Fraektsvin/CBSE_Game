/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.Manager;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author oscar
 */
public class AssetMan {

    public static final AssetManager assetManager = new AssetManager();
    public static final AssetDescriptor<Texture> characterUp = new AssetDescriptor<Texture>("assetsthief.png", Texture.class);

    public static void loadAssets() {
        assetManager.load(characterUp);
    }

}
