package com.my.jlms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import com.my.classes.Librarian;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.my.classes.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Authorization extends JFrame {

	private JPanel contentPane;
	private static Authorization frame;
	private JTextField txtUser;
	private JPasswordField txtPass;
	public static String s;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Authorization();
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
	public Authorization() {
		setTitle("Administrator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(26, 22, 64, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(26, 47, 64, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		Icon img = new ImageIcon(getClass().getResource("Ok.png"));
		btnLogin.setIcon(img);
		btnLogin.setBounds(86, 75, 110, 23);
		contentPane.add(btnLogin);
		
		txtUser = new JTextField();
		txtUser.setBounds(86, 19, 89, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(86, 44, 89, 20);
		contentPane.add(txtPass);
	
		btnLogin.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Database db = new Database();
						//authorization form/frame
						Authorization auth = new Authorization();
						
						//validation class
						Validation v = new Validation();
						String usr = txtUser.getText();
						String pwd = txtPass.getText();
						Admin a = (Admin) db.getPassword();
						
						if((usr.equals("Admin")) && (v.validateName(usr)) && a.getUser().equals(usr)) {
							if(v.validatePass(pwd) && a.getPass().equals(pwd)) {
								JOptionPane.showMessageDialog(null, "Login Successful");
								//db.close(2);
								AdminMenu framee = new AdminMenu();
								framee.setVisible(true);
								setVisible(false);
								
							} else {
								JOptionPane.showMessageDialog(null, "User/Pass is wrong! Please try again.");
							}
						}
						else if(v.validatePassport(usr)) {
						
							Librarian lib = (Librarian) db.readLibrarian(usr);
							
							if((lib != null) && (pwd.equals(lib.getPassword()))) {
								s = usr;
								JOptionPane.showMessageDialog(null, "Login Successful");
								//db.close(2);
								LibrarianMenu framee = new LibrarianMenu();
								framee.setVisible(true);
								setVisible(false);
							}
							else {
								JOptionPane.showMessageDialog(null, "Wrong Password! Please try again");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Wrong username, please try again.");
						}			
					}
				});
		
	}

}
