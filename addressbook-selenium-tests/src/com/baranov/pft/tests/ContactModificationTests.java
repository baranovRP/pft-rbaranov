package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void modifySomeContact() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	int index = getRandomIndex(oldList);

	app.getContactHelper().initContactModification(index);
	ContactData contact = new ContactData();
	contact.setFirstName("New");
	contact.setSecondName("Contact");
	contact.setFullName(contact.getFirstName(), contact.getSecondName());
	app.getContactHelper().fillContactName(contact);
	app.getContactHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.remove(index);
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }

    @Test
    public void modifySomeContactviaViewDetails() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	int index = getRandomIndex(oldList);

	app.getContactHelper().viewContact(index);
	app.getContactHelper().submitModify();
	ContactData contact = new ContactData();
	contact.setFirstName("NewView");
	contact.setSecondName("ContactDetails");
	contact.setFullName(contact.getFirstName(), contact.getSecondName());
	app.getContactHelper().fillContactName(contact);
	app.getContactHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.remove(index);
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }

    @Test
    public void modifySomeContactAddr() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	int index = getRandomIndex(oldList);

	app.getContactHelper().initContactModification(index);
	ContactData contact = new ContactData();
	BirhtdayData bdata = new BirhtdayData();
	GroupData group = new GroupData();
	ContactAddress contactAddr = new ContactAddress();
	contactAddr.setEmail("theNew@email.net");
	contact.setContactAddress(contactAddr);
	contact.setSecondName("modifySomeContactAddr");
	contact.setFullName(app.getContactHelper().getFirstNameFromPage(),
		contact.getSecondName());
	contact.setBdata(bdata);
	contact.setGroup(group);
	app.getContactHelper().fillContactForm(contact);
	app.getContactHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.remove(index);
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }

    @Test
    public void modifySomeContactBirth() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	int index = getRandomIndex(oldList);

	app.getContactHelper().initContactModification(index);
	ContactData contact = new ContactData();
	BirhtdayData bdata = new BirhtdayData();
	GroupData group = new GroupData();
	ContactAddress contactAddr = new ContactAddress();
	contactAddr.setEmail("theNew@email.net");
	contact.setContactAddress(contactAddr);
	contact.setSecondName("modifySomeContactAddr");
	contact.setFullName(app.getContactHelper().getFirstNameFromPage(),
		contact.getSecondName());
	bdata.setBday("21");
	contact.setBdata(bdata);
	contact.setGroup(group);
	app.getContactHelper().fillContactForm(contact);
	app.getContactHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();

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
