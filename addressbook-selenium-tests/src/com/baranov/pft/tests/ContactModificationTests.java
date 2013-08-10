package com.baranov.pft.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void modifySomeContact() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getContactHelper().initGroupModification(1);
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
	app.getContactHelper().initGroupModification(1);
	ContactData contact = new ContactData();
	contact.setFirstName("NewView");
	contact.setSecondName("ContactDetails");
	app.getContactHelper().fillContactName(contact);
	app.getContactHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();
    }
}
