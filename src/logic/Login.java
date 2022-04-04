package logic;

import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import db.Communicator;
import gui.BibliotekarProzor;
import gui.StudentProzor;
import korisnici.*;
import gui.ErrorBox;

public class Login implements Komanda{
	private String username, password;
	private JFrame loginFrame;
	
	public Login(String username, String password, JFrame loginFrame) {
		this.username = username;
		this.password = password;
		this.loginFrame = loginFrame;
	}
	
	public void execute() {
		try {
			String[] vars = {username, password};
			ResultSet rs = Communicator.executeQuery("select * from korisnici where username = ? and password = ?", vars);
			if (rs.next()) {
				if (rs.getString("tip").equals("student")) {
					if (!rs.getBoolean("odobren")) {
						ErrorBox.show("Vaš nalog još uvek nije odobren.", "neodobren nalog");
						return;
					}
					Student student = new Student(rs);
					StudentProzor.launch(student);
				}
				else if (rs.getString("tip").equals("bibliotekar")){
					Bibliotekar bibliotekar = new Bibliotekar(rs);
					BibliotekarProzor.launch(bibliotekar);
				}
				loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
			}
			else
				ErrorBox.show("Pogrešno korisničko ime ili pogrešna lozinka!", "neuspešna prijava");
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
