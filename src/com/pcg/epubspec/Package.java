package com.pcg.epubspec;

import com.pcg.epubspec.metadata.Bindings;
import com.pcg.epubspec.metadata.Collection;
import com.pcg.epubspec.metadata.Guide;
import com.pcg.epubspec.metadata.Manifest;
import com.pcg.epubspec.metadata.Metadata;
import com.pcg.epubspec.metadata.Spine;

public class Package {

	String version,unique_indentifier,prefix,xml_lang,dir,id;
	
	Metadata METADATA;
	Manifest MANIFEST;
	Spine SPINE;
	Guide GUIDE;
	Bindings BINDINGS;
	Collection COLLECTION;
	
}
