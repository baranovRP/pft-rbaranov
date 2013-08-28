package com.baranov.pft.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.baranov.pft.utils.SortedListOf;

public class GroupModificationTests extends TestBase {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void modifySomeGroup(GroupData group) {
	// save old state
	SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();

	int index = getRandomIndex(oldList.size());

	// actions
	app.getGroupHelper().modifyGroup(index, group);

	// save new state
	SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();

	// compare states
	assertThat(newList, equalTo(oldList.without(index).withAdded(group)));
    }
}
