package logic;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import db.Communicator;
import gui.ConfirmBox;
import gui.ErrorBox;
import gui.RezervacijeProzor;
import korisnici.Korisnik;

public class OtkaziRezervaciju {
	public static void execute(JFrame frame, Korisnik korisnik, String id, String isbn) {
		if (ConfirmBox.show("Da li ste sigurni da želite da otkažete rezervaciju?", "Otkaži rezervaciju") != 0)
			return;
		String query = "update rezervacije set istekla = 1 where id = ? and isbn = ?";
		String[] vars = {id, isbn};
		try {
			Communicator.executeUpdate(query, vars);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			RezervacijeProzor.launch(korisnik);
			JOptionPane.showMessageDialog(null, "Rezervacija uspešno otkazana!");
		}
		catch (Exception e) {
			ErrorBox.show("Došlo je do greške prilikom obrade zahteva.", "database error");
			e.printStackTrace();
		}
	}
}
