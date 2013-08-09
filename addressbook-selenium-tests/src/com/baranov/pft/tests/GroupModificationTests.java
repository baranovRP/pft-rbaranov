package com.baranov.pft.tests;

import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
    @Test
    public void modifySomeGroup() {
	app.getNavigationHelper().openMainPage("addressbookv4.1.4/");
	app.getNavigationHelper().gotoPage("groups");
	app.getGroupHelper().initGroupModification(1);
	GroupData group = new GroupData();
	group.setGroupName("new name");
	app.getGroupHelper().fillGroupForm(group);
	app.getActionHelper().submitUpdate();
	app.getNavigationHelper().returntoGroupsPage();
    }
}
