package stealmysheep;

import java.io.IOException;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Lookup;
import stealmysheep.common.services.IPlugin;
import stealmysheep.common.services.IUpdate;
import stealmysheep.common.services.IWave;

public class ApplicationTest extends NbTestCase {

    private static final String ADD_ENEMY_XML = "";
    private static final String REM_ENEMY_XML = "";
    private static final String UPDATES_XML = "";

    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false).
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() throws InterruptedException, IOException {
        List<IPlugin> plugins = new CopyOnWriteArrayList<>();
        List<IWave> waves = new CopyOnWriteArrayList<>();
        List<IUpdate> updates = new CopyOnWriteArrayList<>();
        waitForUpdate(plugins, waves, updates);

        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No waves", 0, waves.size());
        assertEquals("No updates", 0, updates.size());

        copy(get(ADD_ENEMY_XML), get(UPDATES_XML), REPLACE_EXISTING);
        waitForUpdate(plugins, waves, updates);

        assertEquals("One plugins", 1, plugins.size());
        assertEquals("One waves", 1, waves.size());
        assertEquals("One updates", 1, updates.size());

        copy(get(REM_ENEMY_XML), get(UPDATES_XML), REPLACE_EXISTING);
        waitForUpdate(plugins, waves, updates);

        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No waves", 0, waves.size());
        assertEquals("No updates", 0, updates.size());

    }

    public void waitForUpdate(List<IPlugin> iplugins, List<IWave> iwaves, List<IUpdate> iupdates) throws InterruptedException, IOException {
        Thread.sleep(10000);
        iplugins.clear();
        iplugins.addAll(Lookup.getDefault().lookupAll(IPlugin.class));

        iwaves.clear();
        iwaves.addAll(Lookup.getDefault().lookupAll(IWave.class));

        iupdates.clear();
        iupdates.addAll(Lookup.getDefault().lookupAll(IUpdate.class));
    }

}
