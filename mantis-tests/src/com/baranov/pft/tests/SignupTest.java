package com.baranov.pft.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baranov.pft.fw.User;

public class SignupTest extends TestBase {

	public User user = new User().setLogin("testuser4").setPassword("123456")
			.setEmail("testuser4@localhost.localdomain");

	@BeforeClass
	public void createMailUser() {
		if (!app.getJamesHelper().doesUserExist(user.login)) {
			app.getJamesHelper().createUser(user.login, user.password);
		}
	}

	@AfterClass
	public void deleteMailUser() {
		if (app.getJamesHelper().doesUserExist(user.login)) {
			app.getJamesHelper().deleteUser(user.login);
		}
	}

	@Test
	public void newUserShouldSignup() {
		app.getAccountHelper().signup(user);
		Assert.assertTrue(app.getAccountHelper().isLogged(user));
	}

}
