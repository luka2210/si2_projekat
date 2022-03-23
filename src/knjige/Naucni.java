package knjige;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.AbstractCollection;

import pisci.Autor;
import pisci.Editor;
import pisci.Pisac;

public class Naucni extends Knjiga{
	
	public Naucni(ResultSet knjiga) throws SQLException {
		super(knjiga);
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<Editor> editori;
	private ArrayList<Izdanje> izdanja;
	private Period period;

	@Override
	public String getTip() {
		// TODO Auto-generated method stub
		return "naučni časopis";
	}
	
	@Override
	public ArrayList<Pisac> getPisci() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Editor> getEditori() { return editori; }
	public void setEditori(ArrayList<Editor> editori) { this.editori = editori; }
	public ArrayList<Izdanje> getIzdanja() { return izdanja; }
	public void setIzdanja(ArrayList<Izdanje> izdanja) { this.izdanja = izdanja; }
	public Period getPeriod() { return period; }
	public void setPeriod(Period period) { this.period = period; }
}
