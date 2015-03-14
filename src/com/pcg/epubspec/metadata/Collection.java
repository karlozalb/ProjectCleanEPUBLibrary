package com.pcg.epubspec.metadata;

import java.util.LinkedList;

public class Collection {
	
	public String ID;
	
	LinkedList<Item> ITEMS;
	
	public class Item{
		public String value;
		public String id;
		public String href;
		public String media_type;
		public String fallback;
		public String properties;
		public String media_overlay;
	}

}
