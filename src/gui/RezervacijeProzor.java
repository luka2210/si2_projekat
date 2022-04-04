package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import korisnici.Student;
import javax.swing.JTable;

import javax.swing.JLabel;
import java.awt.Font;
import logic.TableModelRezervacije;

public class RezervacijeProzor {

	private Student student;
	private JFrame frame;
	private TableRezervacije aktivneRezervacije;
	private TableRezervacije istekleRezervacije;

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
		
		TableModelRezervacije model1 = new TableModelRezervacije(student, 0);
		aktivneRezervacije = new TableRezervacije(model1);
		aktivneRezervacije.setBounds(0, lblAktivneRezervacije.getHeight(), frame.getWidth(), min(model1.getRowCount() * 16 + 25, 122));
		JScrollPane container1 = new JScrollPane(aktivneRezervacije);
		container1.setBounds(aktivneRezervacije.getBounds());
		frame.getContentPane().add(container1);
		
		JLabel lblIstekleRezervacije = new JLabel("Istekle rezervacije");
		lblIstekleRezervacije.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIstekleRezervacije.setBounds(0, lblAktivneRezervacije.getHeight() + container1.getHeight(), 163, 25);
		frame.getContentPane().add(lblIstekleRezervacije);
		
		TableModelRezervacije model2 = new TableModelRezervacije(student, 1);
		istekleRezervacije = new TableRezervacije(model2);
		istekleRezervacije.setBounds(0, lblAktivneRezervacije.getHeight() + container1.getHeight() + lblAktivneRezervacije.getHeight(), frame.getWidth(), min(model2.getRowCount() * 16 + 25, 122));
		JScrollPane container2 = new JScrollPane(istekleRezervacije);
		container2.setBounds(istekleRezervacije.getBounds());
		frame.getContentPane().add(container2);
	}
	
	private int min(int i, int j) {
		// TODO Auto-generated method stub
		if (i < j)
			return i;
		return j;
	}
}
