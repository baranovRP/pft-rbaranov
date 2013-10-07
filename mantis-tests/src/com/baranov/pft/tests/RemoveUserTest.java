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
	
	@BeforeClass
	public void initShortcuts() {
		accHelper = app.getAccountHelper();
	}

	@Test
	public void deleteSomeUser() throws InterruptedException {
		accHelper.login(userAdministrator);
		SortedListOf<UserData> oldList = app.getUserHelper().getUiUSers();
		int index = getRandomIndex(oldList.size());
		app.getUserHelper().deleteUser(index);
		Thread.sleep(3000);
		SortedListOf<UserData> newList = app.getUserHelper().getUiUSers();
		assertThat(newList, equalTo(oldList.without(index)));
	}
}
