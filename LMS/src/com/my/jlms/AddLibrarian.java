package com.my.jlms;

import com.my.classes.Database;
import com.my.classes.Librarian;
import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AddLibrarian extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtPassport;
	private JTextField txtAddress;
	Librarian lib = new Librarian();
	private JPasswordField txtPassword;
	private JButton btnExit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLibrarian frame = new AddLibrarian();
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
	public AddLibrarian() {
		setTitle("Add Librarian Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 321, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNUser = new JLabel("Username :");
		lblNUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNUser.setBounds(18, 28, 69, 14);
		contentPane.add(lblNUser);
		
		txtUser = new JTextField();
		txtUser.setBounds(89, 25, 86, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(18, 69, 69, 14);
		contentPane.add(lblPassword);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(41, 104, 46, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(89, 101, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhone.setBounds(41, 143, 46, 14);
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(89, 140, 86, 20);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblPassNo = new JLabel("Passport No:");
		lblPassNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassNo.setBounds(10, 178, 77, 14);
		contentPane.add(lblPassNo);
		
		txtPassport = new JTextField();
		txtPassport.setBounds(89, 175, 86, 20);
		contentPane.add(txtPassport);
		txtPassport.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(29, 213, 58, 14);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(89, 210, 86, 20);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreate.setBounds(190, 157, 89, 35);
		contentPane.add(btnCreate);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(89, 66, 86, 20);
		contentPane.add(txtPassword);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(190, 197, 89, 35);
		contentPane.add(btnExit);
		
		btnCreate.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(lib.validate(txtUser.getText(), txtPassword.getText(), txtName.getText(), txtPhone.getText(), txtPassport.getText(), txtAddress.getText())) {
							lib.setUser(txtUser.getText());
							lib.setPasswd(txtPassword.getText());
							lib.setName(txtName.getText());
							lib.setPhoneNo(Integer.parseInt(txtPhone.getText()));
							lib.setPassportNo(txtPassport.getText());
							lib.setAddress(txtAddress.getText());
							
							//create account in database
							if(lib != null) {
								lib.create(lib);
								JOptionPane.showMessageDialog(null, "Account was created Successfully.");
							}
							txtUser.setText(null); txtPassword.setText(null); txtName.setText(null);
							txtPhone.setText(null); txtPassport.setText(null);
							txtAddress.setText(null);
						}else {
							JOptionPane.showMessageDialog(null, "Error! Please check your input.");
						}
					}
				});
		
		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ex) {
						setVisible(false);
					}
				});
	}
}
