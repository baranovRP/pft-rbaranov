package com.baranov.pft.fw;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class HelperBase {

    protected ApplicationManager manager;
    protected WebDriver driver;
    public boolean acceptNextAlert = true;

    public HelperBase(ApplicationManager manager) {
	this.manager = manager;
	this.driver = manager.driver;
    }

    public boolean isElementPresent(By locator) {
	try {
	    driver.findElement(locator);
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    public boolean isAlertPresent() {
	try {
	    driver.switchTo().alert();
	    return true;
	} catch (NoAlertPresentException e) {
	    return false;
	}
    }

    public String closeAlertAndGetItsText() {
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

    protected void type(By locator, String text) {
	if (text != null) {
	    findElementonPage(locator).clear();
	    findElementonPage(locator).sendKeys(text);
	}
    }

    protected void click(By locator) {
	findElementonPage(locator).click();
    }

    protected void selectByText(By locator, String text) {
	if (text != null) {
	    new Select(findElementonPage(locator)).selectByVisibleText(text);
	}
    }

    private WebElement findElementonPage(By locator) {
	return driver.findElement(locator);
    }
}
