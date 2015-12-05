package com.pcg.epubloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.zip.ZipEntry;

import org.apache.commons.io.IOUtils;

import com.pcg.epubspec.Manifest.Item;
import com.pcg.epubspec.Spine.ItemRef;

public class EPUBLauncher {
	public static void main(String[] args) {
		EPUBLoaderHelper epubloader = new EPUBLoaderHelper("book.epub");
		
		System.out.println(epubloader.getPackage().toString());
		
		LinkedList<ItemRef> itemRefs = epubloader.getPackage().getSpine().getSpine();
		
		String fileRef = itemRefs.get(0).idref;
		
		Item it = epubloader.getPackage().getManifest().getIdemByIdRef(fileRef);
		
		if (it != null){
			InputStream is = epubloader.getInputStream(it.href);		
			StringWriter writer = new StringWriter();
			try {
				IOUtils.copy(is, writer, Charset.forName("UTF-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(writer.toString());
		}
	}	
}
