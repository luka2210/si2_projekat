package korisnici;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends Korisnik{
	
	public Student(int id, String ime, String prezime, String username, String email, String password) {
		super(id, ime, prezime, username, email, password);
		// TODO Auto-generated constructor stub
	}
	
	public Student(ResultSet rs) throws SQLException {
		super(rs);
	}

	@Override
	public boolean isStudent() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isBibliotekar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
