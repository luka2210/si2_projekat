package logic;

import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import db.Communicator;
import gui.BlokiranjeProzor;
import gui.ConfirmBox;
import gui.ErrorBox;

public class Blokiraj {
	public static void execute(JFrame frame, String id, int blokiraj) {
		String query = "update korisnici set blokiran = ? where id = ?";
		String[] vars = {Integer.toString(blokiraj), id};
		try {
			String var = "blokirate";
			if (blokiraj == 0) 
				var = "odblokirate";
			if (ConfirmBox.show("Da li ste sigurni da želite da " + var +" nalog " + id + "?", null) != 0)
				return;
			Communicator.executeUpdate(query, vars);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			BlokiranjeProzor.launch();
			var = "blokiran";
			if (blokiraj == 0)
				var = "odblokiran";
			JOptionPane.showMessageDialog(null, "Nalog uspešno " + var + "!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorBox.show("Došlo je problema s radom baze podataka.", "database error");
			e.printStackTrace();
		}
	}
}
