package com.baranov.pft.fw;

import org.openqa.selenium.By;

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
	click(By.xpath("//input[@name='selected[]'][" + index + "]"));
    }

    public void initGroupModification(int index) {
	selectGroupByIndex(index);
	click(By.name("edit"));
    }
}
