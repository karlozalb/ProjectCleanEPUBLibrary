package com.pcg.epubspec.metadata;

import java.util.LinkedList;

public class Spine {
	
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

}
