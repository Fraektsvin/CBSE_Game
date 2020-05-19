package stealmysheep;

import group12.stealmysheep.GameStates.GameModule;
import group12.stealmysheep.Manager.ModuleManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.nio.file.Files.copy;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import stealmysheep.common.services.IPlugin;
import stealmysheep.common.services.IUpdate;
import stealmysheep.common.services.IWave;

public class ApplicationTest extends NbTestCase {

    private static final Path ADD_ENEMY_XML = Paths.get(new File("src/test/resources/addenemy/updates.xml").getAbsolutePath());
    private static final Path REM_ENEMY_XML = Paths.get(new File("src/test/resources/remenemy/updates.xml").getAbsolutePath());
    private static final String UPDATES_XML = "C:/Users/oscar/OneDrive/Skrivebord/Project/Steal My Sheep/StealMySheep/netbeans_site/updates.xml";
    private GameModule module;
    private Document doc;

    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.OFF). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false).
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() throws InterruptedException, IOException {
        setupModule();
        System.out.println(this.module.getName());

        List<IPlugin> plugins = new CopyOnWriteArrayList<>();
        List<IWave> waves = new CopyOnWriteArrayList<>();
        List<IUpdate> updates = new CopyOnWriteArrayList<>();

        waitForUpdate(plugins, waves, updates);
        System.out.println("Testing with all modules");

        assertEquals("No plugins", 4, plugins.size());
        assertEquals("No updates", 5, updates.size());

        System.out.println("Removing one module");
        removeModule();
        waitForUpdate(plugins, waves, updates);

        assertEquals("One plugins", 3, plugins.size());
        assertEquals("One updates", 4, updates.size());

        System.out.println("Testing with all modules again");
        addModule();
        waitForUpdate(plugins, waves, updates);

        assertEquals("No plugins", 4, plugins.size());
        assertEquals("No updates", 5, updates.size());

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

    private void setupModule() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            this.doc = builder.parse(UPDATES_XML);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//module[@codenamebase=\"stealmysheep.Sheep\"]");
            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            System.out.println(nl.getLength());
            Element e = (Element) nl.item(0);
            String name = e.getAttribute("codenamebase");
            this.module = new GameModule(name, true, nl.item(0), nl.item(0).getParentNode());

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void removeModule() {
        Node node = this.module.getNode().cloneNode(true);
        this.module.getParent().removeChild(this.module.getNode());
        this.module.setNode(node);
        refreshFile();
    }

    private void addModule() {
        this.module.setActive(true);

        Node node = this.module.getNode();
        this.module.getParent().appendChild(node);
        refreshFile();
    }

    private void refreshFile() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new FileOutputStream(UPDATES_XML));
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            Exceptions.printStackTrace(e);
        } catch (TransformerException | FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
