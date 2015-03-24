package com.pcg.epubspec.testing;

import static org.junit.Assert.*;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.pcg.epubspec.Metadata;

public class EPUBManifestTestCases {

	@Test
	public void test() {
		String manifestTest = "<manifest>\r\n" + 
				"    <item href=\"cover.jpeg\" id=\"cover\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_000.html\" id=\"id122\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_001.html\" id=\"id121\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_002.html\" id=\"id120\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_003.html\" id=\"id119\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_004.html\" id=\"id118\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_005.html\" id=\"id117\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_006.html\" id=\"id116\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_007.html\" id=\"id115\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_008.html\" id=\"id114\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_009.html\" id=\"id113\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_010.html\" id=\"id112\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_011.html\" id=\"id111\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_012.html\" id=\"id110\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_013.html\" id=\"id19\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_014.html\" id=\"id18\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_015.html\" id=\"id17\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_016.html\" id=\"id16\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_017.html\" id=\"id15\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_018.html\" id=\"id14\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_019.html\" id=\"id13\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"CR!BKHC983TSN68D8QHSRWFA8CG0JKM_split_020.html\" id=\"id12\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"images/00001.jpg\" id=\"id3\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"images/00002.jpg\" id=\"id4\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"images/00003.jpg\" id=\"id5\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"images/00004.jpg\" id=\"id6\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"images/00005.jpg\" id=\"id7\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"images/00006.jpg\" id=\"id8\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"images/00007.jpg\" id=\"id9\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"images/00008.jpg\" id=\"id10\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"images/00009.jpg\" id=\"id11\" media-type=\"image/jpeg\"/>\r\n" + 
				"    <item href=\"page_styles.css\" id=\"page_css\" media-type=\"text/css\"/>\r\n" + 
				"    <item href=\"stylesheet.css\" id=\"css\" media-type=\"text/css\"/>\r\n" + 
				"    <item href=\"titlepage.xhtml\" id=\"titlepage\" media-type=\"application/xhtml+xml\"/>\r\n" + 
				"    <item href=\"toc.ncx\" id=\"ncx\" media-type=\"application/x-dtbncx+xml\"/>\r\n" + 
				"  </manifest>";
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder;  
	    try  
	    {  
	        builder = factory.newDocumentBuilder();  
	        Document document = builder.parse( new InputSource( new StringReader(manifestTest)));  
	        
	        Metadata metadata = new Metadata();        
	        
	        metadata.parse(document.getElementsByTagName("metadata").item(0));
	        
	        //Element sizes checking
	        assertNotNull("dc:publisher size",metadata.getOptionalElementByName("dc:publisher"));
	        assertEquals("dc:identifier size",3,metadata.getDC_IDENTIFIER().size());
	        assertEquals("dc:language size",1,metadata.getDC_LANGUAGE().size());
	        assertNotNull("dc:creator size",metadata.getOptionalElementByName("dc:creator"));
	        assertEquals("dc:title size",1,metadata.getDC_TITLE().size());
	        assertNotNull("dc:date size",metadata.getOptionalElementByName("dc:date"));
	        assertNotNull("dc:contributor size",metadata.getOptionalElementByName("dc:contributor"));
	        assertEquals("meta size",0,metadata.getMETA().size());
	        assertEquals("OPF2 meta size",3,metadata.getOPF2_META().size());   
	        
	        assertEquals("dc:publisher toString","xml_lang: null - dir: null - nodeName: dc:publisher - id: null - textContent: Penguin Random House Grupo Editorial España",metadata.getOptionalElementByName("dc:publisher").toString());
	        assertEquals("dc:contributor toString","xml_lang: null - dir: null - nodeName: dc:contributor - id: null - textContent: calibre (1.8.0) [http://calibre-ebook.com]",metadata.getOptionalElementByName("dc:contributor").toString());
	        assertEquals("dc:creator toString","xml_lang: null - dir: null - nodeName: dc:creator - id: null - textContent: Ken Follett",metadata.getOptionalElementByName("dc:creator").toString());
	        assertEquals("dc:date toString","nodeName: dc:date - id: null - textContent: 2014-09-24T22:00:00+00:00",metadata.getOptionalElementByName("dc:date").toString());
	        assertEquals("dc:language toString","id: null - textContent: es",metadata.getDC_LANGUAGE().getFirst().toString());
	        assertEquals("dc:title toString","xml_lang: null - dir: null - id: null - textContent: El misterio del planeta de los gusanos",metadata.getDC_TITLE().getFirst().toString());

	        assertEquals("dc:identifier 1 toString","id: null - textContent: B00LDAHPYU",metadata.getDC_IDENTIFIER().get(0).toString());
	        assertEquals("dc:identifier 2 toString","id: uuid_id - textContent: 46bdbe55-33ba-421c-9b80-5585f23bc2d0",metadata.getDC_IDENTIFIER().get(1).toString());
	        assertEquals("dc:identifier 3 toString","id: null - textContent: 46bdbe55-33ba-421c-9b80-5585f23bc2d0",metadata.getDC_IDENTIFIER().get(2).toString());

	        assertEquals("name: calibre:title_sort - content: misterio del planeta de los gusanos, El",metadata.getOPF2_META().get(0).toString());
	        assertEquals("name: calibre:timestamp - content: 2014-09-25T12:25:17.666000+00:00",metadata.getOPF2_META().get(1).toString());
	        assertEquals("name: cover - content: cover",metadata.getOPF2_META().get(2).toString());
	    } catch (Exception e) {  
	        e.printStackTrace();
	    } 
	}

}
