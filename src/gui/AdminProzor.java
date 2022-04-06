package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdesktop.swingx.prompt.PromptSupport;

import korisnici.Admin;
import logic.Signup;

public class AdminProzor {

	private JFrame frame;
	private Admin admin;
	private JTextField imeTextField;
	private JTextField prezimeTextField;
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JComboBox<String> comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void launch(Admin admin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminProzor window = new AdminProzor(admin);
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
	public AdminProzor(Admin admin) {
		this.admin = admin;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 162, 237));
		frame.setBounds(700, 200, 819, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel navbar = new JPanel();
		navbar.setBounds(0, 0, frame.getWidth(), 50);
		navbar.setBackground(new Color(36, 37, 42));
		frame.getContentPane().add(navbar);
		navbar.setLayout(null);
		
		JButton btnRezervacije = new JButton("Moje rezervacije");
		btnRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RezervacijeProzor.launch(student);
			}
		});
		btnRezervacije.setForeground(Color.WHITE);
		btnRezervacije.setFont(new Font("Dialog", Font.PLAIN, 25));
		//btnOdjava.setBorder(new RoundBtn(30)); 
		btnRezervacije.setFocusPainted(false);
		btnRezervacije.setBorderPainted(false);
		btnRezervacije.setBackground(new Color(36, 37, 42));
		btnRezervacije.setBounds(320, 1, 220, 50);
		navbar.add(btnRezervacije);
		
		JLabel lblEbiblioteka = new JLabel("E-BIBLIOTEKA 🕮");
		lblEbiblioteka.setForeground(Color.WHITE);
		lblEbiblioteka.setFont(new Font("Dialog", Font.BOLD, 30));
		lblEbiblioteka.setBounds(12, 0, 265, 49);
		navbar.add(lblEbiblioteka);
		
		JButton btnNalog = new JButton("Odjavite se");
		btnNalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				LoginProzor.main(null);
			}
		});
		btnNalog.setForeground(Color.WHITE);
		btnNalog.setFont(new Font("Dialog", Font.PLAIN, 25));
		btnNalog.setFocusPainted(false);
		btnNalog.setBorderPainted(false);
		btnNalog.setBackground(new Color(36, 37, 42));
		btnNalog.setBounds(635, 1, 174, 50);
		navbar.add(btnNalog);
		
		JLabel labelSlika = new JLabel("");
		labelSlika.setBounds(0, 50, 398, 551);
		Image img = new ImageIcon(StudentProzor.class.getResource("/slike/bg/bg2.jpg")).getImage();
		Image newImg = img.getScaledInstance(labelSlika.getWidth(), labelSlika.getHeight(), java.awt.Image.SCALE_SMOOTH);
		labelSlika.setIcon(new ImageIcon(newImg));
		frame.getContentPane().add(labelSlika);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(labelSlika.getWidth(), navbar.getHeight(), (int)Math.round(frame.getWidth() / 2), frame.getHeight() - navbar.getHeight());
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDodajKorisnika = new JLabel("Dodaj korisnika");
		lblDodajKorisnika.setFont(new Font("Dialog", Font.BOLD, 28));
		lblDodajKorisnika.setBounds(20, 11, 265, 54);
		panel.add(lblDodajKorisnika);
		
		imeTextField = new JTextField();
		imeTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		imeTextField.setColumns(10);
		imeTextField.setBounds(20, 80, 350, 35);
		panel.add(imeTextField);
		PromptSupport.setPrompt("Ime", imeTextField);
		
		prezimeTextField = new JTextField();
		prezimeTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		prezimeTextField.setColumns(10);
		prezimeTextField.setBounds(20, 140, 350, 35);
		panel.add(prezimeTextField);
		PromptSupport.setPrompt("Prezime", prezimeTextField);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(20, 200, 350, 35);
		panel.add(usernameTextField);
		PromptSupport.setPrompt("Korisničko ime", usernameTextField);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		emailTextField.setColumns(10);
		emailTextField.setBounds(20, 260, 350, 35);
		panel.add(emailTextField);
		PromptSupport.setPrompt("E-mail", emailTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(20, 320, 350, 35);
		panel.add(passwordTextField);
		PromptSupport.setPrompt("Lozinka", passwordTextField);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(20, 380, 350, 35);
		comboBox.addItem("Student");
		comboBox.addItem("Bibliotekar");
		comboBox.addItem("Admin");
		comboBox.setSelectedIndex(0);
		panel.add(comboBox);
		
		JButton signupButton = new JButton("Registracija");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup signup = new Signup(imeTextField.getText(), prezimeTextField.getText(), usernameTextField.getText(), 
						String.valueOf(passwordTextField.getPassword()), emailTextField.getText(), (String) comboBox.getSelectedItem());
				signup.execute();
			}
		});
		signupButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		signupButton.setForeground(new Color(255, 255, 255));
		signupButton.setBackground(new Color(0, 162, 237));
		signupButton.setBounds(20, 440, 350, 40);
		signupButton.setBorderPainted(false);
		signupButton.setFocusPainted(false);
		panel.add(signupButton);
		
	}

}
