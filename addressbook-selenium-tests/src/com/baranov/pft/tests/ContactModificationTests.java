package com.baranov.pft.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void modifySomeContact() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getContactHelper().initContactModification(1);
	ContactData contact = new ContactData();
	contact.setFirstName("New");
	contact.setSecondName("Contact");
	app.getContactHelper().fillContactName(contact);
	app.getContactHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();
    }

    @Test
    public void modifySomeContactviaViewDetails() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getContactHelper().viewContact(1);
	app.getContactHelper().submitModify();
	ContactData contact = new ContactData();
	contact.setFirstName("NewView");
	contact.setSecondName("ContactDetails");
	app.getContactHelper().fillContactName(contact);
	app.getContactHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();
    }

    @Test
    public void modifySomeContactAddr() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getContactHelper().initContactModification(1);
	ContactData contact = new ContactData();
	BirhtdayData bdata = new BirhtdayData();
	GroupData group = new GroupData();
	ContactAddress contactAddr = new ContactAddress();
	contactAddr.setEmail("theNew@email.net");
	contact.setContactAddress(contactAddr);
	contact.setSecondName("modifySomeContactAddr");
	contact.setBdata(bdata);
	contact.setGroup(group);
	app.getContactHelper().fillContactForm(app, this, contact);
	app.getContactHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();
    }
}
