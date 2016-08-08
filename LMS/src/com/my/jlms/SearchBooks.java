package com.my.jlms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import java.awt.Font;
import javax.swing.JButton;
import com.my.classes.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.util.ArrayList;

public class SearchBooks extends JFrame {

	private JPanel contentPane;
	private static SearchBooks frame;
	private JTextField txtIsbn;
	private Validation v = new Validation();
	private JTable table;
	private Database base = new Database();
	DefaultTableModel dtm = new DefaultTableModel();
	Books book = new Books();
	private JButton btnExit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SearchBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchBooks() {
		setTitle("Search Books from Database");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnExit = new JButton("Exit");
		contentPane.add(btnExit);
		
		JLabel lblBookName = new JLabel("Search Book:");
		lblBookName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBookName.setBounds(23, 11, 80, 14);
		contentPane.add(lblBookName);
		
		txtIsbn = new JTextField();
		txtIsbn.setBounds(113, 8, 202, 20);
		contentPane.add(txtIsbn);
		txtIsbn.setColumns(10);
		
		JButton btnSearch = new JButton("Filter");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(338, 7, 103, 23);
		contentPane.add(btnSearch);
		
		Object[] title = {"ISBN", "Name", "Author", "Shelf No", "Row No", "Col No"};
		
		dtm.setColumnIdentifiers(title);
		table = new JTable(dtm);
		table.setBounds(23, 55, 435, 217);
		table.setModel(dtm);
		JScrollPane scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setForeground(Color.RED);
		table.setRowHeight(30);
		contentPane.add(scroll);
		
		pack();
		final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
		table.setRowSorter(sorter);
		fillTable();

		txtIsbn.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent ev) {
						if(ev.getKeyCode() == KeyEvent.VK_ENTER) {
							if(v.validateIsbn(txtIsbn.getText())  || v.validateAddress(txtIsbn.getText())) {
								String text = txtIsbn.getText();
								try {
									sorter.setRowFilter(RowFilter.regexFilter(text));
								}
								catch(PatternSyntaxException pse) {
									JOptionPane.showMessageDialog(null, pse.getMessage(),"Bad Regex Patern",JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Error! Please check your input");
							}
						}
					}
				});
		
		btnSearch.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String text = txtIsbn.getText();
						if(v.validateIsbn(text)  || v.validateAddress(text)) {
							try {
								sorter.setRowFilter(RowFilter.regexFilter(text));
							}
							catch(PatternSyntaxException pse) {
								JOptionPane.showMessageDialog(null, pse.getMessage(),"Bad Regex Patern",JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Error! Please check your input");
						}
					}
				});
		
		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
	}
	
	/*
	 * this method will retrieve all objects of books from database.
	 * and then add that to table.
	 */
	public void fillTable() {
		List<Books> list = new ArrayList<Books>();
		list = base.searchBook();
		int n = list.size();
		if(n <= 0) {
			
		}else {
			for(int i = 0; i < n; i++) {
				book = list.get(i);
				Object[] fill = { book.getIsbn(),book.getBookName(),book.getAuthorName(),book.getShelfNo(),book.getRowNo(),book.getColNo() };
				dtm.addRow(fill);
			}
		}
	}
}
