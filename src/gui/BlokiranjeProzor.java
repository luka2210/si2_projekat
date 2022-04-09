package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import logic.Blokiraj;
import logic.SviKorisnici;

public class BlokiranjeProzor {

	private JFrame frame;
	private TableKorisnici blokiraniKorisnici;
	private TableKorisnici neblokiraniKorisnici;

	private class TableModelKorisnici extends DefaultTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1574257004609867248L;
		
		public TableModelKorisnici(int blokiran) {
			addColumn("ID studenta");
			addColumn("Ime studenta");
			addColumn("Korisničko ime");
			addColumn("E-mail");
			
			try {
				ResultSet rezervacije = SviKorisnici.execute(blokiran);
				
				while (rezervacije.next())
					addRow(new Object[] {rezervacije.getString("id"), rezervacije.getString("ime_prezime"), rezervacije.getString("username"), rezervacije.getString("email")});
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorBox.show("Student nije pronadjen u bazi podataka.", "sql greška");
				e.printStackTrace();
			}
			
			if (blokiran == 0) 
				addColumn(" ");
			else if (blokiran == 1) 
				addColumn("  ");
		}
		
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return column == 4;
	    }
	}
	
	private class TableKorisnici extends JTable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4774296172684240799L;

		class ButtonEditorBlokiraj extends DefaultCellEditor {	
			/**
			 * 
			 */
			private static final long serialVersionUID = 5698022156754604209L;

			public ButtonEditorBlokiraj(JCheckBox checkBox) {
				super(checkBox);
			}
			
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				return new ButtonRendererBlokiraj(table);
			}
		}

		class ButtonRendererBlokiraj extends JButton implements TableCellRenderer {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2424543730937035859L;
			private ButtonListener bListener = new ButtonListener();
			private JTable table;
			
			public ButtonRendererBlokiraj(JTable table) {
				setOpaque(true);
				setText("Blokiraj");
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
					Blokiraj.execute(frame, id, 1);
				} 
			}
		}
		
		class ButtonEditorOdblokiraj extends DefaultCellEditor {	
			/**
			 * 
			 */
			private static final long serialVersionUID = 5698022156754604209L;

			public ButtonEditorOdblokiraj(JCheckBox checkBox) {
				super(checkBox);
			}
			
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				return new ButtonRendererOdblokiraj(table);
			}
		}

		class ButtonRendererOdblokiraj extends JButton implements TableCellRenderer {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2424543730937035859L;
			private ButtonListener bListener = new ButtonListener();
			private JTable table;
			
			public ButtonRendererOdblokiraj(JTable table) {
				setOpaque(true);
				setText("Odblokiraj");
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
					Blokiraj.execute(frame, id, 0);
				} 
			}
		}
		
		public TableKorisnici(TableModel tm) {
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
				this.getColumn(" ").setCellEditor(new ButtonEditorBlokiraj(new JCheckBox()));
				this.getColumn(" ").setCellRenderer(new ButtonRendererBlokiraj(this));
			}
			catch (IllegalArgumentException e) {}
			try {
				this.getColumn("  ").setCellEditor(new ButtonEditorOdblokiraj(new JCheckBox()));
				this.getColumn("  ").setCellRenderer(new ButtonRendererOdblokiraj(this));
			}
			catch (IllegalArgumentException e) {}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlokiranjeProzor window = new BlokiranjeProzor();
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
	public BlokiranjeProzor() {
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
		
		JLabel lblStudenti = new JLabel("Neblokirani korisnici");
		lblStudenti.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudenti.setBounds(0, 0, 250, 25);
		frame.getContentPane().add(lblStudenti);
		
		TableModelKorisnici model1 = new TableModelKorisnici(0);
		neblokiraniKorisnici = new TableKorisnici(model1);
		neblokiraniKorisnici.setBounds(0, lblStudenti.getHeight(), frame.getWidth(), min(model1.getRowCount() * neblokiraniKorisnici.getRowHeight() + 22, 300));
		JScrollPane container1 = new JScrollPane(neblokiraniKorisnici);
		container1.setBounds(neblokiraniKorisnici.getBounds());
		frame.getContentPane().add(container1);
		
		JLabel lblNeodobreniNalozi = new JLabel("Blokirani korisnici");
		lblNeodobreniNalozi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNeodobreniNalozi.setBounds(0, lblStudenti.getHeight() + container1.getHeight(), 250, 25);
		frame.getContentPane().add(lblNeodobreniNalozi);
		
		TableModelKorisnici model2 = new TableModelKorisnici(1);
		blokiraniKorisnici = new TableKorisnici(model2);
		blokiraniKorisnici.setBounds(0, lblStudenti.getHeight() + container1.getHeight() + lblStudenti.getHeight(), frame.getWidth(), min(model2.getRowCount() * blokiraniKorisnici.getRowHeight() + 22, 300));
		JScrollPane container2 = new JScrollPane(blokiraniKorisnici);
		container2.setBounds(blokiraniKorisnici.getBounds());
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
