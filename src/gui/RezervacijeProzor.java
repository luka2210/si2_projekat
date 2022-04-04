package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import korisnici.Korisnik;
import javax.swing.JLabel;
import java.awt.Font;

public class RezervacijeProzor {

	private Korisnik korisnik;
	private JFrame frame;
	private TableRezervacije aktivneRezervacije;
	private TableRezervacije istekleRezervacije;

	/**
	 * Launch the application.
	 */
	public static void launch(Korisnik korisnik) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RezervacijeProzor window = new RezervacijeProzor(korisnik);
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
	public RezervacijeProzor(Korisnik korisnik) {
		this.korisnik = korisnik;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 330);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAktivneRezervacije = new JLabel("Aktivne rezervacije");
		lblAktivneRezervacije.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAktivneRezervacije.setBounds(0, 0, 163, 25);
		frame.getContentPane().add(lblAktivneRezervacije);
		
		TableModelRezervacije model1 = new TableModelRezervacije(korisnik, 0);
		aktivneRezervacije = new TableRezervacije(model1);
		aktivneRezervacije.setBounds(0, lblAktivneRezervacije.getHeight(), frame.getWidth(), min(model1.getRowCount() * aktivneRezervacije.getRowHeight() + 25, 300));
		JScrollPane container1 = new JScrollPane(aktivneRezervacije);
		container1.setBounds(aktivneRezervacije.getBounds());
		frame.getContentPane().add(container1);
		
		JLabel lblIstekleRezervacije = new JLabel("Istekle rezervacije");
		lblIstekleRezervacije.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIstekleRezervacije.setBounds(0, lblAktivneRezervacije.getHeight() + container1.getHeight(), 163, 25);
		frame.getContentPane().add(lblIstekleRezervacije);
		
		TableModelRezervacije model2 = new TableModelRezervacije(korisnik, 1);
		istekleRezervacije = new TableRezervacije(model2);
		istekleRezervacije.setBounds(0, lblAktivneRezervacije.getHeight() + container1.getHeight() + lblAktivneRezervacije.getHeight(), frame.getWidth(), min(model2.getRowCount() * aktivneRezervacije.getRowHeight() + 25, 300));
		JScrollPane container2 = new JScrollPane(istekleRezervacije);
		container2.setBounds(istekleRezervacije.getBounds());
		frame.getContentPane().add(container2);
		
		frame.setBounds(100, 100, 700, container2.getY() + container2.getHeight() + 70);
	}
	
	private int min(int i, int j) {
		// TODO Auto-generated method stub
		if (i < j)
			return i;
		return j;
	}
}
