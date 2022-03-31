package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import korisnici.Student;
import logic.Pretraga;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jdesktop.swingx.prompt.PromptSupport;

import knjige.*;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
public class StudentProzor {
	
	private Student student;
	private JFrame frame;
	private JTextField nazivTextField;
	private JTextField autorTextField;
	private JTextField mentorTextField;
	private JTextField godinaTextField;
	private JTextField izdavacTextField;
	private JTextField isbnTextField;
	private JComboBox<Knjiga> comboBox;
	
	/**
	 * Launch the application.
	 */
	
	public static void launch(Student student) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentProzor window = new StudentProzor(student);
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

	public StudentProzor (Student student) {
		this.student = student;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 162, 237));
		frame.setBounds(700, 200, 900, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel navbar = new JPanel();
		navbar.setBounds(0, 0, frame.getWidth(), 50);
		navbar.setBackground(new Color(36, 37, 42));
		frame.getContentPane().add(navbar);
		navbar.setLayout(null);
		
		JButton btnRezervacije = new JButton("Rezervacije");
		btnRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RezervacijeProzor.launch(student);
			}
		});
		btnRezervacije.setForeground(Color.WHITE);
		btnRezervacije.setFont(new Font("Dialog", Font.PLAIN, 25));
		//btnOdjava.setBorder(new RoundBtn(30)); 
		btnRezervacije.setFocusPainted(false);
		btnRezervacije.setBorderPainted(false);
		btnRezervacije.setBackground(new Color(36, 37, 42));
		btnRezervacije.setBounds(390, 1, 168, 50);
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
		btnNalog.setBounds(714, 0, 174, 50);
		navbar.add(btnNalog);
		
		JLabel labelSlika = new JLabel("");
		labelSlika.setBounds(0, navbar.getHeight(), (int)Math.round(frame.getWidth() / 2), frame.getHeight() - navbar.getHeight());
		Image img = new ImageIcon(StudentProzor.class.getResource("/slike/bg/bg2.jpg")).getImage();
		Image newImg = img.getScaledInstance(labelSlika.getWidth(), labelSlika.getHeight(), java.awt.Image.SCALE_SMOOTH);
		labelSlika.setIcon(new ImageIcon(newImg));
		frame.getContentPane().add(labelSlika);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(labelSlika.getWidth(), navbar.getHeight(), (int)Math.round(frame.getWidth() / 2), frame.getHeight() - navbar.getHeight());
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		nazivTextField = new JTextField();
		nazivTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		nazivTextField.setColumns(10);
		nazivTextField.setBounds(47, 80, 350, 35);
		panel.add(nazivTextField);
		PromptSupport.setPrompt("Naziv knjige", nazivTextField);
		
		autorTextField = new JTextField();
		autorTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		autorTextField.setColumns(10);
		autorTextField.setBounds(47, 140, 350, 35);
		panel.add(autorTextField);
		PromptSupport.setPrompt("Autor", autorTextField);
		
		mentorTextField = new JTextField();
		mentorTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		mentorTextField.setColumns(10);
		mentorTextField.setBounds(47, 200, 350, 35);
		panel.add(mentorTextField);
		PromptSupport.setPrompt("Mentor", mentorTextField);
		
		godinaTextField = new JTextField();
		godinaTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		godinaTextField.setColumns(10);
		godinaTextField.setBounds(47, 260, 350, 35);
		panel.add(godinaTextField);
		PromptSupport.setPrompt("Godina izdanja", godinaTextField);
		
		izdavacTextField = new JTextField();
		izdavacTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		izdavacTextField.setColumns(10);
		izdavacTextField.setBounds(47, 320, 350, 35);
		panel.add(izdavacTextField);
		PromptSupport.setPrompt("Izdavač", izdavacTextField);
		
		isbnTextField = new JTextField();
		isbnTextField.setFont(new Font("Dialog", Font.PLAIN, 17));
		isbnTextField.setColumns(10);
		isbnTextField.setBounds(47, 380, 350, 35);
		panel.add(isbnTextField);
		PromptSupport.setPrompt("ISBN broj", isbnTextField);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pretraga pretraga = new Pretraga(nazivTextField.getText(), autorTextField.getText(), mentorTextField.getText(), 
												 izdavacTextField.getText(), godinaTextField.getText(), isbnTextField.getText(), comboBox);
				pretraga.execute();
			}
		});
		btnPretraga.setForeground(Color.WHITE);
		btnPretraga.setFont(new Font("Dialog", Font.BOLD, 20));
		btnPretraga.setFocusPainted(false);
		btnPretraga.setBorderPainted(false);
		btnPretraga.setBackground(new Color(0, 162, 237));
		btnPretraga.setBounds(47, 440, 350, 40);
		panel.add(btnPretraga);
		
		JLabel lblPretragaKnjiga = new JLabel("Pretraga knjiga");
		lblPretragaKnjiga.setFont(new Font("Dialog", Font.BOLD, 28));
		lblPretragaKnjiga.setBounds(46, 12, 265, 54);
		panel.add(lblPretragaKnjiga);
		
		comboBox = new JComboBox<Knjiga>();
		comboBox.setBounds(47, 513, 350, 35);
		panel.add(comboBox);
		
		JButton btnPrikaziKnjigu = new JButton("Prikaži knjigu");
		btnPrikaziKnjigu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() == null) {
						ErrorBox.show("Niste odabrali nijednu knjigu.", "null");
						return;
					}
				KnjigaProzor.launch(student, (Knjiga) comboBox.getSelectedItem());
			}
		});
		btnPrikaziKnjigu.setForeground(Color.WHITE);
		btnPrikaziKnjigu.setFont(new Font("Dialog", Font.BOLD, 20));
		btnPrikaziKnjigu.setFocusPainted(false);
		btnPrikaziKnjigu.setBorderPainted(false);
		btnPrikaziKnjigu.setBackground(new Color(0, 162, 237));
		btnPrikaziKnjigu.setBounds(47, 584, 350, 40);
		panel.add(btnPrikaziKnjigu);
		
	}
}