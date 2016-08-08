package com.my.jlms;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.my.classes.Admin;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainLMS {

	public static JFrame mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLMS window = new MainLMS();
					window.mainPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainLMS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainPanel = new JFrame();
		mainPanel.setResizable(false);
		mainPanel.setTitle("Library Management System");
		mainPanel.setBounds(100, 100, 453, 125);
		mainPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainPanel.getContentPane().setLayout(null);
		
		JButton btnAdmin = new JButton("Login");
		Icon img = new ImageIcon(getClass().getResource("admin.png"));
		btnAdmin.setIcon(img);
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdmin.setBounds(28, 30, 122, 33);
		mainPanel.getContentPane().add(btnAdmin);
		
		JButton btnLib = new JButton("Exit");
		Icon img1 = new ImageIcon(getClass().getResource("librarian.png"));
		btnLib.setIcon(img1);
		btnLib.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLib.setBounds(292, 30, 122, 33);
		mainPanel.getContentPane().add(btnLib);
		
		JButton btnGuest = new JButton("Guest");
		Icon img2 = new ImageIcon(getClass().getResource("guest.png"));
		btnGuest.setIcon(img2);
		btnGuest.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGuest.setBounds(160, 30, 122, 33);
		mainPanel.getContentPane().add(btnGuest);
		
		//event handlers
		btnAdmin.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 Authorization auth = new Authorization();
						 auth.show();
						 mainPanel.setVisible(false);
						
					}
				});
		
		btnGuest.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						
						// creating a reference variable of guest frame, and closing mainPanel.
						SearchBooks guest = new SearchBooks();
						guest.show();
						mainPanel.setVisible(false);
					}
				});
		
		btnLib.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent eve) {
						mainPanel.dispose();
						System.exit(0);
					}
				});
	}

}
