package com.baranov.pft01;

import org.testng.annotations.Test;

public class AlsoGroupCreationTests extends TestBase {
    @Test
    public void testNoneEmptyGroupCreation() throws Exception {
	openMainPage("/addressbookv4.1.4/");
	gotoPage("groups");
	initGroupCreation();
	GroupData group = new GroupData();
	group.setGroupName("group name 1");
	group.setHeader("header 1");
	group.setFooter("footer 1");
	fillGroupForm(group);
	submitCreation();
	returntoGroupsPage();
    }

    @Test
    public void testEmptyGroupCreation() throws Exception {
	openMainPage("/addressbookv4.1.4/");
	gotoPage("groups");
	initGroupCreation();
	fillGroupForm(new GroupData("", "", ""));
	submitCreation();
	returntoGroupsPage();
    }
}
