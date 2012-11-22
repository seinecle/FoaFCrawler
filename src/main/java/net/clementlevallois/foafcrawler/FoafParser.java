/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.foafcrawler;

import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author C. Levallois
 */
public class FoafParser extends DefaultHandler {

    private boolean newAbout;
    private boolean newRel;
    private String currSourceURI;
    private String currSourceName;
    private String currTargetURI;
    private StringBuilder currTargetNameBuilder;
    private StringBuilder currSourceNameBuilder;
    private String currProperty;
    private String currResource;
    private InputSource is;

    public FoafParser(InputSource newIs) {
        this.is = newIs;
    }

    public void parse() throws IOException {
        //get a factory
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {

            //get a new instance of parser
            SAXParser sp = spf.newSAXParser();

            //parse the file and also register this class for call backs
            sp.parse(is, this);
            Controller.setNodes.add(new NodeFoaf(currSourceURI, currSourceName));
            Controller.setNodes.add(new NodeFoaf(currTargetURI, currTargetNameBuilder.toString()));
            Controller.setEdges.add(new Edge(currSourceURI, currTargetURI));


        } catch (SAXException se) {
            System.out.println("SAXException: " + se);
        } catch (ParserConfigurationException pce) {
            System.out.println("ParserConfigurationException: " + pce);
        } catch (IOException ie) {
            System.out.println("IOException: " + ie);
        }

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (attributes.getIndex("about") != -1) {
            newAbout = true;
            currSourceURI = attributes.getValue("about");
            System.out.println("currSourceURI is:" + currSourceURI);
            currSourceNameBuilder = new StringBuilder();
        }
//        if (attributes.getIndex("property") != -1) {
//            currProperty = attributes.getValue("foaf:name");
//        }
        if (attributes.getIndex("rel") != -1) {
            newRel = true;
//            currProperty = attributes.getValue("rel");
            currTargetNameBuilder = new StringBuilder();
        }
        if (attributes.getIndex("content") != -1) {
            currSourceName = attributes.getValue("content");
            System.out.println("currSourceName: " + currSourceName);
        }
        if (attributes.getIndex("resource") != -1) {
            currTargetURI = attributes.getValue("resource");
            System.out.println("currResource: " + currTargetURI);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (newAbout) {
            currSourceNameBuilder.append(ch, start, length);
        }
        if (newRel) {
            currTargetNameBuilder.append(ch, start, length);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (newAbout) {
            currSourceName = currSourceNameBuilder.toString();
            System.out.println("currSourceName" + currSourceName);
            newAbout = false;
        }
        if (newRel) {
            System.out.println("currTargetBuilder: " + currTargetNameBuilder.toString());
            newRel = false;
        }
    }
}
