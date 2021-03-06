package com.exner.tools.analyticstdd.SiteInfrastructureTests.tests;

import com.fasterxml.jackson.databind.node.TextNode;

public class GenericJavascriptTestCase extends WebDriverBasedTestCase {
	private final String _jsToRun;

	public GenericJavascriptTestCase(String pageURL, Object params) {
		super(pageURL);
		setName("Generic JS - " + pageURL);
		
		if (TextNode.class.isAssignableFrom(params.getClass())) {
			_jsToRun = ((TextNode) params).asText();
		} else {
			throw new IllegalArgumentException("Must define some script to run!");
		}
	}

	@Override
	protected void runTest() throws Throwable {
		Object response = _jsExecutor.executeScript(_jsToRun);
		
		if (Boolean.class.isAssignableFrom(response.getClass())) {
			boolean br = (Boolean) response;
			assertTrue("JS must return true", br);
		} else {
			fail("JS must return true!");
		}
	}

}
