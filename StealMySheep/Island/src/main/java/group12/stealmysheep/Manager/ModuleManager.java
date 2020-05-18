/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.Manager;

import group12.stealmysheep.GameStates.GameModule;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.openide.util.Exceptions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author oscar
 */
public class ModuleManager {

    private static ModuleManager singleton_instance = null;

    private static String updateXML = "C:\\Users\\oscar\\OneDrive\\Skrivebord\\Project\\Steal My Sheep\\StealMySheep\\netbeans_site\\updates.xml";
    private String moduleInclude = "stealmysheep";
    private static NodeList nodeList;
    private static ArrayList<GameModule> gameModules;
    private static Document doc;

    private ModuleManager() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(this.updateXML);
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//module[contains(@codenamebase, '" + this.moduleInclude + "')]");
        ModuleManager.nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        ModuleManager.gameModules = new ArrayList<>();

        for (int i = 0; i < ModuleManager.nodeList.getLength(); i++) {
            if (!isNodeInvalid(ModuleManager.nodeList.item(i))) {

                Element e = (Element) ModuleManager.nodeList.item(i);
                String name = e.getAttribute("codenamebase").replace("stealmysheep.", "");
                Node node = ModuleManager.nodeList.item(i);
                GameModule gameModule = new GameModule(name, true, node, node.getParentNode());
                ModuleManager.gameModules.add(gameModule);
            }
        }
    }

    public static ArrayList<GameModule> getModules() {
        return ModuleManager.gameModules;
    }

    public static ModuleManager getInstance() {
        if (singleton_instance == null) {
            try {
                singleton_instance = new ModuleManager();
            } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        return singleton_instance;
    }

    public static void unloadModule(GameModule gameModule) {
        System.out.println("Unloading module : " + gameModule.getName());
        gameModule.setActive(false);

        Node node = gameModule.getNode().cloneNode(true);
        gameModule.getParent().removeChild(gameModule.getNode());
        gameModule.setNode(node);
        saveXMLFile();

    }

    public static void loadModule(GameModule gameModule) {
        System.out.println("Loading module : " + gameModule.getName());
        gameModule.setActive(true);

        Node node = gameModule.getNode();
        gameModule.getParent().appendChild(node);
        saveXMLFile();
    }

    private static void saveXMLFile() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new FileOutputStream(updateXML));
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            Exceptions.printStackTrace(e);
        } catch (TransformerException | FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static boolean isNodeInvalid(Node node) {
        String[] excludedModules = {"Common", "SilentUpdate", "branding", "Island"};
        Element e = (Element) node;
        String name = e.getAttribute("codenamebase");

        for (String string : excludedModules) {
            if (name.contains(string)) {
                return true;
            }
        }
        return false;
    }
}
