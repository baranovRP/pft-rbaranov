package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void deleteSomeContact() {
	app.navigateTo().mainPage();

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	int index = getRandomIndex(oldList);

	app.getContactHelper().deleteContact(index);
	// app.getActionHelper().submitUpdate();
	// app.navigateTo().returntoHomePage();
	app.navigateTo().mainPage();

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.remove(index);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }
}
