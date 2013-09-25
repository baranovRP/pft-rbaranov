package com.baranov.pft.tests;

import static com.baranov.pft.tests.ContactDataGenerator.generateRandomContacts;
import static com.baranov.pft.tests.GroupDataGenerator.generateRandomGroups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

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

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() throws IOException {
	return wrapGroupsForDataProvider(generateRandomGroups(5)).iterator();
    }

    public static List<Object[]> wrapGroupsForDataProvider(
	    List<GroupData> groups) {
	List<Object[]> list = new ArrayList<>();
	for (GroupData group : groups) {
	    list.add(new Object[] { group });
	}
	return list;
    }

    @DataProvider
    public Iterator<Object[]> randomValidContactGnerator() {
	return wrapContactsForDataProvider(generateRandomContacts(5))
		.iterator();
    }

    public static List<Object[]> wrapContactsForDataProvider(
	    List<ContactData> contacts) {
	List<Object[]> list = new ArrayList<>();
	for (ContactData contact : contacts) {
	    list.add(new Object[] { contact });
	}
	return list;
    }

    public int getRandomIndex(int size) {
	return new Random().nextInt(size);
    }
}
