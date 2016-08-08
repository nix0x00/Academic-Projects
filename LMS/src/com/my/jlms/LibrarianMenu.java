package com.my.jlms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibrarianMenu extends JFrame {
	
	private JPanel contentPane;
	private static LibrarianMenu frame;
	private static String[] images = { "passwd.png", "search.png", "books.png", "report.png"};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LibrarianMenu();
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
	public LibrarianMenu() {
		setTitle("Librarian");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 385, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManageBooks = new JButton("ManageBooks");
		btnManageBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnManageBooks.setBounds(22, 27, 146, 39);
		contentPane.add(btnManageBooks);
		
		JButton btnSearchBooks = new JButton("Search Books");
		btnSearchBooks.setBounds(202, 27, 146, 39);
		contentPane.add(btnSearchBooks);
		
		JButton btnPasswd = new JButton("Change Pass");
		btnPasswd.setBounds(202, 76, 146, 39);
		contentPane.add(btnPasswd);
		
		JButton btnViewReport = new JButton("View Reports");
		btnViewReport.setBounds(22, 129, 146, 39);
		contentPane.add(btnViewReport);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(202, 127, 146, 42);
		contentPane.add(btnLogout);
		
		JButton btnBorrow = new JButton("Borrow Books");
		btnBorrow.setBounds(22, 77, 146, 38);
		contentPane.add(btnBorrow);
		
		for(int i =0; i < images.length;) {
			btnPasswd.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
			btnSearchBooks.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
			btnManageBooks.setIcon(new ImageIcon(getClass().getResource(images[i])));
			btnBorrow.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
			btnViewReport.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
		}
		
		btnBorrow.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Borrow bor = new Borrow();
						bor.setVisible(true);
					}
				});
		
		btnPasswd.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ChangePwd framee = new ChangePwd();
						framee.setVisible(true);
					}
				});
		
		btnManageBooks.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent eve) {
						ManageBooks framee = new ManageBooks();
						framee.setVisible(true);
					}
				});
		
		btnSearchBooks.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						SearchBooks framee = new SearchBooks();
						framee.setVisible(true);
					}
				});
		
		btnLogout.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						MainLMS.mainPanel.setVisible(true);
					}
				});
	}

}
