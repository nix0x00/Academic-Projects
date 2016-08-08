package com.my.classes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;

import com.my.classes.PieReport;

public class ReportByPercentage extends JFrame implements ActionListener {
	
	private JPanel panel;
	private JButton btnGender;
	private JButton btnBooks;
	private JButton btnGuest;
	private JButton btnAge;
	private JButton btnExit;
	public static int count = 0;
	
	public ReportByPercentage() {
		panel = new JPanel();
		
		btnGender = new JButton("By Gender");
		btnBooks = new JButton("By Books");
		btnGuest = new JButton("By Guest");
		btnAge = new JButton("By Age");
		btnExit = new JButton("Exit");
		setLayout(new BorderLayout(5,5));
		
		btnGender.addActionListener(this);
		btnBooks.addActionListener(this);
		btnGuest.addActionListener(this);
		btnAge.addActionListener(this);
		btnExit.addActionListener(this);
		
		add(btnGender, BorderLayout.WEST);
		add(btnBooks, BorderLayout.EAST);
		add(btnGuest, BorderLayout.NORTH);
		add(btnAge, BorderLayout.SOUTH);
		add(btnExit,BorderLayout.CENTER);
		
		this.pack();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnGender) {
			count = 1;
			PieReport report = new PieReport("Pie Chart","Report");
			report.pack();
			report.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			report.setVisible(true);
		}else if(e.getSource() == btnBooks) {
			count = 2;
			PieReport report = new PieReport("Pie Chart","Report");
			report.pack();
			report.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			report.setVisible(true);
		}else if(e.getSource() == btnGuest) {
			count = 3;
			PieReport report = new PieReport("Pie Chart","Report");
			report.pack();
			report.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			report.setVisible(true);
		}else if(e.getSource() == btnAge){
			count = 4;
			PieReport report = new PieReport("Pie Chart","Report");
			report.pack();
			report.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			report.setVisible(true);
		}else if(e.getSource() == btnExit) {
			count = 5;
			setVisible(false);
		}else {
			
		}
	}
}
