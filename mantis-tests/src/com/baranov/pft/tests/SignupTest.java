package com.baranov.pft.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baranov.pft.fw.AccountHelper;
import com.baranov.pft.fw.User;

public class SignupTest extends TestBase {

	private AccountHelper accHelper;

	public User user = new User().setLogin("dz23_brp_pft")
			.setPassword("dz23_brp_pft").setEmail("dz23_brp_pft@ukr.net");

	@BeforeClass
	public void initShortcuts() {
		accHelper = app.getAccountHelper();
	}

	@Test
	public void newUserShouldSignup() {
		accHelper.signup(user);
		accHelper.login(user);
		assertThat(accHelper.loggedUser(), equalTo(user.login));
	}

	@Test
	public void existingUserShouldNotSignup() {
		try {
			accHelper.signup(user);
		} catch (Exception e) {
			assertThat(
					e.getMessage(),
					containsString("That username is already being used. Please go back and select another one."));
			return;
		}
		fail("Exception expected");
	}
}
