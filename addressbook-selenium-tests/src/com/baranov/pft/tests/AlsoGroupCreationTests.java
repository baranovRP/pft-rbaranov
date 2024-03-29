package com.baranov.pft.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.baranov.pft.utils.SortedListOf;

public class AlsoGroupCreationTests extends TestBase {

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testGroupCreationWithValidData(GroupData group)
	    throws Exception {
	// save old state
	SortedListOf<GroupData> oldList = app.getGroupHelper().getUiGroups();

	// actions
	app.getGroupHelper().createGroup(group);

	// save new state
	SortedListOf<GroupData> newList = app.getGroupHelper().getUiGroups();

	// compare state
	assertThat(newList, equalTo(oldList.withAdded(group)));
    }
}
