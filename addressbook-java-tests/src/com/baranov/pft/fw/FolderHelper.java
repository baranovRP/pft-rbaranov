package com.baranov.pft.fw;

import java.util.ArrayList;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

public class FolderHelper extends HelpersBase {

	public static boolean YES = true;
	public static boolean NO = false;

	public FolderHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public Folders getFolders() {
		ArrayList<String> list = new ArrayList<String>();
		JTreeOperator tree = new JTreeOperator(mainFrame);
		Object[] children = tree.getChildren(tree.getRoot());
		for (Object child : children) {
			list.add("" + child);
		}
		return new Folders(list);
	}

	public String createFolder(String folderName) {
		manager.getMenuHelper().pushCreateFolder();
		JDialogOperator dialog = new JDialogOperator(mainFrame);
		new JTextFieldOperator(dialog).setText(folderName);
		new JButtonOperator(dialog, "OK").push();
		return waitMessageDialog("Warning", 3000);
	}

	public void deleteFolderYES(int index) {
		tryToDeleteFolder(index, YES);
	}

	public void deleteFolderNO(int index) {
		tryToDeleteFolder(index, NO);
	}

	public void tryToDeleteFolder(int index, boolean decision) {
		JTreeOperator tree = new JTreeOperator(mainFrame);
		tree.selectRow(index);
		manager.getMenuHelper().pushDeleteFolder();
		JDialogOperator dialog = new JDialogOperator(mainFrame);
		if (decision) {
			new JButtonOperator(dialog, "Yes").push();
		} else {
			new JButtonOperator(dialog, "No").push();
		}
	}
}
