package com.baranov.pft.fw;

import org.openqa.selenium.By;

public class ActionHelper extends HelperBase {

    public ActionHelper(ApplicationManager manager) {
	super(manager);
    }

    public void submitCreation() {
	click(By.name("submit"));
    }

    public void submitUpdate() {
	click(By.name("update"));
    }

}
