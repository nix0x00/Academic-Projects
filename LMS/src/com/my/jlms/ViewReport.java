package com.my.jlms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.my.classes.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.util.List;
import java.util.ArrayList;

public class ViewReport extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel dtm = new DefaultTableModel();
	private Report r = new Report();
	private Transaction a = new Transaction();
	private Librarian lib = new Librarian();
	private Guest g = new Guest();
	private Books book = new Books();
	private Database base = new Database();
	private List<Guest> gg = new ArrayList<Guest>();
	private List<Books> buk = new ArrayList<Books>();
	private List<Object> obj = new ArrayList<Object>();
	private ArrayList<Object> fill = new ArrayList<Object>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewReport frame = new ViewReport();
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
	public ViewReport() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//setBounds(100, 100, 656, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(null);
		
		table = new JTable(dtm);
		table.setModel(dtm);
		JScrollPane scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setBounds(10, 11, 620, 334);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		contentPane.add(scroll);
		//contentPane.add(table);
		pack();
		
		testing();
	}
	
	//method to test
	public void testing() {
		if(Report.value == 5) {
			//JOptionPane.showMessageDialog(null, "HEllo Librarian");
			Object[] name = { "Librarian Name", "Book Name", "Guest Name", "Guest ID", "Fine"};
			dtm.setColumnIdentifiers(name);
			
			List<Object> obj = new ArrayList<Object>();
			List<Transaction> adm = new ArrayList<Transaction>();
			List<Librarian> lb = new ArrayList<Librarian>();
			List<Guest> gg = new ArrayList<Guest>();
			List<Books> buk = new ArrayList<Books>();
			
			//object to fill with values
			ArrayList<Object> fill = new ArrayList<Object>();
			
			//retrieving all the objects from database.
			obj = base.getReport("lib");
			for(int i = 0; i < Database.t; i++) {
				lib = (Librarian) obj.get(i);
				lb.add(lib);
			}
			obj = base.getReport("guest");
			for(int i = 0; i < Database.t; i++) {
				g = (Guest) obj.get(i);
				gg.add(g);
			}
			obj = base.getReport("Order");
			for(int i = 0; i < Database.t; i++) {
				a = (Transaction) obj.get(i);
				adm.add(a);
			}
			obj = base.getReport("books");
			for(int i = 0; i < Database.t; i++) {
				book = (Books) obj.get(i);
				buk.add(book);
			}
			
			for(int count = 0; count < Database.t; count++) {
				//"Librarian Name", "Book Name", "Guest Name", "Guest ID", "Fine"
				lib = lb.get(count);
				book = buk.get(count);
				g = gg.get(count);
				a = adm.get(count);
				fill.add(0, lib.getName());
				fill.add(1, book.getBookName());
				fill.add(2, g.getName());
				fill.add(3, g.getPassportNp());
				fill.add(4, a.getFine());
				dtm.addRow(fill.toArray());
			}
		}else if(Report.value == 4) {
			//set get data for table
			Object[] name = { "Guest Name", "Gender", "Book Borrowed"};
			dtm.setColumnIdentifiers(name);
			
			
			obj = base.getReport("guest");
			for(int i = 0; i < Database.t; i++) {
				g = (Guest) obj.get(i);
				gg.add(g);
			}
			obj = base.getReport("books");
			for(int i = 0; i < Database.t; i++) {
				book = (Books) obj.get(i);
				buk.add(book);
			}
			
			for(int count = 0; count < Database.t; count++) {
				g = gg.get(count);
				book = buk.get(count);
				fill.add(0, g.getName());
				fill.add(1, g.getGender());
				fill.add(2, book.getBookName());
				dtm.addRow(fill.toArray());
			}
		}else if(Report.value == 3) {
			//JOptionPane.showMessageDialog(null, "HEllo Day");
			Object[] name = { "Librarian Name", "Book Name", "Guest Name", "Guest ID", "Issued Day", "Return Day"};
			dtm.setColumnIdentifiers(name);
			
			//object to retrieve the objects from database.
			List<Object> obj = new ArrayList<Object>();
			List<Transaction> adm = new ArrayList<Transaction>();
			List<Librarian> lb = new ArrayList<Librarian>();
			List<Guest> gg = new ArrayList<Guest>();
			List<Books> buk = new ArrayList<Books>();
			
			//object to fill with values
			ArrayList<Object> fill = new ArrayList<Object>();
			
			//retrieving all the objects from database.
			obj = base.getReport("lib");
			for(int i = 0; i < Database.t; i++) {
				lib = (Librarian) obj.get(i);
				lb.add(lib);
			}
			obj = base.getReport("guest");
			for(int i = 0; i < Database.t; i++) {
				g = (Guest) obj.get(i);
				gg.add(g);
			}
			obj = base.getReport("Order");
			for(int i = 0; i < Database.t; i++) {
				a = (Transaction) obj.get(i);
				adm.add(a);
			}
			obj = base.getReport("books");
			for(int i = 0; i < Database.t; i++) {
				book = (Books) obj.get(i);
				buk.add(book);
			}
		
			for(int count = 0; count < Database.t; count++) {
				lib = lb.get(count);
				book = buk.get(count);
				g = gg.get(count);
				a = adm.get(count);
				fill.add(0,lib.getName());
				fill.add(1,book.getBookName());
				fill.add(2,g.getName());
				fill.add(3, g.getPassportNp());
				fill.add(4, a.getBorrowDate());
				fill.add(5, a.getReturnDate());
				dtm.addRow(fill.toArray());
			}
			
		}else if(Report.value == 2) {
			//JOptionPane.showMessageDialog(null, "HEllo Age");
			Object[] name = { "Guest Name", "Gender", "Guest ID", "Age"};
			dtm.setColumnIdentifiers(name);
			
			List<Guest> gg = new ArrayList<Guest>();
			ArrayList<Object> fill = new ArrayList<Object>();
			List<Object> obj = new ArrayList<Object>();
			
			obj = base.getReport("guest");
			for(int i = 0; i < Database.t; i++) {
				g = (Guest) obj.get(i);
				gg.add(g);
			}
			for(int i = 0; i < Database.t; i++) {
				g = gg.get(i);
				fill.add(0, g.getName());
				fill.add(1, g.getGender());
				fill.add(2, g.getPassportNp());
				fill.add(3, g.getAge());
				dtm.addRow(fill.toArray());
			}
		}else if(Report.value == 1) {
			//JOptionPane.showMessageDialog(null, "HEllo Borrowed");
			Object[] name = { "Order ID", "Librarian Name", "Book Name", "Guest Name", "Guest Phone", "Issued Date", "Return Date", "Fine"};
			dtm.setColumnIdentifiers(name);
			
			//object to retrieve the objects from database.
			List<Object> obj = new ArrayList<Object>();
			List<Transaction> adm = new ArrayList<Transaction>();
			List<Librarian> lb = new ArrayList<Librarian>();
			List<Guest> gg = new ArrayList<Guest>();
			List<Books> buk = new ArrayList<Books>();
			
			//object to fill with values
			ArrayList<Object> fill = new ArrayList<Object>();
			
			//retrieving all the objects from database.
			obj = base.getReport("lib");
			for(int i = 0; i < Database.t; i++) {
				lib = (Librarian) obj.get(i);
				lb.add(lib);
			}
			obj = base.getReport("guest");
			for(int i = 0; i < Database.t; i++) {
				g = (Guest) obj.get(i);
				gg.add(g);
			}
			obj = base.getReport("Order");
			for(int i = 0; i < Database.t; i++) {
				a = (Transaction) obj.get(i);
				adm.add(a);
			}
			obj = base.getReport("books");
			for(int i = 0; i < Database.t; i++) {
				book = (Books) obj.get(i);
				buk.add(book);
			}
			
			for(int i = 0; i < Database.t; i++) {
				//"Order ID", "Librarian Name", "Book Name", "Guest Name", "Guest Phone", "Issued Date", "Return Date", "Fine"
				a = adm.get(i);
				lib = lb.get(i);
				book = buk.get(i);
				g = gg.get(i);
				fill.add(0, a.getOrderID());
				fill.add(1, lib.getName());
				fill.add(2, book.getBookName());
				fill.add(3, g.getName());
				fill.add(4, g.getPhoneNo());
				fill.add(5, a.getBorrowDate());
				fill.add(6, a.getReturnDate());
				fill.add(7, a.getFine());
				dtm.addRow(fill.toArray());
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
}
