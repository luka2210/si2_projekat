package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableRezervacije extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3891048896810228636L;
	
	public TableRezervacije(TableModel tm) {
		super(tm);
		getTableHeader().setFont(new Font("SegoeUI", Font.BOLD, 14));
		getTableHeader().setOpaque(false);
		getTableHeader().setBackground(new Color(0, 162, 237));
		getTableHeader().setForeground(new Color(255, 255, 255));
	}
}
