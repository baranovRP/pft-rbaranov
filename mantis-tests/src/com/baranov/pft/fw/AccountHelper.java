package com.baranov.pft.fw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountHelper extends WebDriverHelperBase {

	public AccountHelper(ApplicationManager manager) {
		super(manager);
	}

	public void signup(UserData user) {
		openUrl("/");
		click(By.xpath("//a[contains(@href, 'signup_page.php')]"));
		type(By.name("username"), user.getLogin());
		type(By.name("email"), user.getEmail());
		click(By.cssSelector("input.button"));

		pause(10000);
		WebElement errorMsg = findElement(By
				.cssSelector("div table.width50 tbody tr td p.center"));
		if (errorMsg != null) {
			throw new RuntimeException(errorMsg.getText());
		}
		
//		click(By.cssSelector("span.bracket-link a"));
		pause(5000);
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
	
	public void logoutUser(){
		manager.navigateTo().logout();
	}
}
