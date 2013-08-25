package com.baranov.pft.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.baranov.pft.fw.ApplicationManager;

public class TestBase {
    public static ApplicationManager app;

    @BeforeTest
    public void setUp() throws Exception {
	app = new ApplicationManager();
    }

    @AfterTest
    public void tearDown() throws Exception {
	app.stop();
    }

    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() {
	List<Object[]> list = new ArrayList<Object[]>();
	for (int i = 0; i < 5; i++) {
	    GroupData group = new GroupData().withName(generateRandomString())
		    .withHeader(generateRandomString())
		    .withFooter(generateRandomString());
	    list.add(new Object[] { group });
	}
	// ...
	return list.iterator();
    }

    public String generateRandomString() {
	Random rnd = new Random();
	String rString;
	if (rnd.nextInt(3) == 0) {
	    rString = "";
	} else {
	    /*
	     * time to time is generated white spaces at the begin or at the end
	     * of random string. Web apps cuts this white spaces and it's the
	     * reason of error during comparison
	     */
	    rString = generateRandomSequence().trim();
	}
	return rString;
    }

    private String generateRandomSequence() {
	Random rnd = new Random();
	// according to ASCII 32 is space and 126 is ~
	int max = 90;
	int min = 65;
	int length = rnd.nextInt(20); // specify desire length of string
	char buf[] = new char[length];
	for (int i = 0; i < buf.length; i++) {
	    char symbol = (char) (rnd.nextInt((max + 1) - min) + min);
	    // symbols ' and \ is lead to error during write to DB, symbol
	    // doesn't write into group name
	    if (isValidCharacter(symbol)) {
		buf[i] = symbol;
	    }
	}
	return new String(buf);
    }

    private boolean isValidCharacter(char symbol) {
	char[] invalidChars = { '\'', '\\', '<', '>', '_', '`' };
	boolean isValid = true;
	for (char c : invalidChars) {
	    if (c == symbol) {
		isValid = false;
	    }
	}
	return isValid;
    }

    public <E> int getRandomIndex(List<E> oldList) {
	return new Random().nextInt(oldList.size() - 1);
    }

    @DataProvider
    public Iterator<Object[]> randomValidContactGenerator() {
	app.navigateTo().addNewPage();
	List<Object[]> list = new ArrayList<Object[]>();
	for (int i = 0; i < 5; i++) {
	    ContactData contact = new ContactData()
		    .withFirstName(generateRandomString())
		    .withSecondName(generateRandomString()).withFullName()
		    .withContactAddress(generateRandomAddress())
		    .withBData(generateRandomData())
		    .withGroup(generateRandomGroup());
	    list.add(new Object[] { contact });
	}
	// ...
	app.navigateTo().mainPage();
	return list.iterator();
    }

    public BirhtdayData generateRandomData() {
	Random rnd = new Random();
	BirhtdayData bdata = new BirhtdayData()
		.withBYear(Integer.toString(rnd.nextInt(10000)))
		.withBMonth(getRandomMonth())
		.withBDay(getRandomDay(rnd.nextInt(32)));
	return bdata;
    }

    public String getRandomMonth() {
	String[] listMonths = app.getContactHelper().getListMonths();
	return listMonths[new Random().nextInt(listMonths.length)];
    }

    public String getRandomDay(int data) {
	String bdata = "-";
	if (data != 0) {
	    bdata = Integer.toString(data);
	}
	return bdata;
    }

    public GroupData generateRandomGroup() {
	String[] listGroups = app.getContactHelper().getListGroups();
	return new GroupData(
		listGroups[new Random().nextInt(listGroups.length)]);
    }

    public ContactAddress generateRandomAddress() {
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

}
