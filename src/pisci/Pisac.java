package pisci;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Pisac {
	private int id;
	private String ime, prezime;
	private int godinaRodjenja;
		
	public Pisac(ResultSet pisac) throws SQLException {
		this.id = pisac.getInt("id");
		this.ime = pisac.getString("ime");
		this.prezime = pisac.getString("prezime");
		this.godinaRodjenja = pisac.getInt("godina");
	}
	
	public abstract String getTip();
	
	public int getId() { return id; }
	public String getIme() { return ime; }
	public String getPrezime() { return prezime; }
	public int getGodinaRodjenja() { return godinaRodjenja; }
	
	public void setId(int id) { this.id = id; }
	public void setIme(String ime) { this.ime = ime; }
	public void setPrezime(String prezime) { this.prezime = prezime; }
	public void setGodinaRodjenja(int godinaRodjenja) { this.godinaRodjenja = godinaRodjenja; }
	public String toString() {
		return getTip() + ": " + this.ime + " " + this.prezime;
	}
}
