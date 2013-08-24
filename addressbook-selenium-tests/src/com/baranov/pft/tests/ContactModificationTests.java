package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void modifySomeContactWithValidData(ContactData contact) {
	app.navigateTo().mainPage();

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	int index = getRandomIndex(oldList);

	app.getContactHelper().initContactModification(index);

	BirhtdayData bdata = contact.getBdata();
	String[] listBMonth = app.getContactHelper().getListMonths();
	int indexMonth = new Random().nextInt(listBMonth.length);
	bdata.setBmonth(listBMonth[indexMonth]);
	contact.setBdata(bdata);

	app.getContactHelper().modifyContactForm(contact);
	app.getContactHelper().submitUpdate();
	// app.navigateTo().returntoHomePage();
	app.navigateTo().mainPage();

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
