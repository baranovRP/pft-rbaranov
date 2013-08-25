package com.baranov.pft.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.baranov.pft.utils.SortedListOf;

public class ContactRemovalTests extends TestBase {

    @Test
    public void deleteSomeContact() {
	// save old state
	SortedListOf<ContactData> oldList = app.getContactHelper()
		.getContacts();

	int index = getRandomIndex(oldList);

	app.getContactHelper().deleteContact(index);

	// save new state
	SortedListOf<ContactData> newList = app.getContactHelper()
		.getContacts();

	// compare states
	assertThat(newList, equalTo(oldList.without(index)));
    }
}
