package com.pcg.epubspec.testing;

import static org.junit.Assert.*;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.pcg.epubspec.Metadata;

public class EPUBMetadataTestCases {

	@Test
	public void metadataParsingAndLoading() {
		
		String metadataTest = "<metadata xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:opf=\"http://www.idpf.org/2007/opf\" xmlns:dcterms=\"http://purl.org/dc/terms/\" xmlns:calibre=\"http://calibre.kovidgoyal.net/2009/metadata\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\r\n" + 
				"    <dc:publisher>Penguin Random House Grupo Editorial España</dc:publisher>\r\n" + 
				"    <meta name=\"calibre:title_sort\" content=\"misterio del planeta de los gusanos, El\"/>\r\n" + 
				"    <dc:language>es</dc:language>\r\n" + 
				"    <dc:creator opf:file-as=\"Follett, Ken\" opf:role=\"aut\">Ken Follett</dc:creator>\r\n" + 
				"    <meta name=\"calibre:timestamp\" content=\"2014-09-25T12:25:17.666000+00:00\"/>\r\n" + 
				"    <dc:title>El misterio del planeta de los gusanos</dc:title>\r\n" + 
				"    <meta name=\"cover\" content=\"cover\"/>\r\n" + 
				"    <dc:date>2014-09-24T22:00:00+00:00</dc:date>\r\n" + 
				"    <dc:contributor opf:role=\"bkp\">calibre (1.8.0) [http://calibre-ebook.com]</dc:contributor>\r\n" + 
				"    <dc:identifier opf:scheme=\"MOBI-ASIN\">B00LDAHPYU</dc:identifier>\r\n" + 
				"    <dc:identifier id=\"uuid_id\" opf:scheme=\"uuid\">46bdbe55-33ba-421c-9b80-5585f23bc2d0</dc:identifier>\r\n" + 
				"    <dc:identifier opf:scheme=\"calibre\">46bdbe55-33ba-421c-9b80-5585f23bc2d0</dc:identifier>\r\n" + 
				"  </metadata>";
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder;  
	    try  
	    {  
	        builder = factory.newDocumentBuilder();  
	        Document document = builder.parse( new InputSource( new StringReader(metadataTest)));  
	        
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
	
	@Test
	public void metadataParsingAndLoading2() {
		
		String metadataTest = "<metadata xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\r\n" + 
				"		<dc:identifier id=\"uid\">com.github.epub-testsuite.epub30-test-0100</dc:identifier>\r\n" + 
				"		<dc:description>Tests for Content Documents in a reflowable context [UNDER CONSTRUCTION]</dc:description>\r\n" + 
				"		<dc:title id=\"title\">EPUBTEST 0100 - Reflowable Content Tests</dc:title>\r\n" + 
				"		<meta refines=\"title\" property=\"title-type\">main</meta>\r\n" + 
				"		<!-- repeat creator element for all contributors -->\r\n" + 
				"		<dc:creator id=\"creator1\">Markus Gylling</dc:creator>\r\n" + 
				"		<dc:creator id=\"creator2\">Vincent Gros</dc:creator>\r\n" + 
				"		<dc:creator id=\"creator3\">Toshiaki Koike</dc:creator>\r\n" + 
				"		<dc:creator id=\"creator4\">Marisa DeMeglio</dc:creator>\r\n" + 
				"		<dc:creator id=\"creator5\">Matt Garrish</dc:creator>\r\n" + 
				"		<dc:creator id=\"creator6\">Ori Idan</dc:creator>\r\n" + 
				"		<meta refines=\"#creator1\" property=\"role\" scheme=\"marc:relators\">aut</meta>\r\n" + 
				"		<meta refines=\"#creator1\" property=\"file-as\">Gylling, Markus</meta>\r\n" + 
				"		<meta refines=\"#creator2\" property=\"role\" scheme=\"marc:relators\">aut</meta>\r\n" + 
				"		<meta refines=\"#creator2\" property=\"file-as\">Gros, Vincent</meta>\r\n" + 
				"		<meta refines=\"#creator3\" property=\"role\" scheme=\"marc:relators\">aut</meta>\r\n" + 
				"		<meta refines=\"#creator3\" property=\"file-as\">Koike, Toshiaki</meta>\r\n" + 
				"		<meta refines=\"#creator3\" property=\"alternate-script\" xml:lang=\"ja\">小池利明</meta>\r\n" + 
				"		<meta refines=\"#creator4\" property=\"role\" scheme=\"marc:relators\">aut</meta>\r\n" + 
				"		<meta refines=\"#creator4\" property=\"file-as\">DeMeglio, Marisa</meta>\r\n" + 
				"		<meta refines=\"#creator5\" property=\"role\" scheme=\"marc:relators\">aut</meta>\r\n" + 
				"		<meta refines=\"#creator5\" property=\"file-as\">Garrish, Matt</meta>\r\n" + 
				"		<meta refines=\"#creator6\" property=\"role\" scheme=\"marc:relators\">aut</meta>\r\n" + 
				"		<meta refines=\"#creator6\" property=\"file-as\">Idan, Ori</meta>\r\n" + 
				"		<dc:language>en</dc:language>\r\n" + 
				"		<dc:language>jp</dc:language>\r\n" + 
				"        <meta property=\"dcterms:modified\">2014-04-24T13:00:00Z</meta>\r\n" + 
				"	</metadata>";
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder;  
	    try  
	    {  
	        builder = factory.newDocumentBuilder();  
	        Document document = builder.parse( new InputSource( new StringReader(metadataTest)));  
	        
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
