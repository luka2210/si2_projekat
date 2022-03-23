package knjige;

import java.sql.ResultSet;
import java.sql.SQLException;
import db.Communicator;
import gui.ErrorBox;

public class KnjigaBuilder {
	
	public static Knjiga getInstance(ResultSet knjiga) throws SQLException {
		switch (knjiga.getString("tip")) {
		case "diplomski":
			return new Diplomski(knjiga, getPisci(knjiga, "autor"), getPisci(knjiga, "mentor"));
		case "doktorski":
			return new Doktorski(knjiga, getPisci(knjiga, "autor"), getPisci(knjiga, "mentor"));
		case "master":
			return new Master(knjiga, getPisci(knjiga, "autor"), getPisci(knjiga, "mentor"));
		case "zavrsni":
			return new Zavrsni(knjiga, getPisci(knjiga, "autor"), getPisci(knjiga, "mentor"));
		case "praktikum":
			return new Praktikum(knjiga, getPisci(knjiga, "autor"));
		case "udzbenik":
			return new Udzbenik(knjiga, getPisci(knjiga, "autor"));
		case "zbirka":
			return new Zbirka(knjiga, getPisci(knjiga, "autor"));
		default:
			ErrorBox.show("Došlo je do greške, pokušajte ponovo.", "sql greška");
			return null;
		}
	}
	
	private static ResultSet getPisci(ResultSet knjiga, String tip) throws SQLException {
		String[] vars = {Integer.toString(knjiga.getInt("isbn")), tip};
		String query = "select p.* from pisci p ";
		query += "inner join knjige_pisci kp on p.id = kp.id ";
		query += "where kp.isbn = ? ";
		query += "and p.tip = ?";
		return Communicator.executeQuery(query, vars);
	}
}
