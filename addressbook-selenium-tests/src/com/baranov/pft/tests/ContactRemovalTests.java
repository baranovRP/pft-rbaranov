package com.baranov.pft.tests;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void deleteSomeContact() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getContactHelper().deleteContact(1);
	// app.getActionHelper().submitUpdate();
	app.getNavigationHelper().returntoHomePage();
    }
}
