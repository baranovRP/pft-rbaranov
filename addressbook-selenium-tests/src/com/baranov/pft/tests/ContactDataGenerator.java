package com.baranov.pft.tests;

import static com.baranov.pft.tests.DataGenerator.generateRandomString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.baranov.pft.fw.ApplicationManager;
import com.thoughtworks.xstream.XStream;

public class ContactDataGenerator {
    public static ApplicationManager app;

    public static void main(String[] args) throws IOException {
	if (args.length < 3) {
	    System.out
		    .println("Please specify parameters: <amount of test data> <file> <format>");
	    return;
	}
	int amount = Integer.parseInt(args[0]);
	File file = new File(args[1]);
	String format = args[2];

	if (file.exists()) {
	    System.out.printf("File %s exists, please remove it manually.",
		    file);
	    return;
	}
	List<ContactData> groups = generateRandomContacts(amount);
	if ("csv".equals(format)) {
	    saveContactsToCsvFile(groups, file);
	} else if ("xml".equals(format)) {
	    saveContactsToXmlFile(groups, file);
	} else {
	    System.out.println("Unknown format " + format);
	}
    }

    private static void saveContactsToXmlFile(List<ContactData> contacts,
	    File file) throws IOException {
	XStream xstream = new XStream();
	xstream.alias("contact", ContactData.class);
	String xml = xstream.toXML(contacts);
	FileWriter writer = new FileWriter(file);
	writer.write(xml);
	writer.close();
    }

    public static List<ContactData> loadContactsFromXmlFile(File file) {
	XStream xstream = new XStream();
	xstream.alias("contact", ContactData.class);
	return (List<ContactData>) xstream.fromXML(file);
    }

    private static void saveContactsToCsvFile(List<ContactData> contacts,
	    File file) throws IOException {
	FileWriter writer = new FileWriter(file);
	for (ContactData contact : contacts) {
	    writer.write(contact.getFirstName() + "," + contact.getSecondName()
		    + "," + contact.getContactAddress().getAddress() + ","
		    + contact.getContactAddress().getHomePhone() + ","
		    + contact.getContactAddress().getMobilePhone() + ","
		    + contact.getContactAddress().getWorkPhone() + ","
		    + contact.getContactAddress().getEmail() + ","
		    + contact.getContactAddress().getEmail2() + ","
		    + contact.getBdata().getBday() + ","
		    + contact.getBdata().getBmonth() + ","
		    + contact.getBdata().getByear() + ","
		    + contact.getGroup().getGroupName() + ","
		    + contact.getContactAddress().getAddress2() + ","
		    + contact.getContactAddress().getPhone2() + ",!" + "\n");
	}
	writer.close();
    }

    public static List<ContactData> loadContactsFromCsvFile(File file)
	    throws IOException {
	List<ContactData> list = new ArrayList<ContactData>();
	FileReader reader = new FileReader(file);
	BufferedReader bufferedReader = new BufferedReader(reader);
	String line = bufferedReader.readLine();
	while (line != null) {
	    String[] part = line.split(",");
	    ContactAddress contactAddress = new ContactAddress()
		    .withAddress(part[2]).withHomePhone(part[3])
		    .withMobilePhone(part[4]).withWorkPhone(part[5])
		    .withEmail(part[6]).withEmail2(part[7])
		    .withAddress2(part[12]).withPhone2(part[13]);
	    BirhtdayData bdata = new BirhtdayData().withBDay(part[8])
		    .withBMonth(part[9]).withBYear(part[10]);
	    GroupData group = new GroupData().withName(part[11]);
	    ContactData contact = new ContactData().withFirstName(part[0])
		    .withSecondName(part[1]).withContactAddress(contactAddress)
		    .withBData(bdata).withGroup(group).withFullName();
	    list.add(contact);
	    line = bufferedReader.readLine();
	}
	bufferedReader.close();
	return list;
    }

    public static List<ContactData> generateRandomContacts(int amount) {
	List<ContactData> list = new ArrayList<ContactData>();
	for (int i = 0; i < amount; i++) {
	    ContactData contact = new ContactData()
		    .withFirstName(generateRandomString())
		    .withSecondName(generateRandomString()).withFullName()
		    .withContactAddress(generateRandomAddress())
		    .withBData(generateRandomData())
		    .withGroup(generateRandomGroup());
	    list.add(contact);
	}
	// ...
	// app.navigateTo().mainPage();
	return list;
    }

    public static ContactAddress generateRandomAddress() {
	ContactAddress address = new ContactAddress()
		.withAddress(generateRandomString())
		.withHomePhone(generateRandomString())
		.withMobilePhone(generateRandomString())
		.withWorkPhone(generateRandomString())
		.withEmail(generateRandomString())
		.withEmail2(generateRandomString())
		.withAddress2(generateRandomString())
		.withPhone2(generateRandomString());
	return address;
    }

    public static BirhtdayData generateRandomData() {
	Random rnd = new Random();
	BirhtdayData bdata = new BirhtdayData()
		.withBYear(Integer.toString(rnd.nextInt(10000)))
		.withBMonth(getRandomMonth())
		.withBDay(getRandomDay(rnd.nextInt(32)));
	return bdata;
    }

    public static GroupData generateRandomGroup() {
	String group = "[none]";
	return new GroupData(group);
    }

    public static String getRandomMonth() {
	String[] listMonths = { "-", "January", "February", "March", "April",
		"May", "June", "July", "August", "September", "October",
		"November", "December" };
	return listMonths[new Random().nextInt(listMonths.length)];
    }

    public static String getRandomDay(int data) {
	String bdata = "-";
	if (data != 0) {
	    bdata = Integer.toString(data);
	}
	return bdata;
    }

}
