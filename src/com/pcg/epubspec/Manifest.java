package com.pcg.epubspec;

import java.util.HashMap;
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
				
				HashMap<String,String> attributes = EPUBUtils.getAttributesMap(node.getAttributes());
				
				item.id = attributes.get("id");		
				item.href = attributes.get("href");	
				item.media_type = attributes.get("media_type");	
				item.fallback = attributes.get("fallback");	
				item.properties = attributes.get("properties");	
				item.media_overlay = attributes.get("media_overlay");
			}			
		}		
	}

	@Override
	public boolean isValid() throws EPUBException {
		return true;
	}	

}
