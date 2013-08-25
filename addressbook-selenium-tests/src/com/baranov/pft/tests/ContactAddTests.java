package com.baranov.pft.tests;

import static com.baranov.pft.fw.ContactHelper.CREATION;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.baranov.pft.utils.SortedListOf;

public class ContactAddTests extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactCreationWithValidData(ContactData contact)
	    throws Exception {

	// save old state
	SortedListOf<ContactData> oldList = app.getContactHelper()
		.getContacts();

	app.getContactHelper().createContact(contact, CREATION);

	// save new state
	SortedListOf<ContactData> newList = app.getContactHelper()
		.getContacts();

	// compare states
	assertThat(newList, equalTo(oldList.withAdded(contact)));
    }
}
