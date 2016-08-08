package com.my.classes;

import java.util.ArrayList;
import java.util.List;
import com.my.jlms.Percentage;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class PieReport extends JFrame {
	
	private Guest g = new Guest();
	private Database base = new Database();
	private static int male;
	private static int female;
	private static int age;
	private static int guest;
	
	public PieReport(String appTitle, String chartTitle) {
		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500,300));
		setContentPane(chartPanel);
	}
	
	private PieDataset createDataset()  {
		DefaultPieDataset result = new DefaultPieDataset();
		
		
		//object to retrieve the objects from database.
		List<Object> obj = new ArrayList<Object>();
		List<Guest> gg = new ArrayList<Guest>();
		
		
		//retrieving all the objects from database.
		obj = base.getReport("guest");
		for(int i = 0; i < Database.t; i++) {
			g = (Guest) obj.get(i);
			gg.add(g);
		}
		
		if(ReportByPercentage.count == 1) {
			obj = base.getReport();
			int size = obj.size();
			for(int i = 0; i < size; i++) {
				g = (Guest) obj.get(i);
				gg.add(g);
				g = gg.get(i);
				if((g.getGender().equals("M")) || (g.getGender().equals("Male"))) {
					++male;
				}else if((g.getGender().equals("F")) || (g.getGender().equals("Female"))) {
					++female;
				}
			}
			result.setValue("Male", male);
			result.setValue("Female", female);
		}
		else if(ReportByPercentage.count == 2) {
			result.setValue("Total Books", base.totalbooks());
		}
		else if(ReportByPercentage.count == 3) {
			for(int i = 0; i < Database.t; i++) {
				g = gg.get(i);
				++guest;
			}
			result.setValue("Total Guests", guest);
		}
		else if(ReportByPercentage.count == 4) {
			for(int i = 0; i < Database.t; i++) {
				g = gg.get(i);
				if((g.getAge() >= 15) && (g.getAge() <= 25)) {
					++age;
				}	
			}
			result.setValue("Age (15-25)", age);
		}
		
		return result;
	}
	
	private JFreeChart createChart(PieDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(0);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	}
}
