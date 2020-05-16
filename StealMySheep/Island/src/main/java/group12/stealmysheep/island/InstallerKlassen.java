/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.island;

/**
 *
 * @author Antonio
 */
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

public class InstallerKlassen extends ModuleInstall {

    @Override
    public void restored() {

        LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();
        cfg.title = "StealMySheep";
        cfg.width = 840;
        cfg.height = 840;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(new Island(), cfg);

    }

}
