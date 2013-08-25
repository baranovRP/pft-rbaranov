package com.baranov.pft.tests;

import static com.baranov.pft.fw.ContactHelper.CREATION;
import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactAddTests extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactCreationWithValidData(ContactData contact)
	    throws Exception {

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	app.getContactHelper().createContact(contact, CREATION);

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }
}
