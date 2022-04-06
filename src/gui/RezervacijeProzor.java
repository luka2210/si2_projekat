package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import korisnici.Korisnik;
import korisnici.Student;
import logic.MojeRezervacije;
import logic.OtkaziRezervaciju;
import logic.SveRezervacije;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RezervacijeProzor {

	private Korisnik korisnik;
	private JFrame frame;
	private TableRezervacije aktivneRezervacije;
	private TableRezervacije istekleRezervacije;
	
	class TableModelRezervacije extends DefaultTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7774125798733746749L;
		
		public TableModelRezervacije(Korisnik korisnik, int istekla) {
			addColumn("ID studenta");
			addColumn("Ime studenta");
			addColumn("ISBN dela");
			addColumn("Naziv dela");
			addColumn("Datum rezervacije");
			
			try {
				ResultSet rezervacije = null;
				
				if (korisnik.isStudent())
					rezervacije = MojeRezervacije.execute((Student) korisnik, istekla);
				else if (korisnik.isBibliotekar())
					rezervacije = SveRezervacije.execute(istekla);
				
				if (rezervacije == null) {
					ErrorBox.show("Greška u bazi podataka.", "database error");
					return;
				}
				
				while (rezervacije.next())
					addRow(new Object[] {rezervacije.getString("id"), rezervacije.getString("ime_prezime"), rezervacije.getString("isbn"), 
										 rezervacije.getString("naziv"), rezervacije.getDate("datum").toString()});
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorBox.show("Student nije pronadjen u bazi podataka.", "sql greška");
				e.printStackTrace();
			}
			
			if (istekla == 0) 
				addColumn(" ");
		}
		
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return column == 5;
	    }
	}
	
	class TableRezervacije extends JTable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3891048896810228636L;

		class ButtonEditor extends DefaultCellEditor {	
			/**
			 * 
			 */
			private static final long serialVersionUID = -7870350416780308742L;

			public ButtonEditor(JCheckBox checkBox) {
				super(checkBox);
			}
			
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				return new ButtonRenderer(table);
			}
		}

		class ButtonRenderer extends JButton implements TableCellRenderer {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7643970598976675447L;
			private ButtonListener bListener = new ButtonListener();
			private JTable table;
			
			public ButtonRenderer(JTable table) {
				setOpaque(true);
				setText("Otkaži");
				setForeground(Color.WHITE);
				setFont(new Font("Dialog", Font.BOLD, 14));
				setFocusPainted(false);
				setBorderPainted(false);
				setBackground(new Color(0, 162, 237));
				addActionListener(bListener);
				this.table = table;
			}
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// TODO Auto-generated method stub
				return this;
			}
			
			class ButtonListener implements ActionListener{
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
					String isbn = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
					System.out.println(id + " " + isbn);
					OtkaziRezervaciju.execute(frame, korisnik, id, isbn);
				} 
			}
		}

		
		public TableRezervacije(TableModel tm) {
			super(tm);
			this.setFont(new Font("SegoeUI", Font.BOLD, 12));
			this.getTableHeader().setFont(new Font("SegoeUI", Font.BOLD, 12));
			this.getTableHeader().setOpaque(false);
			this.getTableHeader().setBackground(new Color(0, 162, 237));
			this.getTableHeader().setForeground(new Color(255, 255, 255));
			this.setIntercellSpacing(new Dimension(0, 0));
			this.setRowHeight(25);
			this.setSelectionBackground(new Color(232, 57, 95));
			//this.setShowVerticalLines(false);
			
			try {
				this.getColumn(" ").setCellEditor(new ButtonEditor(new JCheckBox()));
				this.getColumn(" ").setCellRenderer(new ButtonRenderer(this));
			}
			catch (IllegalArgumentException e) {}
		}
	}
	
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
		frame.setBounds(100, 100, 900, 330);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAktivneRezervacije = new JLabel("Aktivne rezervacije");
		lblAktivneRezervacije.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAktivneRezervacije.setBounds(0, 0, 163, 25);
		frame.getContentPane().add(lblAktivneRezervacije);
		
		TableModelRezervacije model1 = new TableModelRezervacije(korisnik, 0);
		aktivneRezervacije = new TableRezervacije(model1);
		aktivneRezervacije.setBounds(0, lblAktivneRezervacije.getHeight(), frame.getWidth(), min(model1.getRowCount() * aktivneRezervacije.getRowHeight() + 22, 300));
		JScrollPane container1 = new JScrollPane(aktivneRezervacije);
		container1.setBounds(aktivneRezervacije.getBounds());
		frame.getContentPane().add(container1);
		
		JLabel lblIstekleRezervacije = new JLabel("Istekle rezervacije");
		lblIstekleRezervacije.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIstekleRezervacije.setBounds(0, lblAktivneRezervacije.getHeight() + container1.getHeight(), 163, 25);
		frame.getContentPane().add(lblIstekleRezervacije);
		
		TableModelRezervacije model2 = new TableModelRezervacije(korisnik, 1);
		istekleRezervacije = new TableRezervacije(model2);
		istekleRezervacije.setBounds(0, lblAktivneRezervacije.getHeight() + container1.getHeight() + lblAktivneRezervacije.getHeight(), frame.getWidth(), min(model2.getRowCount() * aktivneRezervacije.getRowHeight() + 22, 300));
		JScrollPane container2 = new JScrollPane(istekleRezervacije);
		container2.setBounds(istekleRezervacije.getBounds());
		frame.getContentPane().add(container2);
		
		frame.setBounds(100, 100, 900, container2.getY() + container2.getHeight() + 70);
	}
	
	private int min(int i, int j) {
		// TODO Auto-generated method stub
		if (i < j)
			return i;
		return j;
	}
}