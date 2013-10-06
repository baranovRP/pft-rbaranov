package com.baranov.pft.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends WebDriverHelperBase {

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}

	public void loginPage() {
		if (!onLoginPage()) {
			logout();
		}
	}

	private boolean onLoginPage() {
		if (driver.getCurrentUrl().contains("/login_page.php")
				&& driver.findElements(By.linkText("Signup for a new account"))
						.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void logout() {
		click(By.linkText("Logout"));
	}

	public void managePage() {
		if (!onManagePage()) {
			click(By.linkText("Manage"));
		}
	}

	private boolean onManagePage() {
		if (driver.findElements(By.cssSelector("div p")).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void manageUsersPage() {
		if (!onManageUsersPage()) {
			managePage();
			manageUsers();
		}
	}

	private boolean onManageUsersPage() {
		if (driver.getCurrentUrl().contains("/manage_user_page.php")
				&& driver
						.findElements(
								By.cssSelector("table.width100 tbody tr td.form-title form input.button-small"))
						.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void manageUsers() {
		click(By.linkText("Manage Users"));
	}

}
