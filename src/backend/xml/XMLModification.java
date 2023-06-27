package backend.xml;

import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLModification {
    private String name;
    private String extension;
    private String path;
    public XMLModification(String name, String extension, String path) {
        this.extension = extension;
        this.name = name;
        this.path = path;
    }
    public  void construirArchivos() throws IOException{
        String fileName = this.path;
        String modifiedFileName = this.name + ".xml";

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(fileName);

            // Modificar la etiqueta <featureLabel> en la línea 106
            NodeList lineList = doc.getElementsByTagName("line");
            if (lineList.getLength() > 0) {
                Node lineNode = lineList.item(0);
                if (lineNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element lineElement = (Element) lineNode;
                    NodeList featureLabelList = lineElement.getElementsByTagName("featureLabel");
                    if (featureLabelList.getLength() > 0) {
                        Node featureLabelNode = featureLabelList.item(0);
                        if (featureLabelNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element featureLabelElement = (Element) featureLabelNode;
                            featureLabelElement.setTextContent("Prueba");
                        }
                    }
                }
            }

            // Escribir el archivo XML modificado
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(modifiedFileName));
            transformer.transform(source, result);

            System.out.println("Archivo XML modificado creado: " + modifiedFileName);
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
