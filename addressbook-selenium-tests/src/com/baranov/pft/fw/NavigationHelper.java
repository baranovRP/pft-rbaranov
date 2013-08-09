package com.baranov.pft.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager manager) {
	super(manager);
    }

    public void returntoGroupsPage() {
	click(By.linkText("group page"));
    }

    public void returntoHomePage() {
	click(By.linkText("home page"));
    }

    public void gotoPage(String page) {
	click(By.linkText(page));
    }

    public void openMainPage(String page) {
	driver.get(manager.baseUrl + page);
    }

}
