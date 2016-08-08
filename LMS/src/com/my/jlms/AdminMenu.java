package com.my.jlms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.my.classes.Database;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;


public class AdminMenu extends JFrame {

	private JPanel contentPane;
	private static AdminMenu frame;
	private static String[] images = { "librarian.png", "passwd.png", "search.png", "books.png", "db.png", "report.png"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
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
	public AdminMenu() {
		setTitle("Administration");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManageBooks = new JButton("Manage Books");
		btnManageBooks.setBounds(29, 25, 152, 36);
		contentPane.add(btnManageBooks);
		
		JButton btnLibrarian = new JButton("Librarian");
		btnLibrarian.setBounds(29, 147, 152, 36);
		contentPane.add(btnLibrarian);
		
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(29, 87, 152, 36);
		contentPane.add(btnViewReport);
		
		JButton btnSearchBooks = new JButton("Search Books");
		btnSearchBooks.setBounds(208, 25, 170, 36);
		contentPane.add(btnSearchBooks);
		
		JButton btnBorrowBooks = new JButton("Borrow Books");
		btnBorrowBooks.setBounds(208, 147, 170, 36);
		contentPane.add(btnBorrowBooks);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(208, 87, 170, 36);
		contentPane.add(btnChangePassword);
		
		JButton btnBackupDatabase = new JButton("Backup Database");
		btnBackupDatabase.setBounds(29, 200, 152, 36);
		contentPane.add(btnBackupDatabase);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Database s = new Database();
					s.close(2);
					JOptionPane.showMessageDialog(null, "Logged out!");
					System.exit(0);
				}catch(Exception x) {
					JOptionPane.showMessageDialog(null, "Logged out!");
					System.exit(0);
				}
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogout.setBounds(208, 200, 170, 36);
		contentPane.add(btnLogout);
		
		for(int i = 0; i < images.length;) {
			btnLibrarian.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
			btnChangePassword.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
			btnSearchBooks.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
			btnManageBooks.setIcon(new ImageIcon(getClass().getResource(images[i])));
			btnBorrowBooks.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
			btnBackupDatabase.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
			btnViewReport.setIcon(new ImageIcon(getClass().getResource(images[i])));
			i++;
		}
		
		btnBorrowBooks.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Borrow bor = new Borrow();
						bor.setVisible(true);
					}
				});
		
		btnManageBooks.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						ManageBooks frame = new ManageBooks();
						frame.setVisible(true);
					}
				});
		
		btnChangePassword.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						ChangePwd frame = new ChangePwd();
						frame.setVisible(true);
					}
				});
		
		btnSearchBooks.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						SearchBooks frame = new SearchBooks();
						frame.setVisible(true);
					}
				});
		
		btnLibrarian.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						AddLibrarian frame = new AddLibrarian();
						frame.setVisible(true);
					}
				});
		
		btnViewReport.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ex) {
						Report r = new Report();
						//setVisible(false);
						r.setVisible(true);
					}
				});
		
		btnBackupDatabase.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ex) {
						backup();
					}
				});
	}

	private void backup() {
		String executeCmd = "mysqldump -u root -plolz123 --dump-date -B lms > C:\\lms.sql";
		try {
			Process runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", executeCmd });
			int processComplete = runtimeProcess.waitFor();
			if(processComplete == 0){
				JOptionPane.showMessageDialog(null, "Database backup was successfully.");
			} else {
				JOptionPane.showMessageDialog(null, "Unable to backup.");
			}
		}catch(Exception e) {
			//JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
	}
}
