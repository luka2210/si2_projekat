package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Communicator;

public class SveRezervacije {
	public static ResultSet execute(int istekla) throws SQLException {
		String query = "select concat(s.ime, ' ', s.prezime) as ime_prezime, k.naziv as naziv, r.datum as datum from korisnici s ";
		query += "inner join rezervacije r on r.id = s.id ";
		query += "inner join knjige k on k.isbn = r.isbn ";
		query += "where r.istekla = ?";
		
		String vars[] = {Integer.toString(istekla)};
		return Communicator.executeQuery(query, vars);
	}
}
