package com.baranov.pft.tests;

import org.testng.annotations.Test;

public class AlsoGroupCreationTests extends TestBase {
    @Test
    public void testNoneEmptyGroupCreation() throws Exception {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getNavigationHelper().gotoPage("groups");
	app.getGroupHelper().initGroupCreation();
	GroupData group = new GroupData();
	group.setGroupName("group name 1");
	group.setHeader("header 1");
	group.setFooter("footer 1");
	app.getGroupHelper().fillGroupForm(group);
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoGroupsPage();
    }

    @Test
    public void testEmptyGroupCreation() throws Exception {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getNavigationHelper().gotoPage("groups");
	app.getGroupHelper().initGroupCreation();
	app.getGroupHelper().fillGroupForm(new GroupData("", "", ""));
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoGroupsPage();
    }
}
