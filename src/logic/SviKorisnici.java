package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Communicator;

public class SviKorisnici {
	public static ResultSet execute(int blokiran) throws SQLException {
		String query = "select id, concat(ime, ' ', prezime) as ime_prezime, username, email from korisnici ";
		query += "where blokiran = ?";
		String vars[] = {Integer.toString(blokiran)};
		return Communicator.executeQuery(query, vars);
	}
}
