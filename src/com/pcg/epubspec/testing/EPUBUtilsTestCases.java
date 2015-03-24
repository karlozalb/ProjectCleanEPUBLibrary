package com.pcg.epubspec.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pcg.epubloader.EPUBUtils;

public class EPUBUtilsTestCases {

	@Test
	public void leadingAndTrailingTrim() {
		
		StringBuilder testStringTrail = new StringBuilder("trailing whitespaces              ");	
		assertEquals("Test: leadingAndTrailingTrim 1","trailing whitespaces",EPUBUtils.trimLeadingAndTrailingWhiteSpaces(testStringTrail).toString());
		
		StringBuilder testStringLead = new StringBuilder("			leading whitespaces");		
		assertEquals("Test: leadingAndTrailingTrim 2","leading whitespaces",EPUBUtils.trimLeadingAndTrailingWhiteSpaces(testStringLead).toString());

		StringBuilder testStringLeadAndTrail = new StringBuilder("			leading and trailing whitespaces				");		
		assertEquals("Test: leadingAndTrailingTrim 3","leading and trailing whitespaces",EPUBUtils.trimLeadingAndTrailingWhiteSpaces(testStringLeadAndTrail).toString());
		
		StringBuilder testStringNoLeadOrTrail = new StringBuilder("no leading or trailing whitespaces");		
		assertEquals("Test: leadingAndTrailingTrim 4","no leading or trailing whitespaces",EPUBUtils.trimLeadingAndTrailingWhiteSpaces(testStringNoLeadOrTrail).toString());
	}

}
