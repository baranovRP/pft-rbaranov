package com.baranov.pft.fw;

public class ContactHelper extends HelpersBase {

	public static boolean YES = true;
	public static boolean NO = false;

	public ContactHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void inputContactData(Contact contact, boolean desicion) {
		initContactCreation();
		fillContactForm(contact);
		if (desicion) {
			confirmContactCreation();
		} else {
			cancelContactCreation();
		}

	}

	public void createContact(Contact contact) {
		inputContactData(contact, YES);
	}

	public void cancelCreateContact(Contact contact) {
		inputContactData(contact, NO);
	}

	public void initContactCreation() {
		manager.getAutoItHelper().winWaitAndActivate("AddressBook Portable",
				"", 5000);
	}

	public void fillContactForm(Contact contact) {
		manager.getAutoItHelper().click("Add")
				.winWaitAndActivate("Add Contact", "", 5000)
				.send("TDBEdit12", contact.firstName)
				.send("TDBEdit11", contact.secondName);
	}

	public void confirmContactCreation() {
		manager.getAutoItHelper().click("Save")
				.winWaitAndActivate("AddressBook Portable", "", 5000);
	}

	public void cancelContactCreation() {
		manager.getAutoItHelper().click("Cancel")
				.winWaitAndActivate("AddressBook Portable", "", 5000);
	}

	public Contact getFirstContact() {
		selectFirstElement();
		Contact contact = getContactData();
		cancelContactCreation();
		return contact;
	}

	public void selectFirstElement() {
		manager.getAutoItHelper()
				.winWaitAndActivate("AddressBook Portable", "", 5000)
				.click("TListView1").send("{DOWN}{SPACE}").click("Edit")
				.winWaitAndActivate("Update Contact", "", 5000);
	}

	public Contact getContactData() {
		Contact contact = new Contact().setFirstName(
				manager.getAutoItHelper().getText("TDBEdit12")).setSecondName(
				manager.getAutoItHelper().getText("TDBEdit11"));
		return contact;
	}

}
