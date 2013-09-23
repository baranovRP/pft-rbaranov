package com.baranov.pft.fw;

import org.openqa.selenium.By;

public class ActionHelper extends WebDriverHelperBase {

    public ActionHelper(ApplicationManager manager) {
	super(manager);
    }

    public ActionHelper submitCreation() {
	click(By.name("submit"));
	return this;
    }

    public ActionHelper submitUpdate() {
	click(By.name("update"));
	return this;
    }
}
