package com.baranov.pft.fw;

import org.openqa.selenium.By;

import com.baranov.pft.tests.ContactData;
import com.baranov.pft.tests.TestBase;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
	super(manager);
    }

    public void fillContactForm(ApplicationManager applicationManager,
	    TestBase testBase, ContactData contact) {
	testBase.app.getContactHelper().fillContactAddress(contact);
	testBase.app.getContactHelper().fillBirthday(contact);
	testBase.app.getGroupHelper().fillGroupName(contact);
    }

    public void fillContactName(ContactData contact) {
	type(By.name("firstname"), contact.getFirstName());
	type(By.name("lastname"), contact.getSecondName());
    }

    public void fillContactAddress(ContactData contact) {
	type(By.name("address"), contact.getContactAddress().getAddress());
	type(By.name("home"), contact.getContactAddress().getHomePhone());
	type(By.name("mobile"), contact.getContactAddress().getMobilePhone());
	type(By.name("work"), contact.getContactAddress().getWorkPhone());
	type(By.name("email"), contact.getContactAddress().getEmail());
	type(By.name("email2"), contact.getContactAddress().getEmail2());
	type(By.name("address2"), contact.getContactAddress().getAddress2());
	type(By.name("phone2"), contact.getContactAddress().getPhone2());
    }

    public void fillBirthday(ContactData contact) {
	selectByText(By.name("bday"), contact.getBdata().getBday());
	selectByText(By.name("bmonth"), contact.getBdata().getBmonth());
	type(By.name("byear"), contact.getBdata().getByear());
    }

    public void deleteContact(int index) {
	selectContactByIndex(index);
	click(By.xpath("//*[@id='content']//*[@value='Delete']"));
    }

    private void selectContactByIndex(int index) {
	// The first line it's table's header.
	// That's the reason why we add 1 to index
	int correctIndex = 1 + index;
	click(By.xpath("//*[@id='maintable']/tbody/tr[" + correctIndex
		+ "]/td[7]/a/img"));
    }

    public void initGroupModification(int index) {
	selectContactByIndex(index);
    }

    public void submitUpdate() {
	click(By.xpath("//*[@id='content']//*[@value='Update']"));
    }

}
