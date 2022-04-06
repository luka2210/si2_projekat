package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Communicator;

public class SviStudenti {
	public static ResultSet execute(int odobren) throws SQLException {
		String query = "select id, concat(ime, ' ', prezime) as ime_prezime, username, email from korisnici ";
		query += "where odobren = ? and tip = ?";
		String vars[] = {Integer.toString(odobren), "student"};
		return Communicator.executeQuery(query, vars);
	}
}
