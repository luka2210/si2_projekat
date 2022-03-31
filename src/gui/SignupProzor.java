package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdesktop.swingx.prompt.PromptSupport;

import logic.Signup;
import logic.ToLoginProzor;

public class SignupProzor {

	private JFrame frame;
	private JTextField usernameTextField;
	private JTextField imeTextField;
	private JTextField prezimeTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupProzor window = new SignupProzor();
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
	public SignupProzor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(700, 200, 353, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel registracijaLabel = new JLabel("Registrujte se");
		registracijaLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		registracijaLabel.setBounds(26, 11, 207, 54);
		frame.getContentPane().add(registracijaLabel);
		
		imeTextField = new JTextField();
		imeTextField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		imeTextField.setBounds(26, 70, 281, 35);
		frame.getContentPane().add(imeTextField);
		imeTextField.setColumns(10);
		PromptSupport.setPrompt("Ime", imeTextField);
		
		prezimeTextField = new JTextField();
		prezimeTextField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		prezimeTextField.setBounds(26, 120, 281, 35);
		frame.getContentPane().add(prezimeTextField);
		prezimeTextField.setColumns(10);
		PromptSupport.setPrompt("Prezime", prezimeTextField);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		usernameTextField.setBounds(26, 170, 281, 35);
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		PromptSupport.setPrompt("Korisničko ime", usernameTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordTextField.setBounds(26, 220, 281, 35);
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		PromptSupport.setPrompt("Lozinka", passwordTextField);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		emailTextField.setBounds(26, 270, 281, 35);
		frame.getContentPane().add(emailTextField);
		emailTextField.setColumns(10);
		PromptSupport.setPrompt("E-mail adresa", emailTextField);
		
		JButton signupButton = new JButton("Registracija");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup signup = new Signup(imeTextField.getText(), prezimeTextField.getText(), usernameTextField.getText(), 
						String.valueOf(passwordTextField.getPassword()), emailTextField.getText(), frame);
				signup.execute();
			}
		});
		signupButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		signupButton.setForeground(new Color(255, 255, 255));
		signupButton.setBackground(new Color(0, 162, 237));
		signupButton.setBounds(26, 330, 281, 40);
		signupButton.setBorderPainted(false);
		signupButton.setFocusPainted(false);
		frame.getContentPane().add(signupButton);
		
		JLabel prijavaLabel = new JLabel("Imate nalog?");
		prijavaLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		prijavaLabel.setBounds(26, 390, 142, 40);
		frame.getContentPane().add(prijavaLabel);
		
		JButton loginButton = new JButton("Prijava");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToLoginProzor tlp = new ToLoginProzor(frame);
				tlp.execute();
			}
		});
		loginButton.setForeground(new Color(220, 20, 60));
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setBackground(new Color(255, 255, 255));
		loginButton.setBounds(165, 389, 142, 40);
		frame.getContentPane().add(loginButton);
	}

}
