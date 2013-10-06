package com.baranov.pft.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baranov.pft.fw.AccountHelper;
import com.baranov.pft.fw.UserData;
import com.baranov.pft.utils.SortedListOf;

public class RemoveUserTest extends TestBase {

	private AccountHelper accHelper;
	public UserData user = new UserData().withLogin("administrator")
			.withPassword("root").withEmail("root@localhost");

	@BeforeClass
	public void initShortcuts() {
		accHelper = app.getAccountHelper();
	}

	@Test
	public void deleteSomeUser() {
		accHelper.login(user);
		SortedListOf<UserData> oldList = app.getUserHelper().getUiUSers();
		int index = getRandomIndex(oldList.size());
		app.getUserHelper().deleteUser(index);
		SortedListOf<UserData> newList = app.getUserHelper().getUiUSers();
		assertThat(newList, equalTo(oldList.without(index)));
	}
}
