package knjige;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pisci.Autor;
import pisci.Pisac;

import java.util.AbstractCollection;

public class Izdanje extends Knjiga{
	
	public Izdanje(ResultSet knjiga) throws SQLException {
		super(knjiga);
		// TODO Auto-generated constructor stub
	}
	private ArrayList<Autor> autori;
	

	@Override
	public ArrayList<Pisac> getPisci() {
		// TODO Auto-generated method stub
		ArrayList<Pisac> pisci = new ArrayList<Pisac>();
		for (Autor autor: autori) 
			pisci.add(autor);
		return pisci;
	}
	
	@Override
	public String getTip() {
		// TODO Auto-generated method stub
		return "izdanje";
	}

	public ArrayList<Autor> getAutori() { return autori; }
	public void setAutori(ArrayList<Autor> autori) { this.autori = autori; }
	
}
