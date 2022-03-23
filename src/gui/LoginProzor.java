package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

import org.jdesktop.swingx.prompt.PromptSupport;
import logic.Login;
import logic.ToSignupProzor;

public class LoginProzor {

	private JFrame frame;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginProzor window = new LoginProzor();
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
	public LoginProzor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(700, 200, 353, 316);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel prijavaLabel = new JLabel("Prijavite se");
		prijavaLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		prijavaLabel.setBounds(26, 11, 166, 54);
		frame.getContentPane().add(prijavaLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		usernameTextField.setBounds(26, 70, 281, 35);
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		PromptSupport.setPrompt("Korisničko ime", usernameTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordTextField.setBounds(26, 120, 281, 35);
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		PromptSupport.setPrompt("Lozinka", passwordTextField);
		
		JButton loginButton = new JButton("Prijava");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginKomanda = new Login(usernameTextField.getText(), String.valueOf(passwordTextField.getPassword()), frame);
				loginKomanda.execute();
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(10, 169, 195));
		loginButton.setBounds(26, 179, 281, 40);
		loginButton.setBorderPainted(false);
		loginButton.setFocusPainted(false);
		frame.getContentPane().add(loginButton);
		
		JLabel registracijaLabel = new JLabel("Nemate nalog?");
		registracijaLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		registracijaLabel.setBounds(26, 231, 135, 40);
		frame.getContentPane().add(registracijaLabel);
		
		JButton singupButton = new JButton("Registracija");
		singupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToSignupProzor tsp = new ToSignupProzor(frame);
				tsp.execute();
			}
		});
		singupButton.setForeground(new Color(220, 20, 60));
		singupButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		singupButton.setFocusPainted(false);
		singupButton.setBorderPainted(false);
		singupButton.setBackground(new Color(255, 255, 255));
		singupButton.setBounds(163, 230, 164, 40);
		frame.getContentPane().add(singupButton);
	}
}
