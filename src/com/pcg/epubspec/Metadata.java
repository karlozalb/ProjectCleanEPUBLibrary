package com.pcg.epubspec;

import java.util.LinkedList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pcg.epubloader.EPUBUtils;
import com.pcg.exceptions.EPUBException;
import com.pcg.exceptions.InvalidMetadataException;

public class Metadata implements IVerificable,IEPUBMainNode{

	LinkedList<DCIdentifier> DC_IDENTIFIER;
	LinkedList<DCTitle> DC_TITLE;
	LinkedList<DCLanguage> DC_LANGUAGE;
	LinkedList<Meta> META;
	LinkedList<OptionalElement> DCMES_OPTIONAL;
	LinkedList<OPF2Meta> OPF2_META;
	LinkedList<Link> LINK;
	
	public Metadata(){
		DC_IDENTIFIER = new LinkedList<DCIdentifier>();
		DC_TITLE = new LinkedList<DCTitle>();
		DC_LANGUAGE = new LinkedList<DCLanguage>();
		META = new LinkedList<Meta>();
		DCMES_OPTIONAL = new LinkedList<OptionalElement>();
		OPF2_META = new LinkedList<OPF2Meta>();
		LINK = new LinkedList<Link>();
	}
	
	/**********************************************************************************
	 * Class that describes a required element.
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class RequiredElement{		
		public String textContent;
		public String id;
		
		public boolean check(){
			return false;
		}		
		
		public String toString(){
			return "id: "+id+" - textContent: "+textContent;
		}
	}
	
	/***********************************************************************************
	 * Class that describes an optional element.
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class OptionalElement extends RequiredElement{
		public String nodeName;
		
		public String toString(){
			return "nodeName: "+nodeName+" - "+super.toString();
		}
	}
	
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
		
		public String toString(){
			return "xml_lang: "+xml_lang+" - dir: "+dir+" - "+super.toString();
		}
	}
	
	
	//
	
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
		
		public String toString(){
			return "xml_lang: "+xml_lang+" - dir: "+dir+" - "+super.toString();
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
		
		public String toString(){
			return "property: "+property+" - refines: "+refines+" - scheme: "+scheme+" - "+super.toString();
		}
	}
	
	/***********************************************************************************
	 * OPF2 deprecated meta class.
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class OPF2Meta extends OptionalElement{
		public String name;
		public String content;
		
		public String toString(){
			return "name: "+name+" - content: "+content;
		}
	}
	
	/***********************************************************************************
	 * cd:language class
	 * @author Carlos A.P.
	 *
	 **********************************************************************************/
	public class DCLanguage extends RequiredElement{
		
		public boolean check(){
			return true;
		}	
	}
	
	/**********************************************************************************
	 * Optional DCMES elements 
	 **********************************************************************************/
	
	/*public class DCContributor extends OptionalElementLangAndDir{}
	
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
	
	public class DCType extends OptionalElement{}	*/
	
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
		
		public String toString(){
			return "href: "+href+" - rel: "+rel+" - refines: "+refines+" - media_type: "+media_type+" - "+super.toString();
		}
	}

	@Override
	public boolean isValid() throws EPUBException {
		
		if (DC_IDENTIFIER.size() < 1) throw new InvalidMetadataException("There is no dc:identifier element in metadata. It's needed one element of this type at least.");
		if (DC_TITLE.size() < 1) throw new InvalidMetadataException("There is no dc:title element in metadata. It's needed one element of this type at least.");
		if (DC_LANGUAGE.size() < 1) throw new InvalidMetadataException("There is no dc:language element in metadata. It's needed one element of this type at least.");
		if (META.size() < 1) throw new InvalidMetadataException("There is no meta element in metadata. It's needed one element of this type at least.");
		
		//ID constraints
		//**************************************************
		//Is there an id with 0 length?
		//Is the id of every identifier unique?		
		//Is there only one id marked as unique-identifier?
		
		return false;
	}

	public void parse(Node pnode) {
		
		NodeList children = pnode.getChildNodes();
		int size = children.getLength();
		
		for (int i=0;i<size;i++){
			Node item = children.item(i);
			String nodeName = item.getNodeName();
			
			//Required nodes
			if (nodeName.equalsIgnoreCase("dc:identifier")){
				DC_IDENTIFIER.add(parseIdentifier(item));
			}else if (nodeName.equalsIgnoreCase("dc:title")){
				DC_TITLE.add(parseTitle(item));
			}else if (nodeName.equalsIgnoreCase("dc:language")){
				DC_LANGUAGE.add(parseLanguage(item));
			}else if (nodeName.equalsIgnoreCase("meta")){
				
				boolean isOPF2 = false;
				
				if (item.hasAttributes()){
					//Are there "name" and "content" attributes? If true, OPF2.					
					for (int j=0;j<item.getAttributes().getLength();j++){
						if (item.getAttributes().item(j).getNodeName().equalsIgnoreCase("name")){
							isOPF2 = true;
							break;
						}
					}
				}
				
				if (isOPF2){
					OPF2_META.add(parseOPF2Meta(item));
				}else{
					META.add(parseMeta(item));					
				}
			//Optional DCMES nodes.
			//I use different lines for same behaviours to achieve a better readability of the code.
			}else if (nodeName.equalsIgnoreCase("dc:contributor")){ //*
				DCMES_OPTIONAL.add(parseDCMESOptionalElementLandAndDir(item));
			}else if (nodeName.equalsIgnoreCase("dc:coverage")){ //* 
				DCMES_OPTIONAL.add(parseDCMESOptionalElementLandAndDir(item));
			}else if (nodeName.equalsIgnoreCase("dc:creator")){ //*
				DCMES_OPTIONAL.add(parseDCMESOptionalElementLandAndDir(item));
			}else if (nodeName.equalsIgnoreCase("dc:date")){
				DCMES_OPTIONAL.add(parseDCMESOptionalElement(item));
			}else if (nodeName.equalsIgnoreCase("dc:description")){ //*
				DCMES_OPTIONAL.add(parseDCMESOptionalElementLandAndDir(item));
			}else if (nodeName.equalsIgnoreCase("dc:format")){
				DCMES_OPTIONAL.add(parseDCMESOptionalElement(item));
			}else if (nodeName.equalsIgnoreCase("dc:publisher")){ //*
				DCMES_OPTIONAL.add(parseDCMESOptionalElementLandAndDir(item));
			}else if (nodeName.equalsIgnoreCase("dc:relation")){ //*
				DCMES_OPTIONAL.add(parseDCMESOptionalElementLandAndDir(item));
			}else if (nodeName.equalsIgnoreCase("dc:rights")){ //*
				DCMES_OPTIONAL.add(parseDCMESOptionalElementLandAndDir(item));
			}else if (nodeName.equalsIgnoreCase("dc:source")){
				DCMES_OPTIONAL.add(parseDCMESOptionalElement(item));
			}else if (nodeName.equalsIgnoreCase("dc:subject")){ //*
				DCMES_OPTIONAL.add(parseDCMESOptionalElementLandAndDir(item));
			}else if (nodeName.equalsIgnoreCase("dc:type")){
				DCMES_OPTIONAL.add(parseDCMESOptionalElement(item));
			}
		}		
	}

	private DCIdentifier parseIdentifier(Node pitem) {
		
		DCIdentifier identifier = new DCIdentifier();
		
		identifier.textContent = pitem.getTextContent();
		
		if (pitem.hasAttributes()){
			String identifierId = EPUBUtils.getAttributeValue("id", pitem.getAttributes());	
			
			if (identifierId != null && identifierId.length() > 0) identifier.id = identifierId; 			
		}
		return identifier;
	}
	
	private DCTitle parseTitle(Node pitem) {
		
		DCTitle title = new DCTitle();		
		
		title.textContent = pitem.getTextContent();
		
		if (pitem.hasAttributes()){
			String titleId = EPUBUtils.getAttributeValue("id", pitem.getAttributes());			
			if (titleId != null && titleId.length() > 0) title.id = titleId;
			
			String titleLang = EPUBUtils.getAttributeValue("xml:lang", pitem.getAttributes());			
			if (titleLang != null && titleLang.length() > 0) title.xml_lang = titleLang;
			
			String titleDir = EPUBUtils.getAttributeValue("dir", pitem.getAttributes());			
			if (titleDir != null && titleDir.length() > 0) title.dir = titleDir;
		}
		
		return title;
	}
	
	private DCLanguage parseLanguage(Node pitem) {
		
		DCLanguage language = new DCLanguage();
		
		language.textContent = pitem.getTextContent();
		
		if (pitem.hasAttributes()){
			String identifierId = EPUBUtils.getAttributeValue("id", pitem.getAttributes());	
			
			if (identifierId != null && identifierId.length() > 0) language.id = identifierId; 			
		}
		
		return language;
	}
	
	private OptionalElement parseDCMESOptionalElement(Node pitem){
		
		OptionalElement optionalElement = new OptionalElement();
		
		optionalElement.nodeName = pitem.getNodeName();
		optionalElement.textContent = pitem.getTextContent();
		
		if (pitem.hasAttributes()){
			String elementId = EPUBUtils.getAttributeValue("id", pitem.getAttributes());	
			
			if (elementId != null && elementId.length() > 0) optionalElement.id = elementId; 			
		}		
		
		return optionalElement;
	}
	
	private OptionalElementLangAndDir parseDCMESOptionalElementLandAndDir(Node pitem){
		OptionalElementLangAndDir optionalElement = new OptionalElementLangAndDir();
		
		optionalElement.nodeName = pitem.getNodeName();
		optionalElement.textContent = pitem.getTextContent();
		
		if (pitem.hasAttributes()){
			String elementId = EPUBUtils.getAttributeValue("id", pitem.getAttributes());			
			if (elementId != null && elementId.length() > 0) optionalElement.id = elementId;
			
			String elementLang = EPUBUtils.getAttributeValue("xml:lang", pitem.getAttributes());				
			if (elementId != null && elementId.length() > 0) optionalElement.xml_lang = elementLang; 			
			
			String elementDir = EPUBUtils.getAttributeValue("dir", pitem.getAttributes());				
			if (elementId != null && elementId.length() > 0) optionalElement.dir = elementDir; 			
		}	
		
		return optionalElement;
	}
	
	private Meta parseMeta(Node pitem){
		Meta metaElement = new Meta();
		
		metaElement.textContent = pitem.getTextContent();
		
		if (pitem.hasAttributes()){			
			String elementId = EPUBUtils.getAttributeValue("id", pitem.getAttributes());			
			if (elementId != null && elementId.length() > 0) metaElement.id = elementId;
			
			String elementLang = EPUBUtils.getAttributeValue("xml:lang", pitem.getAttributes());				
			if (elementId != null && elementId.length() > 0) metaElement.xml_lang = elementLang; 			
			
			String elementDir = EPUBUtils.getAttributeValue("dir", pitem.getAttributes());				
			if (elementId != null && elementId.length() > 0) metaElement.dir = elementDir; 		
			
			String property = EPUBUtils.getAttributeValue("property", pitem.getAttributes());			
			if (property != null && property.length() > 0) metaElement.property = property;
			
			String refines = EPUBUtils.getAttributeValue("refines", pitem.getAttributes());				
			if (refines != null && refines.length() > 0) metaElement.refines = refines; 			
			
			String scheme = EPUBUtils.getAttributeValue("scheme", pitem.getAttributes());				
			if (scheme != null && scheme.length() > 0) metaElement.dir = scheme; 			
		}	
		
		return metaElement;
	}
	
	private Link parseLink(Node pitem){
		Link link = new Link();
		
		link.textContent = pitem.getTextContent();
		
		if (pitem.hasAttributes()){			
			String href = EPUBUtils.getAttributeValue("href", pitem.getAttributes());			
			if (href != null && href.length() > 0) link.href = href;
			
			String rel = EPUBUtils.getAttributeValue("rel", pitem.getAttributes());				
			if (rel != null && rel.length() > 0) link.rel = rel; 			
			
			String elementId = EPUBUtils.getAttributeValue("id", pitem.getAttributes());				
			if (elementId != null && elementId.length() > 0) link.id = elementId; 		
			
			String mediaType = EPUBUtils.getAttributeValue("media-type", pitem.getAttributes());			
			if (mediaType != null && mediaType.length() > 0) link.media_type = mediaType;
			
			String refines = EPUBUtils.getAttributeValue("refines", pitem.getAttributes());				
			if (refines != null && refines.length() > 0) link.refines = refines;			
		}	
		
		return link;
	}
	
	private OPF2Meta parseOPF2Meta(Node pitem){
		OPF2Meta meta = new OPF2Meta();
		
		if (pitem.hasAttributes()){			
			String name = EPUBUtils.getAttributeValue("name", pitem.getAttributes());			
			if (name != null && name.length() > 0) meta.name = name;
			
			String content = EPUBUtils.getAttributeValue("content", pitem.getAttributes());				
			if (content != null && content.length() > 0) meta.content = content;			
		}	
		
		return meta;
	}
	
	public LinkedList<DCIdentifier> getDC_IDENTIFIER() {
		return DC_IDENTIFIER;
	}

	public LinkedList<DCTitle> getDC_TITLE() {
		return DC_TITLE;
	}

	public LinkedList<DCLanguage> getDC_LANGUAGE() {
		return DC_LANGUAGE;
	}

	public LinkedList<Meta> getMETA() {
		return META;
	}

	public LinkedList<OptionalElement> getDCMES_OPTIONAL() {
		return DCMES_OPTIONAL;
	}

	public LinkedList<OPF2Meta> getOPF2_META() {
		return OPF2_META;
	}

	public LinkedList<Link> getLINK() {
		return LINK;
	}
	
	public OptionalElement getOptionalElementByName(String pname){
		for (OptionalElement opt : DCMES_OPTIONAL){
			if (opt.nodeName.equalsIgnoreCase(pname)) return opt;
		}
		return null;
	}
	
}
