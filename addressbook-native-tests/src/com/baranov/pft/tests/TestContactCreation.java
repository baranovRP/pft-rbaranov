package com.baranov.pft.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baranov.pft.fw.Contact;

public class TestContactCreation extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void shouldCreateContactWithValidData(Contact contact) {
		app.getContactHelper().createContact(contact);
		Contact createdContact = app.getContactHelper().getFirstContact();
		Assert.assertEquals(contact, createdContact);
	}

	@Test(dataProvider = "randomValidContactGenerator")
	public void shouldNotCreateContact(Contact contact) {
		app.getContactHelper().cancelCreateContact(contact);
		Contact createdContact = app.getContactHelper().getFirstContact();
		Assert.assertNotEquals(contact, createdContact);
	}
}
