package com.baranov.pft.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baranov.pft.fw.User;

public class SignupTest extends TestBase {

	public User user = new User().setLogin("dz23_brp_pft")
			.setPassword("dz23_brp_pft").setEmail("dz23_brp_pft@ukr.net");

	// @BeforeClass
	// public void createMailUser() {
	// if (!app.getJamesHelper().doesUserExist(user.login)) {
	// app.getJamesHelper().createUser(user.login, user.password);
	// }
	// }

	// @AfterClass
	// public void deleteMailUser() {
	// if (app.getJamesHelper().doesUserExist(user.login)) {
	// app.getJamesHelper().deleteUser(user.login);
	// }
	// }

	@Test
	public void newUserShouldSignup() {
		app.getAccountHelper().signup(user);
		Assert.assertTrue(app.getAccountHelper().isLogged(user));
	}

}
