package com.baranov.pft.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.baranov.pft.tests.ContactData;
import com.baranov.pft.tests.GroupData;
import com.baranov.pft.utils.SortedListOf;

public class GroupHelper extends WebDriverHelperBase {

    public GroupHelper(ApplicationManager manager) {
	super(manager);
    }

    public GroupHelper createGroup(GroupData group) {
	returnToGroupsPage();
	initGroupCreation();
	fillGroupForm(group);
	submitGroupCreation();
	returnToGroupsPage();
	// update model
	manager.getGroupModel().addGroup(group);
	return this;
    }

    public GroupHelper modifyGroup(int index, GroupData group) {
	initGroupModification(index);
	fillGroupForm(group);
	submitGroupModification();
	returnToGroupsPage();
	manager.getGroupModel().removeGroup(index).addGroup(group);
	return this;
    }

    public GroupHelper deleteGroup(int index) {
	selectGroupByIndex(index);
	submitGroupDeletion();
	returnToGroupsPage();
	manager.getGroupModel().removeGroup(index);
	return this;
    }

    // ---------------------------------------------------------

    public SortedListOf<GroupData> getUiGroups() {
	SortedListOf<GroupData> groups = new SortedListOf<GroupData>();

	returnToGroupsPage();
	List<WebElement> checkboxes = findElements(By.name("selected[]"));
	for (WebElement checkbox : checkboxes) {
	    String groupName = extractTitle(checkbox, "title");
	    groups.add(new GroupData().withName(groupName));
	}
	return groups;
    }

    public GroupHelper fillGroupForm(GroupData group) {
	type(By.name("group_name"), group.getGroupName());
	type(By.name("group_header"), group.getHeader());
	type(By.name("group_footer"), group.getFooter());
	return this;
    }

    public GroupHelper fillGroupName(ContactData contact) {
	selectByText(By.name("new_group"), contact.getGroup().getGroupName());
	return this;
    }

    public GroupHelper initGroupCreation() {
	click(By.name("new"));
	return this;
    }

    public void selectGroupByIndex(int index) {
	click(By.xpath("//input[@name='selected[]'][" + (index + 1) + "]"));
    }

    public GroupHelper initGroupModification(int index) {
	selectGroupByIndex(index);
	click(By.name("edit"));
	return this;
    }

    public GroupHelper submitGroupCreation() {
	manager.getActionHelper().submitCreation();
	return this;
    }

    public GroupHelper submitGroupModification() {
	manager.getActionHelper().submitUpdate();
	return this;
    }

    public void submitGroupDeletion() {
	click(By.name("delete"));
    }

    public GroupHelper returnToGroupsPage() {
	manager.navigateTo().groupsPage();
	return this;
    }
}
