package com.pcg.epubspec.metadata;

import java.util.LinkedList;

public class Metadata {

	LinkedList<DCIdentifier> DC_IDENTIFIER;
	LinkedList<DCTitle> DC_TITLE;
	LinkedList<DCTitle> DC_LANGUAGE;
	LinkedList<Meta> META;
	LinkedList<OptionalElement> DCMES_OPTIONAL;
	LinkedList<Link> LINK;
	
	/**********************************************************************************
	 * Class that describes a required element.
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class RequiredElement{
		
		public String value;
		public String id;
		
		public boolean check(){
			return false;
		}		
	}
	
	/***********************************************************************************
	 * Class that describes an optional element.
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class OptionalElement extends RequiredElement{}
	
	/***********************************************************************************
	 * Class that describes an "extended" optional element.
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class OptionalElementLangAndDir extends OptionalElement{
		public String xml_lang;
		public String dir;
		
		public boolean check(){
			if (dir.length() > 0 && (dir.equalsIgnoreCase("rtl") || dir.equalsIgnoreCase("ltr"))){	
				System.out.println("The property dir of title element must be rtl or ltr.");
				return false;
			}else{
				return super.check();
			}
		}	
	}
	
	
	/***********************************************************************************
	 * dc:identifier class.
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class DCIdentifier extends RequiredElement{}
	
	/***********************************************************************************
	 * dc:title class.
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class DCTitle extends RequiredElement{
		public String xml_lang;
		public String dir;

		public boolean check(){
			if (dir.length() > 0 && (dir.equalsIgnoreCase("rtl") || dir.equalsIgnoreCase("ltr"))){	
				System.out.println("The property dir of title element must be rtl or ltr.");
				return false;
			}else{
				return super.check();
			}
		}	
	}
	
	/***********************************************************************************
	 * Meta class
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class Meta extends OptionalElementLangAndDir{		
		public String property;
		public String refines;
		public String scheme;
	}
	
	/***********************************************************************************
	 * cd:language class
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class DCLanguaje extends RequiredElement{
		
		public boolean check(){
			return true;
		}	
	}
	
	/**********************************************************************************
	 * Optional DCMES elements 
	 **********************************************************************************/
	
	public class DCContributor extends OptionalElementLangAndDir{}
	
	public class DCCoverage extends OptionalElementLangAndDir{}
	
	public class DCCreator extends OptionalElementLangAndDir{}
	
	public class DCDate extends OptionalElement{}
	
	public class DCDescription extends OptionalElementLangAndDir{}
	
	public class DCFormat extends OptionalElement{}
	
	public class DCPublisher extends OptionalElementLangAndDir{}
	
	public class DCRelation extends OptionalElementLangAndDir{}
	
	public class DCRights extends OptionalElementLangAndDir{}
	
	public class DCSource extends OptionalElement{}
	
	public class DCSubject extends OptionalElementLangAndDir{}
	
	public class DCType extends OptionalElement{}	
	
	/***********************************************************************************
	 * Link class
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class Link extends RequiredElement{
		String href;
		String rel;
		String refines;
		String media_type;
	}
	

	
}
