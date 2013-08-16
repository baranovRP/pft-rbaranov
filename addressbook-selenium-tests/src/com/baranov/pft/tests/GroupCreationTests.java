package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
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
	group.setGroupName("AAA");
	group.setHeader("header 1");
	group.setFooter("footer 1");
	app.getGroupHelper().fillGroupForm(group);
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoGroupsPage();

	// save new state
	List<GroupData> newList = app.getGroupHelper().getGroups();

	// compare states
	oldList.add(group);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

    }

    @Test
    public void testEmptyGroupCreation() throws Exception {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getNavigationHelper().gotoPage("groups");

	// save old state
	List<GroupData> oldList = app.getGroupHelper().getGroups();

	// actions
	GroupData group = new GroupData("", "", "");
	app.getGroupHelper().initGroupCreation();
	app.getGroupHelper().fillGroupForm(group);
	app.getActionHelper().submitCreation();
	app.getNavigationHelper().returntoGroupsPage();

	// save new state
	List<GroupData> newList = app.getGroupHelper().getGroups();

	// compare states
	oldList.add(group);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }
}
