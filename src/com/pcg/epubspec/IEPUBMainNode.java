package com.pcg.epubspec;

import org.w3c.dom.Node;

/**
 * This interface is only for the children of <package> 
 * @author Carlos A.P.
 *
 */

public interface IEPUBMainNode {

	public void parse(Node pnode);
	
}
