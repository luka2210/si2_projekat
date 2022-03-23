package knjige;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pisci.Autor;
import pisci.Pisac;

public class Praktikum extends Knjiga{
	private ArrayList<Autor> autori;
	
	public Praktikum(ResultSet knjiga, ResultSet autori) throws SQLException {
		super(knjiga);
		// TODO Auto-generated constructor stub
		this.setAutori(autori);
	}

	@Override
	public String getTip() {
		// TODO Auto-generated method stub
		return "praktikum";
	}
	
	@Override
	public ArrayList<Pisac> getPisci() {
		// TODO Auto-generated method stub
		ArrayList<Pisac> pisci = new ArrayList<Pisac>();
		for (Autor autor: autori) 
			pisci.add(autor);
		return pisci;
	}

	public ArrayList<Autor> getAutori() { return autori; }
	
	public void setAutori(ResultSet autori) throws SQLException { 
		this.autori = new ArrayList<Autor>();
		while(autori.next()) 
			this.autori.add(new Autor(autori));
	}
}
