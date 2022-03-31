package logic;

import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import db.Communicator;
import gui.ConfirmBox;
import gui.ErrorBox;
import knjige.Knjiga;
import knjige.KnjigaBuilder;
import korisnici.Korisnik;

public class Rezervacija implements Komanda{
	Korisnik korisnik;
	Knjiga knjiga;
	JFrame frame;
	
	public Rezervacija(Korisnik korisnik, Knjiga knjiga, JFrame frame) {
		this.korisnik = korisnik;
		this.knjiga = knjiga;
		this.frame = frame;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			if (!korisnik.isStudent()) {
				ErrorBox.show("Samo studenti mogu da rezervišu knjige!", "korisnik nije student");
				return;
			}
			if (vecRezervisana()) {
				ErrorBox.show("Već ste rezervisali ovu knjigu.", "rezervisana");
				return;
			}
			if (nedostupna()) {
				ErrorBox.show("Trenutno ne postoji nijedan primerak ove knjige na raspolaganju.", "nedostupna knjiga");
				return;
			}
			if (ConfirmBox.show("Da li ste sigurni da želite da rezervišete knjigu \"" + knjiga.getNaziv() + "\" ?", "Potrvda rezervacije") != 0)
				return;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			String query = "insert into rezervacije values(?, ?, ?, ?);";
			String[] vars = {Integer.toString(korisnik.getId()), Integer.toString(knjiga.getIsbn()), dtf.format(now), "0"};
			Communicator.executeUpdate(query, vars);
			JOptionPane.showMessageDialog(null, "Knjiga uspešno rezervisana! ");
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorBox.show("Došlo je do greške prilikom izvršavanja, pokušajte ponovo.", "sql greška");
			e.printStackTrace();
		}
	}
	
	public boolean vecRezervisana() throws SQLException{
		String query = "select * from rezervacije where id = ? and isbn = ? and istekla = ?";
		String[] vars = {Integer.toString(korisnik.getId()), Integer.toString(knjiga.getIsbn()), "0"};
		ResultSet rs = Communicator.executeQuery(query, vars);
		return rs.next();
	}
	
	public boolean nedostupna() throws SQLException {
		String query = "select * from knjige where isbn = ?;";
		String[] vars = {Integer.toString(knjiga.getIsbn())};
		ResultSet rs = Communicator.executeQuery(query, vars);
		if (rs.next())
			return KnjigaBuilder.getInstance(rs).brojPreostalihKnjiga() < 1;
		return true;
	}
}
