package com.baranov.pft.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.baranov.pft.tests.ContactAddress;
import com.baranov.pft.tests.ContactData;
import com.baranov.pft.utils.SortedListOf;

public class ContactHelper extends HelperBase {

    public static boolean CREATION = true;
    public static boolean MODIFICATION = false;

    public ContactHelper(ApplicationManager manager) {
	super(manager);
    }

    private SortedListOf<ContactData> cachedContacts;

    public SortedListOf<ContactData> getContacts() {
	if (cachedContacts == null) {
	    rebuildCache();
	}
	return cachedContacts;
    }

    private void rebuildCache() {
	cachedContacts = new SortedListOf<ContactData>();

	manager.navigateTo().mainPage();
	List<WebElement> td = findElements(By
		.xpath("//*[@id='maintable']//tr[@name='entry']"));
	for (WebElement webElement : td) {
	    ContactData contact = extractShortContact(webElement);
	    cachedContacts.add(contact);
	}
    }

    public ContactHelper createContact(ContactData contact) {
	manager.navigateTo().addNewPage();
	fillContactForm(contact, CREATION);
	submitCreation();
	manager.navigateTo().mainPage();
	rebuildCache();
	return this;
    }

    public ContactHelper modifyContact(int index, ContactData contact) {
	manager.navigateTo().mainPage();
	editContactByIndex(index);
	fillContactForm(contact, MODIFICATION);
	submitUpdate();
	manager.navigateTo().mainPage();
	rebuildCache();
	return this;
    }

    public ContactHelper deleteContact(int index) {
	manager.navigateTo().mainPage();
	editContactByIndex(index);
	submitDelete();
	manager.navigateTo().mainPage();
	rebuildCache();
	return this;
    }

    // ---------------------------------------------------------------------------

    public ContactHelper fillContactForm(ContactData contact, boolean formType) {
	fillContactName(contact);
	fillContactAddress(contact);
	fillBirthday(contact);
	if (formType == CREATION) {
	    fillGroupName(contact);
	} else {
	    if (driver.findElements(By.name("new_group")).size() != 0) {
		throw new Error(
			"Group selector exists in contact modification form");
	    }
	}
	return this;
    }

    public ContactData extractShortContact(WebElement webElement) {
	String firstName = extractData(webElement, (By.xpath("./td[3]")));
	String secondName = extractData(webElement, (By.xpath("./td[2]")));
	String email = extractData(webElement, (By.xpath("./td/a")));
	String phone = extractData(webElement, (By.xpath("./td[5]")));
	ContactAddress address = new ContactAddress().withEmail(email)
		.withHomePhone(phone);
	ContactData contact = new ContactData().withFirstName(firstName)
		.withSecondName(secondName).withContactAddress(address)
		.withFullName();
	return contact;
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

    public ContactHelper initContactModification(int index) {
	editContactByIndex(index);
	return this;
    }

    public ContactHelper submitCreation() {
	manager.getActionHelper().submitCreation();
	cachedContacts = null;
	return this;
    }

    public ContactHelper submitUpdate() {
	click(By.xpath("//*[@id='content']//*[@value='Update']"));
	cachedContacts = null;
	return this;
    }

    public ContactHelper submitDelete() {
	click(By.xpath("//*[@id='content']//*[@value='Delete']"));
	cachedContacts = null;
	return this;
    }

    public void viewContact(int index) {
	detailsContactByIndex(index);
    }

    public ContactHelper submitModify() {
	click(By.xpath("//*[@value='Modify']"));
	cachedContacts = null;
	return this;
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

    public String getFirstNameFromPage() {
	return findElement(By.name("firstname")).getAttribute("value");
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
	String[] listGroups = new String[values.size()];
	for (WebElement webElement : values) {
	    listGroups[iter] = extractGroup(webElement);
	    iter++;
	}
	return listGroups;
    }

}
