package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Communicator;
import gui.ErrorBox;
import knjige.Knjiga;

public class BrojRezervacijaKnjige{
	Knjiga knjiga;
	
	public BrojRezervacijaKnjige(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	
	public int execute() {
		// TODO Auto-generated method stub
		String query = "select count(*) as cnt from rezervacije where isbn = ? and istekla = ?;";
		String[] vars = {Integer.toString(knjiga.getIsbn()), "0"};
		try {
			ResultSet rs = Communicator.executeQuery(query, vars);
			while (rs.next())
				return rs.getInt("cnt");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorBox.show("Došlo je do greške prilikom izvršavanja, pokušajte ponovo.", "sql greška");
			e.printStackTrace();
		}
		return 0;
	}

}
