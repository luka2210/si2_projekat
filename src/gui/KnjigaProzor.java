package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import knjige.Knjiga;
import korisnici.Korisnik;
import logic.Rezervacija;
import pisci.Pisac;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KnjigaProzor {

	private JFrame frame;
	private Korisnik korisnik;
	private Knjiga knjiga;
	
	private JLabel lblSlika;
	private JLabel lblSlika2;
	private JLabel lblNaziv;
	private JLabel lblGodinaIzdanja;
	private JLabel lblTip;
	private JLabel lblIzdavac;
	private JLabel lblIsbn;
	private JLabel lblBroj;
	private JLabel lblPisci;
	/**
	 * Launch the application.
	 */
	public static void launch(Korisnik korisnik, Knjiga knjiga) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KnjigaProzor window = new KnjigaProzor(korisnik, knjiga);
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
	public KnjigaProzor(Korisnik korisnik, Knjiga knjiga) {
		this.korisnik = korisnik;
		this.knjiga = knjiga;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(500, 300, 1250, 635);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 600);
		panel.setBackground(Color.black);
		frame.getContentPane().add(panel);
		
		lblSlika = new JLabel("");
		lblSlika.setBounds(15, 15, panel.getWidth()-30, panel.getHeight()-30);
		panel.setLayout(null);
		panel.add(lblSlika);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(450, 0, 450, 600);
		panel2.setBackground(Color.black);
		frame.getContentPane().add(panel2);
		
		lblSlika2 = new JLabel("");
		lblSlika2.setBounds(15, 15, panel2.getWidth()-30, panel2.getHeight()-30);
		panel2.setLayout(null);
		panel2.add(lblSlika2);
		
		lblNaziv = new JLabel("Naziv: ");
		lblNaziv.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNaziv.setBounds(906, 326, 354, 35);
		frame.getContentPane().add(lblNaziv);
		
		lblGodinaIzdanja = new JLabel("Godina izdanja:");
		lblGodinaIzdanja.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblGodinaIzdanja.setBounds(906, 359, 275, 35);
		frame.getContentPane().add(lblGodinaIzdanja);
		
		lblTip = new JLabel("Tip: ");
		lblTip.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblTip.setBounds(906, 394, 275, 35);
		frame.getContentPane().add(lblTip);
		
		lblIzdavac = new JLabel("Izdavač: ");
		lblIzdavac.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblIzdavac.setBounds(906, 431, 275, 35);
		frame.getContentPane().add(lblIzdavac);
		
		lblIsbn = new JLabel("ISBN broj: ");
		lblIsbn.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblIsbn.setBounds(906, 466, 275, 35);
		frame.getContentPane().add(lblIsbn);
		
		lblBroj = new JLabel("Broj dostupnih primeraka: ");
		lblBroj.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblBroj.setBounds(906, 499, 275, 35);
		frame.getContentPane().add(lblBroj);
		
		JButton btnRezervacija = new JButton("Rezerviši");
		btnRezervacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rezervacija rezervacija = new Rezervacija(korisnik, knjiga, frame);
				rezervacija.execute();
			}
		});
		btnRezervacija.setForeground(Color.WHITE);
		btnRezervacija.setFont(new Font("Dialog", Font.BOLD, 20));
		btnRezervacija.setFocusPainted(false);
		btnRezervacija.setBorderPainted(false);
		btnRezervacija.setBackground(new Color(0, 162, 237));
		btnRezervacija.setBounds(906, 545, 282, 40);
		frame.getContentPane().add(btnRezervacija);
		
		lblPisci = new JLabel("");
		lblPisci.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblPisci.setBounds(906, 0, 275, 480);
		frame.getContentPane().add(lblPisci);
		
		ispisKnjige();
	}
	
	private void ispisKnjige() {
		Image img = new ImageIcon(KnjigaProzor.class.getResource(knjiga.getSlikaPath())).getImage();
		Image scaledImg = img.getScaledInstance(lblSlika.getWidth(), lblSlika.getHeight(), java.awt.Image.SCALE_SMOOTH);
		lblSlika.setIcon(new ImageIcon(scaledImg));
		
		Image img2 = new ImageIcon(KnjigaProzor.class.getResource(knjiga.getSlikaPath2())).getImage();
		Image scaledImg2 = img2.getScaledInstance(lblSlika2.getWidth(), lblSlika2.getHeight(), java.awt.Image.SCALE_SMOOTH);
		lblSlika2.setIcon(new ImageIcon(scaledImg2));
		
		lblNaziv.setText("Naziv: " + knjiga.getNaziv());
		lblGodinaIzdanja.setText("Godina izdanja: " + Integer.toString(knjiga.getGodina()));
		lblTip.setText("Tip: " + knjiga.getTip());
		lblIzdavac.setText("Izdavač: " + knjiga.getIzdavac());
		lblIsbn.setText("ISBN: " + Integer.toString(knjiga.getIsbn()));
		lblBroj.setText("Broj preostalih primeraka: " + Integer.toString(knjiga.brojPreostalihKnjiga()));
		
		ArrayList<Pisac> pisci = knjiga.getPisci();
		lblPisci.setText(getPisciText(pisci));
		lblPisci.setBounds(456, 0, 275, pisci.size() * 35);
	}
	
	private String getPisciText(ArrayList<Pisac> pisci) {
		String text = "";
		for (Pisac pisac: pisci) 
			text += pisac.toString() + " <br> ";
		return "<html>" + text + "</html>";
	}
}
