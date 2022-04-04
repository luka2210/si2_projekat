package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Communicator;
import korisnici.Student;

public class MojeRezervacije {
	public static ResultSet execute(Student student, int istekla) throws SQLException {
		String query = "select concat(s.ime, ' ', s.prezime) as ime_prezime, k.naziv as naziv, r.datum as datum from korisnici s ";
		query += "inner join rezervacije r on r.id = s.id ";
		query += "inner join knjige k on k.isbn = r.isbn ";
		query += "where s.id = ? and r.istekla = ?";
		
		String vars[] = {Integer.toString(student.getId()), Integer.toString(istekla)};
		return Communicator.executeQuery(query, vars);
	}
}
