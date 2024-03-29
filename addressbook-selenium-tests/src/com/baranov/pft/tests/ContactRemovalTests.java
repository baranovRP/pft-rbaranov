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
		.getUiContacts();

	int index = getRandomIndex(oldList.size());

	app.getContactHelper().deleteContact(index);

	// save new state
	SortedListOf<ContactData> newList = app.getContactModel().getContacts();

	// compare states
	assertThat(newList, equalTo(oldList.without(index)));
    }
}
