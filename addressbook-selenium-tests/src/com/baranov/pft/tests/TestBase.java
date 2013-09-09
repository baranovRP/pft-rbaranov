package com.baranov.pft.tests;

import static com.baranov.pft.tests.ContactDataGenerator.generateRandomContacts;
import static com.baranov.pft.tests.GroupDataGenerator.generateRandomGroups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.baranov.pft.fw.ApplicationManager;

public class TestBase {
    public static ApplicationManager app;

    @BeforeTest
    public void setUp() throws Exception {
	app = new ApplicationManager();
    }

    @AfterTest
    public void tearDown() throws Exception {
	app.stop();
    }

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() {
	return wrapGroupsForDataProvider(generateRandomGroups(5)).iterator();
    }

    private List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
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

    private List<Object[]> wrapContactsForDataProvider(
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
