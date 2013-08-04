package com.baranov.pft01;

import org.testng.annotations.Test;

public class ContactAddTest extends TestBase {
    @Test
    public void testNoneEmptyContact() throws Exception {
	openMainPage("/addressbookv4.1.4/");
	gotoPage("add new");
	GroupData groupdata = new GroupData("group 1");
	BirhtdayData bdata = new BirhtdayData("2", "January", "1902");
	ContactAddress contactAddress = new ContactAddress(
		"82 Woodbine Cir \n\nNeedham, MA(Massachusetts)",
		"(781) 444-2222", "(781) 444-1111", "(781) 444-3333",
		"iivanov@iv.com", "ivanivanov@mail.com",
		"5234 Locust St\nPhiladelphia, PA", "(215) 528-2134");
	fillContactForm(new ContactData("Petr", "Petrov", contactAddress,
		bdata, groupdata));
	submitCreation();
	returntoHomePage();
    }

    @Test
    public void testEmptyContact() throws Exception {
	openMainPage("/addressbookv4.1.4/");
	gotoPage("add new");
	GroupData groupdata = new GroupData("");
	/*
	 * comboboxs "bday" and "bmonth" don't allow put empty value. range for
	 * bday (-, 1-31), range for bmonth (-, "January" - "December")
	 */
	BirhtdayData bdata = new BirhtdayData("-", "-", "");
	ContactAddress contactAddress = new ContactAddress("", "", "", "", "",
		"", "", "");
	fillContactForm(new ContactData("", "", contactAddress, bdata,
		groupdata));
	submitCreation();
	returntoHomePage();
    }
}
