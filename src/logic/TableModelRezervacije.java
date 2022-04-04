package logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import db.Communicator;
import gui.ErrorBox;
import korisnici.Student;

public class TableModelRezervacije extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7774125798733746749L;
	
	public TableModelRezervacije(Student student, int istekla) {
		addColumn("Student");
		addColumn("Naziv dela");
		addColumn("Datum rezervacije");
		
		String query = "select concat(s.ime, ' ', s.prezime) as ime_prezime, k.naziv as naziv, r.datum as datum from korisnici s ";
		query += "inner join rezervacije r on r.id = s.id ";
		query += "inner join knjige k on k.isbn = r.isbn ";
		query += "where s.id = ? and r.istekla = ?";
		String vars[] = {Integer.toString(student.getId()), Integer.toString(istekla)};
		
		try {
			ResultSet rezervacije = Communicator.executeQuery(query, vars);
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
       //all cells false
       return false;
    }
}
