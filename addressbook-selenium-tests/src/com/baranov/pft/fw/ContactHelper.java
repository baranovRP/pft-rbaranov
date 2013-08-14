package com.baranov.pft.fw;

import org.openqa.selenium.By;

import com.baranov.pft.tests.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
	super(manager);
    }

    public void fillContactForm(ContactData contact) {
	fillContactAddress(contact);
	fillBirthday(contact);
	fillGroupName(contact);
    }

    public void fillGroupName(ContactData contact) {
	selectByText(By.name("new_group"), contact.getGroup().getGroupName());
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
	editContactByIndex(index);
	submitDelete();
    }

    private void submitDelete() {
	click(By.xpath("//*[@id='content']//*[@value='Delete']"));
    }

    public void initContactModification(int index) {
	editContactByIndex(index);
    }

    public void viewContact(int index) {
	detailsContactByIndex(index);
    }

    public void submitUpdate() {
	click(By.xpath("//*[@id='content']//*[@value='Update']"));
    }

    public void submitModify() {
	click(By.xpath("//*[@value='Modify']"));
    }

    private void editContactByIndex(int index) {
	// The first line it's table's header.
	// That's the reason why we add 1 to index
	int correctIndex = 1 + index;
	click(By.xpath("//*[@id='maintable']/tbody/tr[" + correctIndex
		+ "]/td[7]/a/img"));
    }

    private void detailsContactByIndex(int index) {
	// The first line it's table's header.
	// That's the reason why we add 1 to index
	int correctIndex = 1 + index;
	click(By.xpath("//*[@id='maintable']/tbody/tr[" + correctIndex
		+ "]/td[6]/a/img"));
    }
}
