package com.baranov.pft.fw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountHelper extends WebDriverHelperBase {

	public AccountHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void signup(UserData user) {
		openUrl("/");
		click(By.cssSelector("span.bracket-link"));
		type(By.name("username"), user.getLogin());
		type(By.name("email"), user.getEmail());
		click(By.cssSelector("input.button"));

		pause(3000);
		WebElement errorMsg = findElement(By
				.cssSelector("table.width50 tbody tr td p"));
		if (errorMsg != null) {
			throw new RuntimeException(errorMsg.getText());
		}

		pause(3000);
		String msg = manager.getMailHelper().getNewMail(user.getLogin(),
				user.getPassword());
		String confirmationLink = getConfirmationLink(msg);
		openAbsoluteUrl(confirmationLink);
		type(By.name("password"), user.getPassword());
		type(By.name("password_confirm"), user.getPassword());
		click(By.cssSelector("input.button"));
	}

	public String getConfirmationLink(String text) {
		Pattern regex = Pattern.compile("http\\S*");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}

	public String loggedUser() {
		WebElement element = findElement(By
				.cssSelector("td.login-info-left span"));
		return element.getText();
	}

	public void login(UserData user) {
		openUrl("/");
		type(By.name("username"), user.getLogin());
		type(By.name("password"), user.getPassword());
		click(By.cssSelector("input.button"));

	}
	
	public void logout(){
		logout();
	}
}
