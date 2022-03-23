package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import korisnici.Bibliotekar;

public class BibliotekarProzor {

	private Bibliotekar bibliotekar;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(Bibliotekar bibliotekar) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BibliotekarProzor window = new BibliotekarProzor(bibliotekar);
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
	public BibliotekarProzor() {
		initialize();
	}
	
	public BibliotekarProzor(Bibliotekar bibliotekar) {
		this.bibliotekar = bibliotekar;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
