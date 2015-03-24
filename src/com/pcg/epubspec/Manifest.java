package com.pcg.epubspec;

import java.util.LinkedList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pcg.epubloader.EPUBUtils;
import com.pcg.exceptions.EPUBException;

public class Manifest implements IVerificable,IEPUBMainNode{
	
	public String id;
	LinkedList<Item> ITEM;
	
	public Manifest(){
		ITEM = new LinkedList<Item>();
	}
	
	public class Item{
		public String id; 
		public String href; 
		public String media_type; 
		public String fallback; 
		public String properties;
		public String media_overlay;
	}
	
	@Override
	public void parse(Node pnode) {
		
		NodeList children = pnode.getChildNodes();
		int size = children.getLength();
		
		for (int i=0;i<size;i++){
			Item item = new Item();	
			Node node = children.item(i);
			
			if (node.hasAttributes()){
				item.id = EPUBUtils.getAttributeValue("id", node.getAttributes());		
				item.href = EPUBUtils.getAttributeValue("href", node.getAttributes());		
				item.media_type = EPUBUtils.getAttributeValue("media_type", node.getAttributes());		
				item.fallback = EPUBUtils.getAttributeValue("fallback", node.getAttributes());		
				item.properties = EPUBUtils.getAttributeValue("properties", node.getAttributes());		
				item.media_overlay = EPUBUtils.getAttributeValue("media_overlay", node.getAttributes());
			}			
		}		
	}

	@Override
	public boolean isValid() throws EPUBException {
		return false;
	}	

}
