package com.baranov.pft.tests;

import static com.baranov.pft.fw.ContactHelper.MODIFICATION;
import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void modifySomeContactWithValidData(ContactData contact) {

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	int index = getRandomIndex(oldList);

	app.getContactHelper().modifyContact(index, contact, MODIFICATION);

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.remove(index);
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }
}
