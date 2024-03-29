package com.baranov.pft.tests;

import static com.baranov.pft.fw.ContactDataGenerator.generateRandomContacts;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.baranov.pft.fw.ApplicationManager;
import com.baranov.pft.fw.Contact;

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
		app = ApplicationManager.getInstance(properties);
		log.info("setUp end");
	}

	@AfterTest
	public void tearDown() throws Exception {
		log.info("tearDown start");
		ApplicationManager.getInstance(null).stop();
		log.info("tearDown end");
	}

	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator() throws IOException {
		return wrapContactsForDataProvider(generateRandomContacts(1))
				.iterator();
	}

	public static List<Object[]> wrapContactsForDataProvider(
			List<Contact> contacts) {
		List<Object[]> list = new ArrayList<>();
		for (Contact contact : contacts) {
			list.add(new Object[] { contact });
		}
		return list;
	}
}
