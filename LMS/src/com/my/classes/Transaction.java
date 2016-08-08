package com.my.classes;

import java.util.Date;

import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 8765456787654567L;
	private String status;
	private String duration;
	private int isbn;
	private Date borrowDate;
	private Date returnDate;
	private String id;
	private transient int fine; 
	private transient int orderID;
	private transient SimpleDateFormat formatter;
	private transient Database base;
	private transient Validation v = new Validation();
	
	//Parameterless constructor	
	public Transaction() {
		base = new Database();
	}
	
	//setters
	public void setDuration(String dur) { this.duration = dur; }
	public void setStatus(String status) { this.status = status; }
	public void setIsbn(int isbn) { this.isbn = isbn; }
	public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
	public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
	public void setGuest(String id) { this.id = id; }
	public void setFine(int fine) { this.fine = fine; }
	public void setOrderID(int order) { this.orderID = order; }
	
	//getters
	public String getStatus() { return this.status; }
	public String getDuration() { return this.duration; }
	public int getIsbn() { return this.isbn; }
	public Date getBorrowDate() { return this.borrowDate; }
	public Date getReturnDate() { return this.returnDate; }
	public String getGuest() { return id; }
	public int getFine() { return this.fine; }
	public int getOrderID() { return this.orderID; }
	
	//method to validate data
	public boolean validate(String isbn, String duration) {
	
		boolean isTrue = false;
		
		if(v.validateIsbn(isbn)) {
			if(v.validateAddress(duration)) {
				isTrue = true;
			}
		}
		return isTrue;
	}
	
	//method to create order object in the database.
	public void createOrder(String pass, int Isbn, String libPass,Object obj) {
		CloneObject oc = new CloneObject();
		byte[] data = (byte[]) oc.cloneObject(obj);
		base.createOrder(pass, Isbn, libPass, data);
	}	
	
	public void returnBook(int isbn, int price) {
		base.readOrder(isbn, "update", price);
		//JOptionPane.showMessageDialog(null, isbn + " " + price);
	}
}
