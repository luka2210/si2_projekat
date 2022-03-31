package gui;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import korisnici.Student;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import db.Communicator;
import javax.swing.JLabel;
import java.awt.Font;

public class RezervacijeProzor {

	private Student student;
	private JFrame frame;
	private JTable aktivneRezervacije;
	private JTable istekleRezervacije;

	/**
	 * Launch the application.
	 */
	public static void launch(Student student) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RezervacijeProzor window = new RezervacijeProzor(student);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RezervacijeProzor(Student student) {
		this.student = student;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 330);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAktivneRezervacije = new JLabel("Aktivne rezervacije");
		lblAktivneRezervacije.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAktivneRezervacije.setBounds(0, 0, 163, 25);
		frame.getContentPane().add(lblAktivneRezervacije);
		
		DefaultTableModel model1 = new DefaultTableModel();
		aktivneRezervacije = new JTable(model1);
		getRezervacije(model1, 0);
		aktivneRezervacije.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aktivneRezervacije.setBounds(0, lblAktivneRezervacije.getHeight(), frame.getWidth(), min(model1.getRowCount() * 16 + 23, 120));
		JScrollPane container1 = new JScrollPane(aktivneRezervacije);
		container1.setBounds(aktivneRezervacije.getBounds());
		frame.getContentPane().add(container1);
		
		JLabel lblIstekleRezervacije = new JLabel("Istekle rezervacije");
		lblIstekleRezervacije.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIstekleRezervacije.setBounds(0, lblAktivneRezervacije.getHeight() + container1.getHeight(), 163, 25);
		frame.getContentPane().add(lblIstekleRezervacije);
		
		DefaultTableModel model2 = new DefaultTableModel();
		istekleRezervacije = new JTable(model2);
		getRezervacije(model2, 1);
		istekleRezervacije.setFont(new Font("Tahoma", Font.PLAIN, 14));
		istekleRezervacije.setBounds(0, lblAktivneRezervacije.getHeight() + container1.getHeight() + lblAktivneRezervacije.getHeight(), frame.getWidth(), min(model2.getRowCount() * 16, 120));
		JScrollPane container2 = new JScrollPane(istekleRezervacije);
		container2.setBounds(istekleRezervacije.getBounds());
		frame.getContentPane().add(container2);
	}

	public void getRezervacije(DefaultTableModel model, int istekla) {
		model.addColumn("Student");
		model.addColumn("Naziv dela");
		model.addColumn("Datum rezervacije");
		
		String query = "select concat(s.ime, ' ', s.prezime) as ime_prezime, k.naziv as naziv, r.datum as datum from korisnici s ";
		query += "inner join rezervacije r on r.id = s.id ";
		query += "inner join knjige k on k.isbn = r.isbn ";
		query += "where s.id = ? and r.istekla = ?";
		String vars[] = {Integer.toString(student.getId()), Integer.toString(istekla)};
		
		try {
			ResultSet rezervacije = Communicator.executeQuery(query, vars);
			while (rezervacije.next())
				model.addRow(new Object[] {rezervacije.getString("ime_prezime"), rezervacije.getString("naziv"), rezervacije.getDate("datum").toString()});
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorBox.show("Student nije pronadjen u bazi podataka.", "sql greška");
			e.printStackTrace();
		}
	}
	
	private int min(int i, int j) {
		// TODO Auto-generated method stub
		if (i < j)
			return i;
		return j;
	}
}
