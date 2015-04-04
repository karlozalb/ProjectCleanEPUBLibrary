package com.pcg.epubspec;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.pcg.epubloader.EPUBUtils;
import com.pcg.exceptions.EPUBException;


public class Package implements IVerificable,IEPUBMainNode{

	public static String VERSION = "version",UNIQUE_IDENTIFIER = "unique-indentifier",PREFIX = "prefix",XML_LANG = "xml:lang",DIR = "dir",ID = "id";
	
	String version,unique_identifier,prefix,xml_lang,dir,id;
	
	Metadata METADATA;
	Manifest MANIFEST;
	Spine SPINE;
	Guide GUIDE;
	Bindings BINDINGS;
	Collection COLLECTION;
	
	public Package(){
		METADATA = new Metadata();
	}
	
	public Metadata getMetadata(){
		return METADATA;
	}
	
	public Manifest getManifest(){
		return MANIFEST;
	}

	@Override
	public void parse(Node pnode) {		
		NamedNodeMap atts = pnode.getAttributes();
		
		version = EPUBUtils.getAttributeValue(VERSION, atts);
		unique_identifier = EPUBUtils.getAttributeValue(UNIQUE_IDENTIFIER, atts);
		
		//We need this identifier to correctly validate the metadata node later.
		METADATA.setPackageUniqueId(unique_identifier);		
		
		prefix = EPUBUtils.getAttributeValue(PREFIX, atts);
		xml_lang = EPUBUtils.getAttributeValue(XML_LANG, atts);
		dir = EPUBUtils.getAttributeValue(DIR, atts);
		id = EPUBUtils.getAttributeValue(ID, atts);
	}

	@Override
	public boolean isValid() throws EPUBException {
		return false;
	}
	
}
