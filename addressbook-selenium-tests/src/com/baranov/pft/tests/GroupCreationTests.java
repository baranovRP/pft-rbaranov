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

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> groupsFromFile() throws IOException {
	return wrapGroupsForDataProvider(
		loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
    }

    @Test(dataProvider = "groupsFromFile")
    public void testGroupCreationWithValidData(GroupData group)
	    throws Exception {
	// save old state
	SortedListOf<GroupData> oldList = app.getGroupHelper().getUiGroups();

	// actions
	app.getGroupHelper().createGroup(group);

	// save new state
	SortedListOf<GroupData> newList = app.getModel().getGroups();
	// compare state
	assertThat(newList, equalTo(oldList.withAdded(group)));

	if (wantToCheck()) {
	    if ("yes".equals(app.getProperty("check.db"))) {
		assertThat(app.getModel().getGroups(), equalTo(app
			.getHibernateHelper().listGroups()));
	    }
	    if ("yes".equals(app.getProperty("check.ui"))) {
		assertThat(app.getModel().getGroups(), equalTo(app
			.getGroupHelper().getUiGroups()));
	    }
	}
    }
}
