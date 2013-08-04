package com.baranov.pft01;

import org.testng.annotations.Test;

public class AlsoGroupCreationTests extends TestBase {
    @Test
    public void testNoneEmptyGroupCreation() throws Exception {
        openMainPage();
        gotoGroupsPage();
        initGroupCreation();
        GroupData group = new GroupData();
        group.setGroupname("group name 1");
        group.setHeader("header 1");
        group.setFooter("footer 1");
        fillGroupForm(group);
        submitGroupCreation();
        returntoGroupsPage();
    }

    @Test
    public void testEmptyGroupCreation() throws Exception {
        openMainPage();
        gotoGroupsPage();
        initGroupCreation();
        fillGroupForm(new GroupData("", "", ""));
        submitGroupCreation();
        returntoGroupsPage();
    }
}
