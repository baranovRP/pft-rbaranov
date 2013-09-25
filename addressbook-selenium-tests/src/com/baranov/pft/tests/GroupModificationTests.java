package com.baranov.pft.tests;

import static com.baranov.pft.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baranov.pft.utils.SortedListOf;

public class GroupModificationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> groupsFromFile() throws IOException {
	return wrapGroupsForDataProvider(
		loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
    }

    @Test(dataProvider = "groupsFromFile")
    public void modifySomeGroup(GroupData group) {
	// save old state
	SortedListOf<GroupData> oldList = app.getGroupHelper().getUiGroups();

	int index = getRandomIndex(oldList.size());

	// actions
	app.getGroupHelper().modifyGroup(index, group);

	// save new state
	SortedListOf<GroupData> newList = app.getGroupHelper().getUiGroups();

	// compare states
	assertThat(newList, equalTo(oldList.without(index).withAdded(group)));
    }
}
