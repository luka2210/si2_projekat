package gui;

import javax.swing.JOptionPane;

public class ConfirmBox {
	public static int show(String infoMessage, String titleBar) {
		return JOptionPane.showConfirmDialog(null, infoMessage, titleBar, JOptionPane.OK_CANCEL_OPTION);
	}
}
