package com.baranov.pft.fw;

import java.util.ArrayList;

import javax.swing.JDialog;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

public class FolderHelper {

	private ApplicationManager manager;

	public FolderHelper(ApplicationManager applicationManager) {
		this.manager = applicationManager;
	}

	public Folders getFolders() {
		ArrayList<String> list = new ArrayList<String>();
		JTreeOperator tree = new JTreeOperator(manager.getApplication());
		Object[] children = tree.getChildren(tree.getRoot());
		for (Object child : children) {
			list.add("" + child);
		}
		return new Folders(list);
	}

	public String createFolder(String folderName) {
		manager.getMenuHelper().pushCreateFolder();
		JDialogOperator dialog = new JDialogOperator(manager.getApplication());
		new JTextFieldOperator(dialog).setText(folderName);
		new JButtonOperator(dialog, "OK").push();
		return waitMessageDialog("Warning", 3000);

	}

	private String waitMessageDialog(String title, int timeout) {
		long start = System.currentTimeMillis();
		long currentTime = start;
		while (currentTime < start + timeout) {
			JDialog dialog = JDialogOperator.findJDialog(manager
					.getApplication().getOwner(), title, false, false);
			if (dialog != null) {
				JDialogOperator dialogOp = new JDialogOperator(dialog);
				String message = new JLabelOperator(dialogOp).getText();
				dialogOp.requestClose();
				return message;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime = System.currentTimeMillis();
		}
		return null;
	}
}
