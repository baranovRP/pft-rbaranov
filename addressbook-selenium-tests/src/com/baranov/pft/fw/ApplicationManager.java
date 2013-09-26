package com.baranov.pft.fw;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ApplicationManager {

    private WebDriver driver;
    public String baseUrl;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private ActionHelper actionHelper;
    private Properties properties;
    private HibernateHelper hibernateHelper;
    private ApplicationGroupModel groupModel;
    private ApplicationContactModel contactModel;

    public ApplicationManager(Properties properties) {
	this.properties = properties;
	groupModel = new ApplicationGroupModel();
	groupModel.setGroups(getHibernateHelper().listGroups());
	contactModel = new ApplicationContactModel();
	contactModel.setContacts(getHibernateHelper().listContacts());
    }

    public void stop() {
	driver.quit();
    }

    public ApplicationGroupModel getGroupModel() {
	return groupModel;
    }

    public ApplicationContactModel getContactModel() {
	return contactModel;
    }

    public NavigationHelper navigateTo() {
	if (navigationHelper == null) {
	    navigationHelper = new NavigationHelper(this);
	}
	return navigationHelper;
    }

    public GroupHelper getGroupHelper() {
	if (groupHelper == null) {
	    groupHelper = new GroupHelper(this);
	}
	return groupHelper;
    }

    public ContactHelper getContactHelper() {
	if (contactHelper == null) {
	    contactHelper = new ContactHelper(this);
	}
	return contactHelper;
    }

    public ActionHelper getActionHelper() {
	if (actionHelper == null) {
	    actionHelper = new ActionHelper(this);
	}
	return actionHelper;
    }

    public HibernateHelper getHibernateHelper() {
	if (hibernateHelper == null) {
	    hibernateHelper = new HibernateHelper(this);
	}
	return hibernateHelper;
    }

    public WebDriver getDriver() {
	if (driver == null) {
	    String browser = properties.getProperty("browser");
	    if ("firefox".equals(browser)) {
		driver = new FirefoxDriver();
	    } else if ("ie".equals(browser)) {
		driver = new InternetExplorerDriver();
	    } else {
		throw new Error("Unsupported browser: " + browser);
	    }
	    baseUrl = properties.getProperty("baseUrl");
	    driver.manage()
		    .timeouts()
		    .implicitlyWait(
			    Integer.parseInt(properties.getProperty("timeOut")),
			    TimeUnit.SECONDS);
	    driver.get(baseUrl);
	}
	return driver;
    }

    public String getProperty(String key) {
	return properties.getProperty(key);
    }

}
