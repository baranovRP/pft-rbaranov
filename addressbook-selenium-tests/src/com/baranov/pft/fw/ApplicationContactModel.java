package com.baranov.pft.fw;

import java.util.List;

import com.baranov.pft.tests.ContactData;
import com.baranov.pft.utils.SortedListOf;

public class ApplicationContactModel extends ApplicationGroupModel {
    private SortedListOf<ContactData> contacts;

    public SortedListOf<ContactData> getContacts() {
	return contacts;
    }

    public void setContacts(List<ContactData> contacts) {
	this.contacts = new SortedListOf<ContactData>(contacts);
    }

    public void addContact(ContactData contact) {
	contacts.add(contact);
    }

    public ApplicationContactModel removeContact(int index) {
	contacts.remove(index);
	return this;
    }
}
