package gui;

import javax.swing.JOptionPane;

public class ErrorBox {
	
	public static void show(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Greška: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}

