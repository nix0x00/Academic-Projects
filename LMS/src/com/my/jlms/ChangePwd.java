package com.my.jlms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.my.classes.*;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.my.classes.Validation;

public class ChangePwd extends JFrame {

	private JPanel contentPane;
	private static ChangePwd frame;
	private JTextField txtOld;
	private JTextField txtNew;
	//private Librarian lib;
	//private Admin a;
	Validation v = new Validation();
	private JButton btnExit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ChangePwd();
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
	public ChangePwd() {
		setResizable(false);
		setTitle("Password!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 266, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPass = new JLabel("User ID:");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPass.setBounds(45, 11, 45, 14);
		contentPane.add(lblPass);
		
		txtOld = new JTextField();
		txtOld.setBounds(100, 8, 120, 20);
		contentPane.add(txtOld);
		txtOld.setColumns(10);
		
		JLabel lblNew = new JLabel("New Password:");
		lblNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNew.setBounds(10, 52, 87, 14);
		contentPane.add(lblNew);
		
		txtNew = new JTextField();
		txtNew.setBounds(100, 49, 120, 20);
		contentPane.add(txtNew);
		txtNew.setColumns(10);
		
		JButton btnChange = new JButton("Change!");
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChange.setBounds(133, 80, 87, 30);
		contentPane.add(btnChange);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(20, 80, 89, 30);
		contentPane.add(btnExit);
		
		btnChange.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if((v.validatePass(txtOld.getText()) && (v.validatePass(txtNew.getText())))) {
							if(!txtOld.getText().equals("Admin")) {
								User libb = new Librarian();
								
								boolean t = libb.changePass(null, txtOld.getText(), txtNew.getText());
								if(t) {
									JOptionPane.showMessageDialog(null, "Password changed Successfully.");
								}else { 
									JOptionPane.showMessageDialog(null, "Sorry, your password cannot be changed"
																	+ " at this time, please try again later.");
								}
							}
							else {
								User aa = new Admin();
								boolean t = aa.changePass(null, txtOld.getText(), txtNew.getText());
								if(t) {
									JOptionPane.showMessageDialog(null, "Password changed Successfully.");
								}else { 
									JOptionPane.showMessageDialog(null, "Sorry, your password cannot be changed"
																+ " at this time, please try again later.");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Error! please check your input.");
						}
					}
				});
		
		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						setVisible(false);
					}
				});
	}

}
