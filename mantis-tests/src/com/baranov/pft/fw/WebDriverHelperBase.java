package com.baranov.pft.fw;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class WebDriverHelperBase extends HelperBase {

	protected WebDriver driver;
	public boolean acceptNextAlert = true;

	public WebDriverHelperBase(ApplicationManager manager) {
		super(manager);
		// this.manager = manager;
		this.driver = manager.getDriver();
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
			findElement(locator).clear();
			findElement(locator).sendKeys(text);
		}
	}

	protected void click(By locator) {
		findElement(locator).click();
	}

	protected void selectByText(By locator, String text) {
		if (text != null) {
			new Select(findElement(locator)).selectByVisibleText(text);
		}
	}

	protected WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	protected List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	protected String extractTitle(WebElement element, String title) {
		int beginIndex = "Select (".length();
		int endIndex = element.getAttribute(title).length() - ")".length();
		return element.getAttribute(title).substring(beginIndex, endIndex);
	}

	protected String extractValue(WebElement element, String title) {
		return element.getAttribute(title);
	}

	protected String extractGroup(WebElement element) {
		return element.getText();
	}

	protected String extractData(WebElement element, By locator) {
		return element.findElement(locator).getText();
	}

	protected void openUrl(String string) {
		driver.get(manager.getProperty("baseUrl") + string);
	}

	protected void openAbsoluteUrl(String string) {
		driver.get(string);
	}
}
