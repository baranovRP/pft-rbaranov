package com.baranov.pft.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
//import static org.junit.Assert.assertThat;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baranov.pft.fw.AccountHelper;
import com.baranov.pft.fw.UserData;
import com.baranov.pft.utils.SortedListOf;

public class SignupTest extends TestBase {

	private AccountHelper accHelper;

	@BeforeClass
	public void initShortcuts() {
		accHelper = app.getAccountHelper();
	}

	@Test(dataProvider = "validUser")
	public void newUserShouldSignup(UserData user) {
		accHelper.login(userAdministrator);
		SortedListOf<UserData> oldList = app.getUserHelper().getUiUSers();
		accHelper.logoutUser();
		accHelper.signup(user);
		accHelper.login(user);
		assertThat(accHelper.loggedUser(), equalTo(user.getLogin()));
		accHelper.logoutUser();
		accHelper.login(userAdministrator);
		SortedListOf<UserData> newList = app.getUserHelper().getUiUSers();
		assertThat(newList, equalTo(oldList.withAdded(user)));
	}

	@Test(dataProvider = "validUser")
	public void existingUserShouldNotSignup(UserData user) {
		try {
			accHelper.signup(user);
		} catch (Exception e) {
			assertThat(
					e.getMessage(),
					containsString("That username is already being used. Please go back and select another one."));
			return;
		}
		Assert.fail("Exception expected");
	}
	
}
