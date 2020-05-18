package stealmysheep;

import java.io.File;
import java.io.IOException;
import static java.nio.file.Files.copy;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static final Path ADD_ENEMY_XML = Paths.get(new File("src/test/resources/addenemy/updates.xml").getAbsolutePath());
    private static final Path REM_ENEMY_XML = Paths.get(new File("src/test/resources/remenemy/updates.xml").getAbsolutePath());
    private static final Path UPDATES_XML = Paths.get("C:/Users/oscar/OneDrive/Skrivebord/Project/Steal My Sheep/StealMySheep/netbeans_site/updates.xml");

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

        copy(REM_ENEMY_XML, UPDATES_XML, REPLACE_EXISTING);
        waitForUpdate(plugins, waves, updates);

        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No waves", 0, waves.size());
        assertEquals("No updates", 0, updates.size());

        copy(ADD_ENEMY_XML, UPDATES_XML, REPLACE_EXISTING);
        waitForUpdate(plugins, waves, updates);

        assertEquals("One plugins", 1, plugins.size());
        assertEquals("One waves", 1, waves.size());
        assertEquals("One updates", 1, updates.size());

        copy(REM_ENEMY_XML, UPDATES_XML, REPLACE_EXISTING);
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
