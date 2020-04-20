/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.Manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class AssetLoader {

    public final AssetManager assetManager;

    public AssetLoader() {
        assetManager = new AssetManager();
        loadAssets();
        assetManager.finishLoading();
    }

    public Texture getAsset(String name) {
        return assetManager.get(name, Texture.class);
    }

    private void loadAssets() {
        assetManager.load("assets/player.png", Texture.class);
        assetManager.load("assets/thief.png", Texture.class);
        assetManager.load("assets/sheep.png", Texture.class);
        assetManager.load("assets/waterBottom.png", Texture.class);
        assetManager.load("assets/waterTop.png", Texture.class);
        assetManager.load("assets/waterRight.png", Texture.class);
        assetManager.load("assets/waterLeft.png", Texture.class);
        assetManager.load("assets/waterTopLeft.png", Texture.class);
        assetManager.load("assets/waterTopRight.png", Texture.class);
        assetManager.load("assets/waterBottomRight.png", Texture.class);
        assetManager.load("assets/waterBottomLeft.png", Texture.class);
        assetManager.load("assets/sand.png", Texture.class);
        assetManager.load("assets/projectile.png", Texture.class);
        assetManager.load("assets/archer.png", Texture.class);
        assetManager.load("assets/rock.png", Texture.class);
    }

}
