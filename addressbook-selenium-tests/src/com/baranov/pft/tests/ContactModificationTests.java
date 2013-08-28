package com.baranov.pft.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.baranov.pft.utils.SortedListOf;

public class ContactModificationTests extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void modifySomeContactWithValidData(ContactData contact) {

	// save old state
	SortedListOf<ContactData> oldList = app.getContactHelper()
		.getContacts();

	int index = getRandomIndex(oldList.size());

	app.getContactHelper().modifyContact(index, contact);

	// save new state
	SortedListOf<ContactData> newList = app.getContactHelper()
		.getContacts();

	// compare states
	assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));
    }
}
