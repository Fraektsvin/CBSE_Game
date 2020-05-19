package org.netbeans.modules.autoupdate.silentupdate;

/**
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import static java.nio.file.Files.copy;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.netbeans.api.autoupdate.UpdateUnitProvider;
import org.netbeans.api.autoupdate.UpdateUnitProviderFactory;
import static org.netbeans.modules.autoupdate.silentupdate.UpdateHandler.SILENT_UC_CODE_NAME;
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
        try {
            setUpUpdateFIle();
        } catch (URISyntaxException ex) {
            Exceptions.printStackTrace(ex);
        }
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

    private void setUpUpdateFIle() throws URISyntaxException {
        String SILENT_UC_CODE_NAME = "org_netbeans_modules_autoupdate_silentupdate_update_center";
        String ogFile = "";
        String completeFile = "";
        List<UpdateUnitProvider> providers = UpdateUnitProviderFactory.getDefault().getUpdateUnitProviders(true);
        for (UpdateUnitProvider p : providers) {
            if (SILENT_UC_CODE_NAME.equals(p.getName())) {
                ogFile = Paths.get(p.getProviderURL().toURI()).toFile().getAbsolutePath();
                completeFile = ogFile.replace("updates.xml", "completeUpdates.xml");
            }
        }

        Path goodFile = Paths.get(completeFile);
        System.out.println(ogFile);
        System.out.println(goodFile.toString());
        Path oldFile = Paths.get(ogFile);
        try {
            copy(goodFile, oldFile, REPLACE_EXISTING);
            System.out.println("Sucess");
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
