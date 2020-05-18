package org.netbeans.modules.autoupdate.silentupdate;

/**
 *
 */
import java.io.File;
import java.io.IOException;
import static java.nio.file.Files.copy;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class UpdateActivator extends ModuleInstall {

    private final ScheduledExecutorService exector = Executors.newScheduledThreadPool(1);

    @Override
    public void restored() {
        setUpUpdateFIle();
        exector.scheduleAtFixedRate(doCheck, 5000, 5000, TimeUnit.MILLISECONDS);
    }

    private static final Runnable doCheck = new Runnable() {
        @Override
        public void run() {
            if (UpdateHandler.timeToCheck()) {
                UpdateHandler.checkAndHandleUpdates();
            }
        }

    };

    @Override
    public void uninstalled() {
        super.uninstalled(); //To change body of generated methods, choose Tools | Templates.
    }

    private void setUpUpdateFIle() {
        Path goodFile = Paths.get("C:/Users/oscar/OneDrive/Skrivebord/Project/Steal My Sheep/StealMySheep/netbeans_site/completeUpdates.xml");
        System.out.println(goodFile.toString());
        Path oldFile = Paths.get("C:/Users/oscar/OneDrive/Skrivebord/Project/Steal My Sheep/StealMySheep/netbeans_site/updates.xml");
        try {
            copy(goodFile, oldFile, REPLACE_EXISTING);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
