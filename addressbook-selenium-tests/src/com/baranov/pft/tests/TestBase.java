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
	int max = 126;
	int min = 32;
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
	char[] invalidChars = { '\'', '\\', '<', '_' };
	boolean isValid = true;
	for (char c : invalidChars) {
	    if (c == symbol) {
		isValid = false;
	    }
	}

	return isValid;
    }

    public <E> int getRandomIndex(List<E> oldList) {
	Random rnd = new Random();
	int index = rnd.nextInt(oldList.size() - 1);
	return index;
    }

    @DataProvider
    public Iterator<Object[]> randomValidContactGenerator() {
	List<Object[]> list = new ArrayList<Object[]>();
	app.navigateTo().addNewPage();
	for (int i = 0; i < 5; i++) {
	    ContactData contact = new ContactData();
	    contact.setFirstName(generateRandomString());
	    contact.setSecondName(generateRandomString());
	    contact.setFullName(contact.getFirstName(), contact.getSecondName());
	    contact.setContactAddress(generateRandomAddress());
	    contact.setBdata(generateRandomData());
	    contact.setGroup(generateRandomGroup());
	    list.add(new Object[] { contact });
	}
	// ...
	app.navigateTo().mainPage();
	return list.iterator();
    }

    public BirhtdayData generateRandomData() {
	BirhtdayData bdata = new BirhtdayData();
	Random rnd = new Random();
	bdata.setByear(Integer.toString(rnd.nextInt(10000)));
	bdata.setBmonth(getRandomMonth());
	bdata.setBday(getRandomDay(rnd.nextInt(32)));
	return bdata;
    }

    public String getRandomMonth() {
	String[] listMonths = app.getContactHelper().getListMonths();
	int indexMonth = new Random().nextInt(listMonths.length);
	return listMonths[indexMonth];
    }

    public String getRandomDay(int data) {
	String bdata = "-";
	if (data != 0) {
	    bdata = Integer.toString(data);
	}
	return bdata;
    }

    // Group name is selector, and the name can be obtain from DB or from
    // page, but taken group from page is broke conception of dataprovider!?
    public GroupData generateRandomGroup() {
	GroupData group = new GroupData();
	String[] listGroups = app.getContactHelper().getListGroups();
	int indexGroup = new Random().nextInt(listGroups.length);
	group.setGroupName(listGroups[indexGroup]);
	return group;
    }

    public ContactAddress generateRandomAddress() {
	ContactAddress address = new ContactAddress();
	address.setAddress(generateRandomString());
	address.setHomePhone(generateRandomString());
	address.setMobilePhone(generateRandomString());
	address.setWorkPhone(generateRandomString());
	address.setEmail(generateRandomString());
	address.setEmail2(generateRandomString());
	address.setAddress2(generateRandomString());
	address.setPhone2(generateRandomString());
	return address;
    }

}
