package com.baranov.pft.tests;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.baranov.pft.fw.ApplicationManager;

public class TestBase {
	public static ApplicationManager app;
	private int checkFrequency;
	private int checkCounter;

	@BeforeTest
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile",
				"application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);
		checkCounter = 0;
		checkFrequency = Integer.parseInt(properties.getProperty(
				"check.frequency", "0"));
	}

	protected boolean wantToCheck() {
		checkCounter++;
		if (checkCounter > checkFrequency) {
			checkCounter = 0;
			return true;
		} else {
			return false;
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	}
}
