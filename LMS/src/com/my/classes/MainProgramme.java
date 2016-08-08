package com.my.classes;

import java.awt.EventQueue;
import com.my.jlms.MainLMS;

public class MainProgramme  {
	public static void main(String[] uzair) {
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
}