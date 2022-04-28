package knjige;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.BrojRezervacijaKnjige;
import pisci.Pisac;

public abstract class Knjiga {
	private String naziv;
	private int godina;
	private String izdavac;
	private int isbn;
	private int broj;
	private String slikaPath;
	private String slikaPath2;
	
	public Knjiga(ResultSet knjiga) throws SQLException {
		super();
		this.naziv = knjiga.getString("naziv");
		this.godina = knjiga.getInt("godina");
		this.izdavac = knjiga.getString("izdavac");
		this.isbn = knjiga.getInt("isbn");
		this.broj = knjiga.getInt("broj");
		this.slikaPath = knjiga.getString("slika");
		this.slikaPath2 = knjiga.getString("slika2");
	}
	
	public abstract String getTip();
	public abstract ArrayList<Pisac> getPisci();
	
	public String getNaziv() { return naziv; }
	public int getGodina() { return godina; }
	public String getIzdavac() { return izdavac; }
	public int getIsbn() { return isbn; }
	public int getBroj() { return broj; }
	public String getSlikaPath() { return slikaPath; }
	
	public void setNaziv(String naziv) { this.naziv = naziv; }
	public void setGodina(int godina) { this.godina = godina; }
	public void setIzdavac(String izdavac) { this.izdavac = izdavac; }
	public void setIsbn(int isbn) { this.isbn = isbn; }
	public void setBroj(int broj) { this.broj = broj; }
	public void setSlikaPath(String slikaPath) { this.slikaPath = slikaPath; }	
	public String getSlikaPath2() { return slikaPath2; }
	public void setSlikaPath2(String slikaPath2) { this.slikaPath2 = slikaPath2; }

	
	public int brojPreostalihKnjiga() {
		BrojRezervacijaKnjige brk = new BrojRezervacijaKnjige(this);
		return broj - brk.execute();
	}
	
	@Override
	public String toString() {
		return naziv + ", " + getTip() + ", " + godina + ".";
	}
}
