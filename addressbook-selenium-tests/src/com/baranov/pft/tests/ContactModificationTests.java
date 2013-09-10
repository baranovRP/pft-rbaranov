package com.baranov.pft.tests;

import static com.baranov.pft.tests.ContactDataGenerator.loadContactsFromXmlFile;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baranov.pft.utils.SortedListOf;

public class ContactModificationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> contactsFromFile() throws IOException {
	return wrapContactsForDataProvider(
		loadContactsFromXmlFile(new File("contacts.xml"))).iterator();
    }

    @Test(dataProvider = "contactsFromFile")
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
