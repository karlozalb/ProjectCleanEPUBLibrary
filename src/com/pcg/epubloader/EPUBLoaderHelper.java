package com.pcg.epubloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.pcg.epubspec.Package;

public class EPUBLoaderHelper {

	private ZipFile mZipHelper;
    private DocumentBuilderFactory mDocumentFactory = DocumentBuilderFactory.newInstance();
 
    private Package ePub;
     
    public EPUBLoaderHelper(String pzippath){
      try {
         ePub = new Package();
 
         //Zip opening
         mZipHelper = new ZipFile(pzippath);
         Enumeration<? extends ZipEntry> entries = mZipHelper.entries();
 
         //We need to find container.xml to point to our OPF file.       
         while(entries.hasMoreElements()){          
            ZipEntry entry = entries.nextElement();
            if (entry.getName().equalsIgnoreCase("META-INF/container.xml")){
                //We've found our container file.
                parseContainerFile(mZipHelper.getInputStream(entry));
                return;
            }
         }
          
         //If we're here, we haven't found container.xml, so the epub is invalid.        
      } catch (IOException e) {
          e.printStackTrace();
      }
    }   
     
    /**
     * This method looks for the OPF file, parsing container.xml.
     * @param pcontainerstream
     * @return 
     */
    void parseContainerFile(InputStream pcontainerstream){
        DocumentBuilder builder;
        try {
            builder = mDocumentFactory.newDocumentBuilder();
            //Load and Parse the XML document
            //document contains the complete XML as a Tree.
             
            Document document = builder.parse(pcontainerstream);        
             
            //Root node.
            NodeList nodeList = document.getDocumentElement().getChildNodes(); //<container> node children.
             
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);               
                if (node.getNodeName().equalsIgnoreCase("rootfiles")){
                    //root files list, children of "rootfiles" node.
                    NodeList rootFileList = node.getChildNodes(); //<rootfiles> node children.
                     
                    for (int j = 0; j < rootFileList.getLength(); j++) {
                        Node rootFile = rootFileList.item(i);               
                        if (rootFile.hasAttributes() && rootFile.getAttributes().getNamedItem("media-type").getNodeValue().equalsIgnoreCase("application/oebps-package+xml")){
                            //We return the opf file path.
                            parseOPFFile(rootFile.getAttributes().getNamedItem("full-path").getNodeValue());
                            return; //Exit now from this.
                        } 
                    }
                }
            }           
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
             
        //The opf file was not found
        //ERROR!!!!!!!!!!!!!!!!!!!!!!!!!
    }
     
    void parseOPFFile(String popfpath){
        DocumentBuilder builder;        
        try {
            builder = mDocumentFactory.newDocumentBuilder();
             
            Document document = builder.parse(mZipHelper.getInputStream(getZipEntry(popfpath)));
             
            //<package node>
            Node packageNode = document.getDocumentElement();
                         
            parsePackage(packageNode);
             
            NodeList nodeList = document.getDocumentElement().getChildNodes(); //<package> node children.
             
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);               
                if (node.getNodeName().equalsIgnoreCase("metadata")){ //metadata node found.
                    processMetadata(node);
                }
            }           
             
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    private void parsePackage(Node packageNode) {       
        //ePub.getPackage().parsePackage(packageNode);        
    }
 
    private void processGuide(NodeList pnodeList) {
         
    }
 
    private void processSpine(NodeList pnodeList) {
         
    }
 
    private void processManifest(NodeList pnodeList) {
         
    }
 
    private void processMetadata(Node pmetadatanode) {      
        this.ePub.getMetadata().parse(pmetadatanode);
    }
 
    ZipEntry getZipEntry(String popfpath){
        Enumeration<? extends ZipEntry> entries = mZipHelper.entries();
 
         //We need to find container.xml to point to our OPF file.       
         while(entries.hasMoreElements()){          
            ZipEntry entry = entries.nextElement();
            if (entry.getName().equalsIgnoreCase(popfpath)){                
                return entry;
            }
         }
          
         return null; //File not found.
    }
	
}