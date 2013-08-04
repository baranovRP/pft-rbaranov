package com.baranov.pft01;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {

    private static WebDriver driver;
    private static String baseUrl;
    private static boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();

    @BeforeTest
    public void setUp() throws Exception {
	driver = new FirefoxDriver();
	baseUrl = "http://localhost/";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() throws Exception {
	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
	    fail(verificationErrorString);
	}
    }

    protected void returntoGroupsPage() {
	driver.findElement(By.linkText("group page")).click();
    }

    protected void returntoHomePage() {
	driver.findElement(By.linkText("home page")).click();
    }

    protected void submitCreation() {
	driver.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData group) {
	driver.findElement(By.name("group_name")).clear();
	driver.findElement(By.name("group_name"))
		.sendKeys(group.getGroupName());
	driver.findElement(By.name("group_header")).clear();
	driver.findElement(By.name("group_header")).sendKeys(group.getHeader());
	driver.findElement(By.name("group_footer")).clear();
	driver.findElement(By.name("group_footer")).sendKeys(group.getFooter());
    }

    protected void fillContactForm(ContactData contact) {
	fillContactName(contact);
	fillContactAddress(contact);
	fillBirthday(contact);
	fillGroupName(contact);
    }

    private void fillContactName(ContactData contact) {
	driver.findElement(By.name("firstname")).clear();
	driver.findElement(By.name("firstname")).sendKeys(
		contact.getFirstName());
	driver.findElement(By.name("lastname")).clear();
	driver.findElement(By.name("lastname")).sendKeys(
		contact.getSecondName());
    }

    private void fillContactAddress(ContactData contact) {
	driver.findElement(By.name("address")).clear();
	driver.findElement(By.name("address")).sendKeys(
		contact.getContactAddress().getAddress());
	driver.findElement(By.name("home")).clear();
	driver.findElement(By.name("home")).sendKeys(
		contact.getContactAddress().getHomePhone());
	driver.findElement(By.name("mobile")).clear();
	driver.findElement(By.name("mobile")).sendKeys(
		contact.getContactAddress().getMobilePhone());
	driver.findElement(By.name("work")).clear();
	driver.findElement(By.name("work")).sendKeys(
		contact.getContactAddress().getWorkPhone());
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys(
		contact.getContactAddress().getEmail());
	driver.findElement(By.name("email2")).clear();
	driver.findElement(By.name("email2")).sendKeys(
		contact.getContactAddress().getEmail2());

	driver.findElement(By.name("address2")).clear();
	driver.findElement(By.name("address2")).sendKeys(
		contact.getContactAddress().getAddress2());
	driver.findElement(By.name("phone2")).clear();
	driver.findElement(By.name("phone2")).sendKeys(
		contact.getContactAddress().getPhone2());
    }

    private void fillGroupName(ContactData contact) {
	new Select(driver.findElement(By.name("new_group")))
		.selectByVisibleText(contact.getGroup().getGroupName());
    }

    private void fillBirthday(ContactData contact) {
	new Select(driver.findElement(By.name("bday")))
		.selectByVisibleText(contact.getBdata().getBday());
	new Select(driver.findElement(By.name("bmonth")))
		.selectByVisibleText(contact.getBdata().getBmonth());
	driver.findElement(By.name("byear")).clear();
	driver.findElement(By.name("byear")).sendKeys(
		contact.getBdata().getByear());
    }

    protected void initGroupCreation() {
	driver.findElement(By.name("new")).click();
    }

    protected void gotoPage(String page) {
	driver.findElement(By.linkText(page)).click();
    }

    protected void openMainPage(String page) {
	driver.get(baseUrl + page);
    }

    private boolean isElementPresent(By by) {
	try {
	    driver.findElement(by);
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    private boolean isAlertPresent() {
	try {
	    driver.switchTo().alert();
	    return true;
	} catch (NoAlertPresentException e) {
	    return false;
	}
    }

    private String closeAlertAndGetItsText() {
	try {
	    Alert alert = driver.switchTo().alert();
	    String alertText = alert.getText();
	    if (acceptNextAlert) {
		alert.accept();
	    } else {
		alert.dismiss();
	    }
	    return alertText;
	} finally {
	    acceptNextAlert = true;
	}
    }
}
