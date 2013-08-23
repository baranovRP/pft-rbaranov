package com.baranov.pft.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void modifySomeGroup(GroupData group) {
	// save old state
	List<GroupData> oldList = app.getGroupHelper().getGroups();

	int index = getRandomIndex(oldList);

	// actions
	app.getGroupHelper().modifyGroup(index, group);

	// save new state
	List<GroupData> newList = app.getGroupHelper().getGroups();

	// compare states
	oldList.remove(index);
	oldList.add(group);
	Collections.sort(oldList);
	Collections.sort(newList);
	assertEquals(newList, oldList);
    }
}
