package com.baranov.pft.tests;

import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.baranov.pft.fw.ApplicationManager;

public class TestBase {
	protected Logger log = Logger.getLogger("TEST");
	protected ApplicationManager app;

	@BeforeClass
	@Parameters({ "configFile" })
	public void setUp(@Optional String configFile) throws Exception {
		if (configFile == null) {
			configFile = System.getProperty("configFile");
		}
		if (configFile == null) {
			configFile = System.getenv("configFile");
		}
		if (configFile == null) {
			configFile = ("application.properties");
		}

		Properties properties = new Properties();
		properties.load(new FileReader(configFile));
		log.info("setUp start");
		app = ApplicationManager.getInstance();
	}

	@AfterTest
	public void tearDown() throws Exception {
		log.info("tearDown start");
		ApplicationManager.getInstance().stop();
		log.info("tearDown end");
	}
}
