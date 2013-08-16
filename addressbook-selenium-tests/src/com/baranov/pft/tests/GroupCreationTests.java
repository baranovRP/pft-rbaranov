package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
    @Test
    public void testNoneEmptyGroupCreation() throws Exception {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getNavigationHelper().gotoPage("groups");

	// save old state
	List<GroupData> oldList = app.getGroupHelper().getGroups();

	// actions

	app.getGroupHelper().initGroupCreation();
	GroupData group = new GroupData();
	group.setGroupName("group name 1");
	group.setHeader("header 1");
	group.setFooter("footer 1");
	app.getGroupHelper().fillGroupForm(group);
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoGroupsPage();

	// save new state
	List<GroupData> newList = app.getGroupHelper().getGroups();

	// compare states
	assertEquals(newList.size(), oldList.size() + 1);

    }

    // @Test
    public void testEmptyGroupCreation() throws Exception {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getNavigationHelper().gotoPage("groups");
	app.getGroupHelper().initGroupCreation();
	app.getGroupHelper().fillGroupForm(new GroupData("", "", ""));
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoGroupsPage();
    }
}
