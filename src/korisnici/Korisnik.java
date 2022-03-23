package korisnici;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Korisnik {
	private int id;
	private String ime, prezime;
	private String username;
	private String email;
	private String password;
	
	public Korisnik(int id, String ime, String prezime, String username, String email, String password) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public Korisnik(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.ime = rs.getString("ime");
		this.prezime = rs.getString("prezime");
		this.username = rs.getString("username");
		this.email = rs.getString("email");
		this.password = rs.getString("password");
	}
	
	public abstract boolean isStudent();
	public abstract boolean isBibliotekar();

	public int getId() { return id; }
	public String getIme() { return ime; }
	public String getPrezime() { return prezime; }
	public String getUsername() { return username; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	
	public void setId(int id) { this.id = id; }
	public void setIme(String ime) { this.ime = ime; }
	public void setPrezime(String prezime) { this.prezime = prezime; }
	public void setUsername(String username) { this.username = username; }
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
}
