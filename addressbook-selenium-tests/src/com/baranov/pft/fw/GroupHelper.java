package com.baranov.pft.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.baranov.pft.tests.ContactData;
import com.baranov.pft.tests.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
	super(manager);
    }

    public void fillGroupForm(GroupData group) {
	type(By.name("group_name"), group.getGroupName());
	type(By.name("group_header"), group.getHeader());
	type(By.name("group_footer"), group.getFooter());
    }

    public void fillGroupName(ContactData contact) {
	selectByText(By.name("new_group"), contact.getGroup().getGroupName());
    }

    public void initGroupCreation() {
	click(By.name("new"));
    }

    public void deleteGroup(int index) {
	selectGroupByIndex(index);
	click(By.name("delete"));
    }

    private void selectGroupByIndex(int index) {
	click(By.xpath("//input[@name='selected[]'][" + (index + 1) + "]"));
    }

    public void initGroupModification(int index) {
	selectGroupByIndex(index);
	click(By.name("edit"));
    }

    public List<GroupData> getGroups() {
	List<GroupData> groups = new ArrayList<GroupData>();
	List<WebElement> checkboxes = findElements(By.name("selected[]"));
	for (WebElement checkbox : checkboxes) {
	    GroupData group = new GroupData();
	    group.setGroupName(extractTitle(checkbox, "title"));
	    groups.add(group);
	}
	return groups;
    }

}
