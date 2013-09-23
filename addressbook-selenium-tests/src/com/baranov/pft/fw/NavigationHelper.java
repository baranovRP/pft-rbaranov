package com.baranov.pft.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends WebDriverHelperBase {

    public NavigationHelper(ApplicationManager manager) {
	super(manager);
    }

    public void mainPage() {
	if (!onMainPage()) {
	    click(By.linkText("home"));
	}
    }

    private boolean onMainPage() {
	return driver.findElements(By.id("maintable")).size() > 0;
    }

    public void addNewPage() {
	if (!onAddNewPage()) {
	    click(By.linkText("add new"));
	}
    }

    private boolean onAddNewPage() {
	driver.findElements(By.name("submit"));
	if (driver.getCurrentUrl().contains("/edit.php")
		&& driver.findElements(By.name("new")).size() > 0) {
	    return true;
	} else {
	    return false;
	}
    }

    public void groupsPage() {
	if (!onGroupsPage()) {
	    click(By.linkText("groups"));
	}
    }

    public boolean onGroupsPage() {
	driver.findElements(By.name("new"));
	if (driver.getCurrentUrl().contains("/group.php")
		&& driver.findElements(By.name("new")).size() > 0) {
	    return true;
	} else {
	    return false;
	}
    }
}
