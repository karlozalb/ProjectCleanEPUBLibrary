package com.pcg.epubspec;

import com.pcg.exceptions.EPUBException;

public interface IMetadata {
	
	public String getBookTitle() throws EPUBException;
	public String[] getBookAuthor() throws EPUBException;

}
