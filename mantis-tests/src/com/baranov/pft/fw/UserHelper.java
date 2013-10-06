package com.baranov.pft.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.baranov.pft.utils.SortedListOf;

public class UserHelper extends WebDriverHelperBase {

	public UserHelper(ApplicationManager manager) {
		super(manager);
	}

	public UserData user = new UserData().withLogin("administrator")
			.withPassword("root").withEmail("root@localhost");

	public UserHelper deleteUser(int index) {
		goToManageUsersPage();
		selectUser(index);
		submitUserDeletion();
		goToManageUsersPage();
		return this;
	}

	public SortedListOf<UserData> getUiUSers() {

		SortedListOf<UserData> users = new SortedListOf<UserData>();

		goToManageUsersPage();
		List<WebElement> elements = findElements(By
				.xpath("//table[3]/tbody/tr"));
		removeInessentialElements(elements);
		for (WebElement element : elements) {
			UserData user = extractUser(element);
			users.add(user);
		}
		return users;
	}

	private void removeInessentialElements(List<WebElement> elements) {
		elements.remove(0);
		elements.remove(0);
		elements.remove(elements.size() - 1);
	}

	public UserData extractUser(WebElement webElement) {
		String login = extractData(webElement, (By.xpath("./td[1]/a")));
		String email = extractData(webElement, (By.xpath("./td[3]")));
		UserData user = new UserData().withLogin(login).withEmail(email);
		return user;
	}

	public UserHelper goToManageUsersPage() {
		manager.navigateTo().managePage();
		manager.navigateTo().manageUsersPage();
		return this;
	}

	public void selectUser(int index) {
		WebElement element = findElement(By.xpath("//table[3]/tbody/tr["
				+ (index + 3) + "]"));
		String login = extractData(element, (By.xpath("./td[1]/a")));
		String email = extractData(element, (By.xpath("./td[3]")));
		UserData selectedUser = new UserData().withLogin(login)
				.withEmail(email);
		if (user.equals(selectedUser)) {
			throw new RuntimeException("Administrator cannot be deleted");
		} else {
			click(By.xpath("//table[3]/tbody/tr[" + (index + 3) + "]/td[1]/a"));
		}
	}

	public void submitUserDeletion() {
		click(By.xpath("//input[@value='Delete User']"));
		click(By.cssSelector("input.button"));
		pause(3000);
	}
}
