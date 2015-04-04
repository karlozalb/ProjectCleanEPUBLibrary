package com.pcg.epubspec;

import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pcg.epubloader.EPUBUtils;
import com.pcg.epubspec.Manifest.Item;
import com.pcg.exceptions.EPUBException;

public class Spine implements IVerificable,IEPUBMainNode{
	
	public String ID;
	public String TOC;
	public String PAGE_PROGRESSION_DIRECTION;
	
	LinkedList<ItemRef> ITEM_REF;
	
	public class ItemRef{
		public String idref;
		public String linear;
		public String id;
		public String properties;
	}

	@Override
	public void parse(Node pnode) {
		
		ID = EPUBUtils.getAttributeValue("id", pnode.getAttributes());
		TOC = EPUBUtils.getAttributeValue("toc", pnode.getAttributes());
		PAGE_PROGRESSION_DIRECTION = EPUBUtils.getAttributeValue("page-progression-direction", pnode.getAttributes());

		
		NodeList children = pnode.getChildNodes();
		int size = children.getLength();
		
		for (int i=0;i<size;i++){
			ItemRef item = new ItemRef();	
			Node node = children.item(i);
			
			HashMap<String,String> attributes = EPUBUtils.getAttributesMap(node.getAttributes());
			
			if (node.hasAttributes()){
				item.id = EPUBUtils.getAttributeValue("id", node.getAttributes());		
				item.idref = EPUBUtils.getAttributeValue("idref", node.getAttributes());		
				item.linear = EPUBUtils.getAttributeValue("linear", node.getAttributes());		
				item.properties = EPUBUtils.getAttributeValue("properties", node.getAttributes());				
			}			
		}		
	}

	@Override
	public boolean isValid() throws EPUBException {		
		
		if (!PAGE_PROGRESSION_DIRECTION.equalsIgnoreCase("ltr") && !PAGE_PROGRESSION_DIRECTION.equalsIgnoreCase("rtl") && !PAGE_PROGRESSION_DIRECTION.equalsIgnoreCase("default")) return false;
		
		for (ItemRef itemref : ITEM_REF){
			if (!itemref.linear.equalsIgnoreCase("yes") && !itemref.linear.equalsIgnoreCase("no")) return false;
		}
		
		return false;
	}

}
