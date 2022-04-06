package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import db.Communicator;
import gui.ErrorBox;

public class Signup implements Komanda{
	private String ime, prezime, username, password, email, tip = null;
	private JFrame signupFrame;
	
	public Signup(String ime, String prezime, String username, String password, String email, JFrame signupFrame) {
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.signupFrame = signupFrame;
	}
	
	public Signup(String ime, String prezime, String username, String password, String email, String tip) {
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tip = tip.toLowerCase();
	}

	public void execute() {
		try {
			if (!checkLengths()) 
				return;
			if (!checkFormats())
				return;
			if (!checkUniques())
				return;
			
			if (tip == null) {
				String[] vars = {ime, prezime, username, password, email};
				Communicator.executeUpdate("insert into korisnici(ime, prezime, username, password, email) values(?, ?, ?, ?, ?)", vars);
				
				JOptionPane.showMessageDialog(null, "Uspešno ste se registrovali! ");
				ToLoginProzor tlg = new ToLoginProzor(signupFrame);
				tlg.execute();
			}
			else {
				String[] vars = {ime, prezime, username, password, email, tip};
				Communicator.executeUpdate("insert into korisnici(ime, prezime, username, password, email, tip) values(?, ?, ?, ?, ?, ?)", vars);
				JOptionPane.showMessageDialog(null, "Nalog uspešno dodat u bazu!");
			}
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private boolean checkUniques() throws SQLException {
		String[] varsUsername = {username};
		ResultSet rsUsername = Communicator.executeQuery("select * from korisnici where username = ?", varsUsername);
		if (rsUsername.next()) {
			ErrorBox.show("Korisničko ime je zauzeto, odaberite neko drugo.", "Greška: korisničko ime");
			return false;
		}
		String[] varsEmail = {email};
		ResultSet rsEmail = Communicator.executeQuery("select * from korisnici where email = ?", varsEmail);
		if (rsEmail.next()) {
			ErrorBox.show("E-mail adresa koju ste uneli je već u upotrebi.", "Greška: e-mail");
			return false;
		}
		return true;
	}
	
	private boolean checkLengths() {
		if (ime.length() == 0 || ime.length() > 30) {
			ErrorBox.show("Polje ime ne sme biti prazno i ne sme imati više od 30 karaktera!", "Greška: ime");
			return false;
		}
		if (prezime.length() == 0 || prezime.length() > 30) {
			ErrorBox.show("Polje prezime ne sme biti prazno i ne sme imati više od 30 karaktera!", "Greška: prezime");
			return false;
		}
		if (username.length() < 5 || username.length() > 20) {
			ErrorBox.show("Korisničko ime mora imati izmedju 5 i 20 karaktera!", "Greška: korisničko ime");
			return false;
		}
		if (password.length() < 8 || password.length() > 30) {
			ErrorBox.show("Lozinka mora imati izmedju 8 i 30 karaktera!", "Greška: lozinka");
			return false;
		}
		if (email.length() == 0 || email.length() > 60) {
			ErrorBox.show("Polje e-mail ne sme biti prazno i ne sme imati više od 60 karaktera!", "Greška: e-mail");
			return false;
		}
		return true;
	}
	
	private boolean checkFormats() {
		if (!username.matches("^[a-zA-Z0-9]*$")) {
			ErrorBox.show("Korisničko ime sme da sadrži samo slova i brojeve!", "Greška: korisničko ime");
			return false;
		}
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} 
		catch (AddressException ex) {
			ErrorBox.show("E-mail nije u odgovarajućem formatu!", "Greška: e-mail");
			return false;
		}
		return true;
	}
}
