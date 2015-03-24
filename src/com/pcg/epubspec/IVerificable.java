package com.pcg.epubspec;

import com.pcg.exceptions.EPUBException;

public interface IVerificable {

	public boolean isValid() throws EPUBException;	
	
}
