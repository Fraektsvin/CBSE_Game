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

    public class AssetDescriptor {

        public String folder;
        public Class<?> assetType;

        public AssetDescriptor(String folder, Class<?> assetType) {
            this.folder = folder;
            this.assetType = assetType;
        }
    }

    private ArrayList<AssetDescriptor> assets = new ArrayList<AssetDescriptor>();
    private FileHandleResolver resolver;

    public AssetLoader() {
        assetManager = new AssetManager();
        resolver = new ClasspathFileHandleResolver();
        assets.add(new AssetDescriptor("assets", Texture.class));
    }

    public Texture getAsset(String name) {
        return assetManager.get(name, Texture.class);
    }

    public void loadAssets() {
        for (AssetDescriptor descriptor : assets) {
            FileHandle folder = resolver.resolve(descriptor.folder);
            if (!folder.exists()) {
                continue;
            }

            for (FileHandle asset : folder.list()) {
                assetManager.load(asset.path(), descriptor.assetType);
            }
        }

        assetManager.finishLoading();
    }

}
