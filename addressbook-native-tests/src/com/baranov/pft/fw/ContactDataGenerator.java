package com.baranov.pft.fw;

import static com.baranov.pft.fw.DataGenerator.generateRandomString;

import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

	public static List<Contact> generateRandomContacts(int amount) {
		List<Contact> list = new ArrayList<Contact>();
		for (int i = 0; i < amount; i++) {
			Contact contact = new Contact().withFirstName(
					generateRandomString()).withSecondName(
					generateRandomString());
			list.add(contact);
		}
		return list;
	}
}
