package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Communicator;
import gui.ErrorBox;
import gui.RezervacijeProzor;
import korisnici.Student;

public class StudentRezervacije {
	public static void execute(String id) {
		String query = "select * from korisnici where id = ? and tip = ?";
		String vars[] = {id, "student"};
		try {
			ResultSet rs = Communicator.executeQuery(query, vars);
			if (rs.next()) {
				Student student = new Student(rs);
				RezervacijeProzor.launch(student);
			}
			else 
				throw new SQLException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorBox.show("Došlo je do problema s radom baze podataka", "database error");
			e.printStackTrace();
		}
	}
}
