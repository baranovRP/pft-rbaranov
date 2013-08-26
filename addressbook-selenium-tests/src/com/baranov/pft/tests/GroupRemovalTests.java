package com.baranov.pft.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.baranov.pft.utils.SortedListOf;

public class GroupRemovalTests extends TestBase {

    @Test
    public void deleteSomeGroup() {
	// save old state
	SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();

	int index = getRandomIndex(oldList);

	// actions
	app.getGroupHelper().deleteGroup(index);

	// save new state
	SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();

	// compare states
	assertThat(newList, equalTo(oldList.without(index)));
    }
}
