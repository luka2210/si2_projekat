package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Communicator;
import gui.ErrorBox;
import knjige.Knjiga;
import knjige.KnjigaBuilder;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class Pretraga implements Komanda {
	private String naziv, autor, mentor, izdavac, godina, isbn;
	private JComboBox<Knjiga> comboBox;
	
	public Pretraga(String naziv, String autor, String mentor, String izdavac, String godina, String isbn, JComboBox<Knjiga> comboBox) {
		super();
		this.naziv = naziv;
		this.autor = autor;
		this.mentor = mentor;
		this.izdavac = izdavac;
		this.godina = godina;
		this.isbn = isbn;
		this.comboBox = comboBox;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		comboBox.removeAllItems();
		if (!checkNumberInputs())
			return;
		try {
			ResultSet knjige = getSearchResults();
			while(knjige.next()) 
				comboBox.addItem(KnjigaBuilder.getInstance(knjige));
			if (comboBox.getItemCount() == 0)
				ErrorBox.show("Nije pronadjena nijedna knjiga koja odgovara vаšim kriterijumima.", "nije pronadjena knjiga");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorBox.show("Došlo je do greške, pokušajte ponovo.", "sql greška");
			e.printStackTrace();
		}
	}
	
	private ResultSet getSearchResults() throws SQLException {
		ArrayList<String> varsList = new ArrayList<String>();
		
		String query = "SELECT DISTINCT k.* FROM knjige k ";
		query += "inner join knjige_pisci kp on k.isbn = kp.isbn ";
		query += "inner join pisci p on p.id = kp.id ";
		
		if (!naziv.isEmpty()) {
			query += "where k.naziv like ? ";
			varsList.add(rgx(naziv));
		}
		
		if (!autor.isEmpty()) {
			query += "and concat(p.ime, \" \", p.prezime) like ? ";
			query += "and p.tip = \"autor\" ";
			varsList.add(rgx(autor));
		}
		
		if (!mentor.isEmpty()) {
			query += "and concat(p.ime, \" \", p.prezime) like ? ";
			query += "and p.tip = \"mentor\" ";
			varsList.add(rgx(mentor));
		}
		
		if (!izdavac.isEmpty()) {
			query += "and k.izdavac like ? "; 
			varsList.add(rgx(izdavac));
		}
		
		if (!godina.isEmpty()) {
			query += "and k.godina = ? ";
			varsList.add(godina);
		}
		
		if (!isbn.isEmpty()) {
			query += "and k.isbn = ? ";
			varsList.add(isbn);
		}
		
		String[] vars = ArrayListToArray(varsList);
		return Communicator.executeQuery(query, vars);
	}
	
	private String rgx(String str) {
		return "%" + str + "%";
	}
	
	private String[] ArrayListToArray(ArrayList<String> varsList) {
		Object[] varsObj = varsList.toArray();
		String[] vars = new String[varsList.size()];
		for (int i = 0; i < vars.length; i++)
			vars[i] = (String) varsObj[i];
		return vars;
	}
	
	private boolean checkNumberInputs() {
		try {
			if (!godina.isEmpty())
				Integer.parseInt(godina);
			if (!isbn.isEmpty())
				Integer.parseInt(isbn);
			return true;
		} 
		catch (Exception e) {
			ErrorBox.show("Polja \"Godina izdanja\" i \"ISBN broj\" moraju biti brojevi!", "nepravilan unos");
			return false;
		}
	}
}
