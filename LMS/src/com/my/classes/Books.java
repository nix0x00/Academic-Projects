package com.my.classes;

import javax.swing.JOptionPane;
import java.io.Serializable;

public class Books implements Serializable {
	
	private static final long serialVersionUID = 5102693437507707486L;
	private int isbn;
	private String bookName;
	private String author;
	private String edition;
	private int rowNo;
	private int colNo;
	private String shelfNo;
	private String img;
	
	//using transient to avoid these objects being Serialized.
	private transient Validation v;
	private transient Database database;
	//constructor
	public Books() {
		 v = new Validation();
		 database = new Database();
	}
	
	public Books(int isbn, String name, String author, String shelfNo, int rowNo, int colNo) {
		setIsbn(isbn);
		setbookName(name);
		setAuthorName(author);
		setShelfNo(shelfNo);
		setRowNo(rowNo);
		setColNo(colNo);
	}
	
	/*
	 * we use set&get approach as its considered secure, besides this
	 * as we have no default/initial value for any variable. 
	 */
	
	//setters
	public void setIsbn(int isbnn) {	this.isbn = isbnn; }
	public void setbookName(String name) { this.bookName = name; }
	public void setAuthorName(String name) { this.author = name; }
	public void setEdition(String edition) { this.edition = edition; }
	public void setRowNo(int row) { this.rowNo = row; }
	public void setColNo(int colNo) { this.colNo = colNo; }
	public void setShelfNo(String shelfNo) { this.shelfNo = shelfNo; }
	public void setImage(String image) { this.img = image; }
	
	//getters
	public int getIsbn() { return this.isbn; }
	public String getBookName() { return this.bookName; }
	public String getAuthorName() { return this.author; }
	public String getEdition() { return this.edition; }
	public int getRowNo() { return this.rowNo; }
	public int getColNo() { return this.colNo; }
	public String getShelfNo() { return this.shelfNo; }
	public String getImage() { return this.img; }
	
	//validate data
	public boolean validate(String bookName, String isbn,String author, String edition, String row, String col, String shelf) {
		boolean istrue = false;

		if((v.validatebBook(bookName))) {

			if(v.validateIsbn(isbn)) {

				if(v.validateName(author)) {

					if(v.validateEdition(edition)) {

						if(v.validateIsbn(row)) {

							if(v.validateIsbn(col)) {
								
								if(v.validateEdition(shelf)) {
									istrue = true;
								}else {
									//JOptionPane.showMessageDialog(null, "error in shelf");
								}
							}else {
								//JOptionPane.showMessageDialog(null, "error in col");
							}
						}else {
							//JOptionPane.showMessageDialog(null, "error in row");
						}
					}else {
						//JOptionPane.showMessageDialog(null, "edition");
					}
				}else {
					//JOptionPane.showMessageDialog(null, "Author");
				}
			}else {
				//JOptionPane.showMessageDialog(null, "isbn");
			}
		}
		else {
			//JOptionPane.showMessageDialog(null, "book");
		}
	
		return istrue;
	}
	
	//this method is going to insert data about book into
	// database. 
	public void insertBookRecord(Object obj, String act) {
		try {
			CloneObject co = new CloneObject();
			byte[] data = (byte[]) co.cloneObject(obj);
		
			database.insertBookRecord(this.getIsbn(), data, act);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public boolean checkStatus(int isbn) {
		boolean isTrue = false;
		if(database.checkStatus(isbn)) {
			isTrue = true;
		}
		
		//return value of isTrue
		return isTrue;
	}
}
