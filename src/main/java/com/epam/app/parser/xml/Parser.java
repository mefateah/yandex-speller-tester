package com.epam.app.parser.xml;

import com.epam.app.result.CheckResult;
import com.epam.app.result.SpellError;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Aleksei_Voronin on 10/28/2016.
 */
public class Parser {
    public CheckResult parse(String text, String charset) {
        CheckResult result = new CheckResult();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // also we can pass a InputStream
            Document document = builder.parse(new ByteArrayInputStream(text.getBytes(charset)));

            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element el = (Element) nodeList.item(i);
                SpellError e = new SpellError();
                e.setErrorCode(Integer.parseInt(el.getAttribute("code")));
                e.setRow(Integer.parseInt(el.getAttribute("row")));
                e.setColumn(Integer.parseInt(el.getAttribute("col")));
                e.setWordLength(Integer.parseInt(el.getAttribute("len")));

                NodeList childNodes = nodeList.item(i).getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node n = childNodes.item(j);
                    Element innerEl = (Element)n;

                    switch (n.getNodeName()) {
                        case "word":
                            e.setWord(innerEl.getTextContent());
                            break;
                        case "s":
                            e.getS().add(innerEl.getTextContent());
                            break;
                        default:
                            break;
                    }
                }
                //result.getSpellErrors().add(e);
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
