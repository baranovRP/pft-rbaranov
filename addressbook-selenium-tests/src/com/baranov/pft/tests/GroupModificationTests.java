package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
    @Test
    public void modifySomeGroup() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getNavigationHelper().gotoPage("groups");

	// save old state
	List<GroupData> oldList = app.getGroupHelper().getGroups();

	// actions
	app.getGroupHelper().initGroupModification(0);
	GroupData group = new GroupData();
	group.setGroupName("new name");
	app.getGroupHelper().fillGroupForm(group);
	app.getActionHelper().submitUpdate();
	app.getNavigationHelper().returntoGroupsPage();

	// save new state
	List<GroupData> newList = app.getGroupHelper().getGroups();

	// compare states
	oldList.remove(0);
	oldList.add(group);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
    }
}
