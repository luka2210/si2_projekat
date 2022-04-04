package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import korisnici.Korisnik;
import korisnici.Student;
import logic.MojeRezervacije;
import logic.SveRezervacije;

public class TableModelRezervacije extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7774125798733746749L;
	
	public TableModelRezervacije(Korisnik korisnik, int istekla) {
		addColumn("Student");
		addColumn("Naziv dela");
		addColumn("Datum rezervacije");
		
		try {
			ResultSet rezervacije = null;
			
			if (korisnik.isStudent())
				rezervacije = MojeRezervacije.execute((Student) korisnik, istekla);
			else if (korisnik.isBibliotekar())
				rezervacije = SveRezervacije.execute(istekla);
			
			if (rezervacije == null) {
				ErrorBox.show("Greška u bazi podataka.", "database error");
				return;
			}
			
			while (rezervacije.next())
				addRow(new Object[] {rezervacije.getString("ime_prezime"), rezervacije.getString("naziv"), rezervacije.getDate("datum").toString()});
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorBox.show("Student nije pronadjen u bazi podataka.", "sql greška");
			e.printStackTrace();
		}
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}
