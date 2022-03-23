package knjige;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pisci.Autor;
import pisci.Editor;
import pisci.Pisac;

import java.util.AbstractCollection;

public class Monografija extends Knjiga {
	
	public Monografija(ResultSet knjiga) throws SQLException {
		super(knjiga);
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<Editor> editori;
	private ArrayList<Poglavlje> poglavlja;
	

	@Override
	public String getTip() {
		// TODO Auto-generated method stub
		return "monografsko izdanje";
	}
	
	@Override
	public ArrayList<Pisac> getPisci() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Editor> getEditori() { return editori; }
	public void setEditori(ArrayList<Editor> editori) { this.editori = editori; }
	public ArrayList<Poglavlje> getPoglavlja() { return poglavlja; }
	public void setPoglavlja(ArrayList<Poglavlje> poglavlja) { this.poglavlja = poglavlja; }
}
