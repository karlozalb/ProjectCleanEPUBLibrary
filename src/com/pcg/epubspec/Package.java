package com.pcg.epubspec;


public class Package {

	String version,unique_indentifier,prefix,xml_lang,dir,id;
	
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
	
}
