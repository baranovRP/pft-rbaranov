package com.baranov.pft.tests;

import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void deleteSomeGroup() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getNavigationHelper().gotoPage("groups");
	app.getGroupHelper().deleteGroup(1);
	app.getNavigationHelper().returntoGroupsPage();
    }
}
