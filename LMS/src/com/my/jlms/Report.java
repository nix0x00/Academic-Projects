package com.my.jlms;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import com.my.classes.ReportByPercentage;

public class Report extends JFrame {

	private JPanel contentPane;
	protected static int value = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
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
	public Report() {
		setTitle("View Reports");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 421, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGender = new JButton("By Gender");
		btnGender.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnGender.setBounds(30, 53, 95, 29);
		contentPane.add(btnGender);
		
		JButton btnByAge = new JButton("By Age ");
		btnByAge.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnByAge.setBounds(159, 53, 89, 29);
		contentPane.add(btnByAge);
		
		JButton btnByDay = new JButton("By Day");
		btnByDay.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnByDay.setBounds(284, 53, 89, 29);
		contentPane.add(btnByDay);
		
		JButton btnBorrowed = new JButton("Borrowed");
		btnBorrowed.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnBorrowed.setBounds(30, 109, 95, 29);
		contentPane.add(btnBorrowed);
		
		JButton btnLibrarian = new JButton("Librarian");
		btnLibrarian.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnLibrarian.setBounds(159, 109, 89, 29);
		contentPane.add(btnLibrarian);
		
		JButton btnPercent = new JButton("By %age");
		btnPercent.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnPercent.setBounds(284, 112, 89, 26);
		contentPane.add(btnPercent);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(159, 155, 89, 23);
		contentPane.add(btnExit);
		
		btnLibrarian.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ac) {
						value = 5;
						ViewReport v = new ViewReport();
						//setVisible(false);
						v.setVisible(true);
					}
				});
		
		btnBorrowed.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ac) {
						value = 1;
						ViewReport v = new ViewReport();
						//setVisible(false);
						v.setVisible(true);;
					}
				});
		
		btnByAge.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ac) {
						value = 2;
						ViewReport v = new ViewReport();
						//setVisible(false);
						v.setVisible(true);
					}
				});
		
		btnByDay.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ac) {
						value = 3;
						ViewReport v = new ViewReport();
						//setVisible(false);
						v.setVisible(true);
					}
				});
		
		btnGender.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ac) {
						value = 4;
						ViewReport v = new ViewReport();
						v.setVisible(true);
					}
				});
		
		btnPercent.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ac) {
						value = 6;
						ReportByPercentage report = new ReportByPercentage();
						//report.pack();
						report.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						report.setVisible(true);
						
					}
				});
		
		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ac) {
						setVisible(false);
					}
				});
	}
}
