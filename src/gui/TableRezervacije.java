package gui;

import java.awt.Color;
import java.awt.Dimension;
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
		this.setFont(new Font("SegoeUI", Font.BOLD, 12));
		this.getTableHeader().setFont(new Font("SegoeUI", Font.BOLD, 14));
		this.getTableHeader().setOpaque(false);
		this.getTableHeader().setBackground(new Color(0, 162, 237));
		this.getTableHeader().setForeground(new Color(255, 255, 255));
		this.setIntercellSpacing(new Dimension(0, 0));
		this.setRowHeight(25);
		this.setSelectionBackground(new Color(232, 57, 95));
		//this.setShowVerticalLines(false);
	}
}
