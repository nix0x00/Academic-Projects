package com.my.jlms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import com.my.classes.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DeleteLibrarian extends JFrame {
	private JTextField txtPassport;
	private JTextField txtPass;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtUser;
	private JTextField txtAddress;
	private Database base = new Database();
	private Librarian lib;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteLibrarian frame = new DeleteLibrarian();
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
	public DeleteLibrarian() {
		setResizable(false);
		setTitle("Borrow / Return Books");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 302, 278);
		JTabbedPane tab = new JTabbedPane();
		Font font = new Font(null,Font.BOLD,11);
		tab.setFont(font);
		JPanel panel2 = new JPanel();
		tab.addTab("Edit Librarian Info",null,panel2);
		panel2.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(59, 37, 41, 14);
		panel2.add(lblName);
		
		JLabel lblPassportic_1 = new JLabel("Passport/IC:");
		lblPassportic_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassportic_1.setBounds(23, 11, 76, 14);
		panel2.add(lblPassportic_1);
		
		txtPass = new JTextField();
		txtPass.setBounds(109, 8, 108, 20);
		panel2.add(txtPass);
		txtPass.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(110, 34, 107, 20);
		panel2.add(txtName);
		txtName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(109, 65, 108, 20);
		panel2.add(txtPhone);
		txtPhone.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setBounds(109, 96, 108, 20);
		panel2.add(txtUser);
		txtUser.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(109, 127, 108, 20);
		panel2.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhoneNo.setBounds(40, 68, 60, 14);
		panel2.add(lblPhoneNo);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(40, 99, 69, 14);
		panel2.add(lblUsername);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(51, 130, 49, 14);
		panel2.add(lblAddress);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBounds(128, 168, 89, 37);
		panel2.add(btnEdit);
		getContentPane().add(tab);
		
		JPanel panel = new JPanel();
		tab.addTab("Delete Librarian", panel);
		panel.setLayout(null);
		
		txtPassport = new JTextField();
		txtPassport.setBounds(115, 33, 135, 20);
		panel.add(txtPassport);
		txtPassport.setColumns(10);
		
		JLabel lblPassportic = new JLabel("Passport/IC:");
		lblPassportic.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassportic.setBounds(27, 36, 78, 14);
		panel.add(lblPassportic);
		
		JButton btnDelete = new JButton("Delete!");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(156, 81, 94, 37);
		panel.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(42, 81, 89, 37);
		panel.add(btnExit);
		
		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ex) {
						setVisible(false);
					}
				});
		
		btnEdit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Validation v = new Validation();
						if(v.validateName(txtName.getText()) && v.validateAddress(txtAddress.getText()) &&
								v.validatePhone(txtPhone.getText()) && v.validateName(txtName.getText())) {
							lib = new Librarian();
							lib.setName(txtName.getText());
							lib.setAddress(txtAddress.getText());
							lib.setUser(txtUser.getText());
							lib.setPhoneNo(Integer.parseInt(txtPhone.getText()));
							if(lib != null) {
								base.deleteEditLibrarian(lib, txtPass.getText(), "edit");
								JOptionPane.showMessageDialog(null, "Successfully edited");
								
								txtName.setText(null); txtAddress.setText(null); txtUser.setText(null);
							    txtPhone.setText(null); txtPass.setText(null);
							}
						}
						txtPass.setEnabled(true);
					}
				});
		
		txtPass.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent ev) {
						if(ev.getKeyCode() == KeyEvent.VK_ENTER) {
							lib = new Librarian();
							lib = (Librarian) base.readLibrarian(txtPass.getText());
							if(lib != null) {
								txtName.setText(lib.getName());
								txtPhone.setText(Integer.toString(lib.getPhoneNo()));
								txtAddress.setText(lib.getAddress());
								txtUser.setText(lib.getUser());
								txtPass.setEnabled(false);
							}else {
								JOptionPane.showMessageDialog(null, "Error, User doesn't exist or please try again later.");
							}
						}
					}
				});
		
		btnDelete.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ex) {
						base.deleteEditLibrarian(null, txtPassport.getText(), "del");
						JOptionPane.showMessageDialog(null, "Librarian Deleted.");
					}
				});
	}
}
