package com.exner.tools.analyticstdd.SiteInfrastructureTests.tests.adobe;

import com.exner.tools.analyticstdd.SiteInfrastructureTests.Tools;
import com.exner.tools.analyticstdd.SiteInfrastructureTests.tests.WebDriverBasedTestCase;
import com.fasterxml.jackson.databind.node.BooleanNode;

public class DTMLoadedTestCase extends WebDriverBasedTestCase {

	private final boolean _test;

	public DTMLoadedTestCase(String pageURL, Object params) {
		super(pageURL);

		// handle parameters
		String note = "";
		if (BooleanNode.class.isAssignableFrom(params.getClass())) {
			_test = ((BooleanNode) params).asBoolean();
			if (!_test) {
				note = " (inactive)";
			}
		} else {
			_test = true;
		}

		setName(Tools.DTM + " loaded" + note + " - " + pageURL);
	}

	@Override
	protected void runTest() throws Throwable {
		if (_test) {
			// check whether DTM has been loaded on the page
			Object response = _jsExecutor
					.executeScript("if (typeof _satellite !== 'undefined') { return true } else { return false }");

			// make sure the element exists
			if (Boolean.class.isAssignableFrom(response.getClass())) {
				assertTrue(Tools.DTM + " must load", (Boolean) response);
			} else {
				fail(Tools.DTM + " not loaded");
			}
		}
	}

}
