package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class ContactAddTests extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void testContactCreationWithValidData(ContactData contact)
	    throws Exception {
	app.navigateTo().mainPage();

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	app.navigateTo().addNewPage();

	BirhtdayData bdata = contact.getBdata();
	String[] listBMonth = app.getContactHelper().getListMonths();
	int indexMonth = new Random().nextInt(listBMonth.length);
	bdata.setBmonth(listBMonth[indexMonth]);
	contact.setBdata(bdata);

	GroupData group = contact.getGroup();
	String[] listGroups = app.getContactHelper().getListGroups();
	int indexGroup = new Random().nextInt(listGroups.length);
	group.setGroupName(listGroups[indexGroup]);
	contact.setGroup(group);

	app.getContactHelper().fillContactForm(contact);
	app.getActionHelper().submitCreation();
	app.navigateTo().mainPage();

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }
}
