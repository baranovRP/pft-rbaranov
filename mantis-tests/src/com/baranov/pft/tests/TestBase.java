package com.baranov.pft.tests;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.baranov.pft.fw.ApplicationManager;
import com.baranov.pft.fw.UserData;

public class TestBase {
	public static ApplicationManager app;
	private int checkFrequency;
	private int checkCounter;
	public UserData userAdministrator;

	@BeforeTest
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile",
				"application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);
		userAdministrator = new UserData().withLogin("administrator")
				.withPassword("root").withEmail("root@localhost");
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

	@DataProvider
	public Iterator<Object[]> validUser() {
		List<Object[]> list = new ArrayList<>();
		UserData user = new UserData().withLogin("dz23_brp_pft")
				.withPassword("dz23_brp_pft").withEmail("dz23_brp_pft@ukr.net");
		list.add(new Object[] { user });
		return list.iterator();
	}

	public int getRandomIndex(int size) {
		return new Random().nextInt(size);
	}
}
