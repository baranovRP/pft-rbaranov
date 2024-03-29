package com.baranov.pft.fw;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ApplicationManager {

	private WebDriver driver;
	public String baseUrl;

	private Properties properties;
	private HibernateHelper hibernateHelper;
	private AccountHelper accountHelper;
	private MailHelper mailHelper;
	private JamesHelper jamesHelper;
	private UserHelper userHelper;
	private NavigationHelper navigationHelper;

	public ApplicationManager(Properties properties) {
		this.properties = properties;
	}

	public void stop() {
		driver.quit();
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

	public AccountHelper getAccountHelper() {
		if (accountHelper == null) {
			accountHelper = new AccountHelper(this);
		}
		return accountHelper;
	}

	public MailHelper getMailHelper() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}

	public JamesHelper getJamesHelper() {
		if (jamesHelper == null) {
			jamesHelper = new JamesHelper(this);
		}
		return jamesHelper;
	}

	public UserHelper getUserHelper() {
		if (userHelper == null) {
			userHelper = new UserHelper(this);
		}
		return userHelper;
	}

	public NavigationHelper navigateTo() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}
}
