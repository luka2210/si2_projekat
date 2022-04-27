package knjige;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pisci.Autor;
import pisci.Mentor;
import pisci.Pisac;

public class Zavrsni extends Knjiga {
	private Autor autor;
	private ArrayList<Mentor> mentori;
	
	public Zavrsni(ResultSet knjiga, ResultSet autor, ResultSet mentori) throws SQLException {
		super(knjiga);
		// TODO Auto-generated constructor stub
		this.setAutor(autor);
		this.setMentori(mentori);
	}
	
	@Override
	public String getTip() {
		// TODO Auto-generated method stub
		return "zavrsni rad";
	}
	
	@Override
	public ArrayList<Pisac> getPisci() {
		// TODO Auto-generated method stub
		ArrayList<Pisac> pisci = new ArrayList<Pisac>();
		pisci.add(autor);
		for (Mentor mentor: mentori) 
			pisci.add(mentor);
		return pisci;
	}

	public Autor getAutor() { return autor; }
	public void setAutor(Autor autor) { this.autor = autor; }
	public ArrayList<Mentor> getMentori() { return mentori; }
	public void setMentori(ArrayList<Mentor> mentori) { this.mentori = mentori; }
	
	public void setAutor(ResultSet autor) throws SQLException { 
		autor.next();
		this.autor = new Autor(autor);
	}
	
	public void setMentori(ResultSet mentori) throws SQLException {
		this.mentori = new ArrayList<Mentor>();
		while (mentori.next()) 
			this.mentori.add(new Mentor(mentori));
	}
}
