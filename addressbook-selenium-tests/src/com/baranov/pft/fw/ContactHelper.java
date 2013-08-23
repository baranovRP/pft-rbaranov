package com.baranov.pft.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.baranov.pft.tests.ContactData;

public class ContactHelper extends HelperBase {

    public static boolean CREATION = true;
    public static boolean MODIFICATION = true;

    public ContactHelper(ApplicationManager manager) {
	super(manager);
    }

    public void fillContactForm(ContactData contact) {
	fillContactName(contact);
	fillContactAddress(contact);
	fillBirthday(contact);
	fillGroupName(contact);
    }

    // field 'group' is absent on modification form
    public void modifyContactForm(ContactData contact) {
	fillContactName(contact);
	fillContactAddress(contact);
	fillBirthday(contact);
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
	// Other 1 we add due to array in java begins from zero
	int correctIndex = 2 + index;
	click(By.xpath("//*[@id='maintable']/tbody/tr[" + correctIndex
		+ "]/td[7]/a/img"));
    }

    private void detailsContactByIndex(int index) {
	// The first line it's table's header.
	// That's the reason why we add 1 to index
	// Other 1 we add due to array in java begins from zero
	int correctIndex = 2 + index;
	click(By.xpath("//*[@id='maintable']/tbody/tr[" + correctIndex
		+ "]/td[6]/a/img"));
    }

    public List<ContactData> getContacts() {
	List<ContactData> contacts = new ArrayList<ContactData>();
	List<WebElement> checkboxes = findElements(By.name("selected[]"));
	for (WebElement checkbox : checkboxes) {
	    ContactData contact = new ContactData();
	    contact.setFullName(extractTitle(checkbox, "title"));
	    contacts.add(contact);
	}
	return contacts;
    }

    public String getFirstNameFromPage() {
	String firstName;
	WebElement textField = findElement(By.name("firstname"));
	firstName = textField.getAttribute("value");
	return firstName;
    }

    public String[] getListMonths() {
	int iter = 0;
	List<WebElement> values = findElements(By
		.xpath("//*[@name='bmonth']/option"));
	String[] listMonths = new String[values.size()];
	for (WebElement webElement : values) {
	    listMonths[iter] = extractValue(webElement, "value");
	    iter++;
	}
	return listMonths;
    }

    public String[] getListGroups() {
	int iter = 0;
	List<WebElement> values = findElements(By
		.xpath("//*[@name='new_group']/option"));
	String[] listMonths = new String[values.size()];
	for (WebElement webElement : values) {
	    listMonths[iter] = extractGroup(webElement);
	    iter++;
	}
	return listMonths;
    }
}
