package com.pcg.epubspec;

import java.util.LinkedList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pcg.epubloader.EPUBUtils;
import com.pcg.epubspec.Manifest.Item;
import com.pcg.exceptions.EPUBException;

public class Bindings implements IVerificable,IEPUBMainNode {

	LinkedList<MediaType> MEDIATYPE;
	
	public Bindings(){
		MEDIATYPE = new LinkedList<MediaType>();
	}
	
	public class MediaType{
		public String media_type;
		public String handler;
	}

	@Override
	public void parse(Node pnode) {
		NodeList children = pnode.getChildNodes();
		int size = children.getLength();
		
		for (int i=0;i<size;i++){
			MediaType mediaType = new MediaType();	
			Node node = children.item(i);
			
			if (node.hasAttributes()){
				mediaType.media_type = EPUBUtils.getAttributeValue("media_type", node.getAttributes());		
				mediaType.handler = EPUBUtils.getAttributeValue("handler", node.getAttributes());						
			}			
		}		
	}

	@Override
	public boolean isValid() throws EPUBException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
