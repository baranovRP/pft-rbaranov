package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactAddTests extends TestBase {

    @Test
    public void testNoneEmptyContact() throws Exception {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	app.getNavigationHelper().gotoPage("add new");
	GroupData groupdata = new GroupData("group 1");
	BirhtdayData bdata = new BirhtdayData("2", "January", "1902");
	ContactAddress contactAddress = new ContactAddress(
		"82 Woodbine Cir \n\nNeedham, MA(Massachusetts)",
		"(781) 444-2222", "(781) 444-1111", "(781) 444-3333",
		"iivanov@iv.com", "ivanivanov@mail.com",
		"5234 Locust St\nPhiladelphia, PA", "(215) 528-2134");

	ContactData contact = new ContactData("Petr", "Petrov", contactAddress,
		bdata, groupdata);
	app.getContactHelper().fillContactForm(contact);
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoHomePage();

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

    }

    @Test
    public void testEmptyContact() throws Exception {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");

	// save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();

	app.getNavigationHelper().gotoPage("add new");
	// combobox "new_group" uses value "[none]" in a case of empty value
	GroupData groupdata = new GroupData("[none]");
	/*
	 * comboboxs "bday" and "bmonth" don't allow put empty value. range for
	 * bday (-, 1-31), range for bmonth (-, "January" - "December")
	 */
	BirhtdayData bdata = new BirhtdayData("-", "-", "");
	ContactAddress contactAddress = new ContactAddress("", "", "", "", "",
		"", "", "");
	ContactData contact = new ContactData("", "", contactAddress, bdata,
		groupdata);
	app.getContactHelper().fillContactForm(contact);
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoHomePage();

	// save new state
	List<ContactData> newList = app.getContactHelper().getContacts();

	// compare states
	oldList.add(contact);
	Collections.sort(newList);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }
}
