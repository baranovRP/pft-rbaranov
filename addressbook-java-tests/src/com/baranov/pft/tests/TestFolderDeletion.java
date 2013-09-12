package com.baranov.pft.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.testng.annotations.Test;

import com.baranov.pft.fw.Folders;

public class TestFolderDeletion extends TestBase {
	@Test
	public void testFolderDeletion() {
		Folders oldFolders = app.getFolderHelper().getFolders();
		int index = new Random().nextInt(oldFolders.length());
		app.getFolderHelper().deleteFolderYES(index);
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders, equalTo(oldFolders.withoutDeleted(index)));
	}

	@Test
	public void testFolderNegativeDeletion() {
		Folders oldFolders = app.getFolderHelper().getFolders();
		int index = new Random().nextInt(oldFolders.length());
		app.getFolderHelper().deleteFolderNO(index);
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders, equalTo(oldFolders));
	}

	// Create test: try to press File|Delete when folder's tree is empty
}
