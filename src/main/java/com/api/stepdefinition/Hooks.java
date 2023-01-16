package com.api.stepdefinition;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

class Hooks {
	private static final Logger LOG = LogManager.getLogger(Hooks.class);

	@Before
	public void testStart(Scenario scenario) {
		LOG.info("*****************************************************************************************");
		LOG.info("	Scenario: " + scenario.getName());
		LOG.info("*****************************************************************************************");
	}
}
