package logic;

import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import db.Communicator;
import gui.ConfirmBox;
import gui.ErrorBox;
import gui.StudentiProzor;

public class OdobriNalog {
	public static void execute(JFrame frame, String id) {
		String query = "update korisnici set odobren = 1 where id = ? and tip = ?";
		String[] vars = {id, "student"};
		try {
			if (ConfirmBox.show("Da li ste sigurni da želite da odobrite nalog " + id + "?", null) != 0)
				return;
			Communicator.executeUpdate(query, vars);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			StudentiProzor.launch();
			JOptionPane.showMessageDialog(null, "Nalog uspešno odobren!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorBox.show("Došlo je problema s radom baze podataka.", "database error");
			e.printStackTrace();
		}
	}
}
