package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactAddTests extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactCreationWithValidData(ContactData contact)
	    throws Exception {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	app.getNavigationHelper().gotoPage("add new");
	app.getContactHelper().fillContactForm(contact);
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoHomePage();

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }
}
