package com.my.classes;

import java.io.Serializable;
import javax.swing.JOptionPane;

public class Librarian extends User implements Serializable {
	
	private static final long serialVersionUID = 6464746467678644L;
	private String name;
	private String user;
	private String passwd;
	private int phoneNo;
	private String address;
	private String passportNo;
	
	//so that these shouldn't be serialized
	private transient Validation v = new Validation();
	private transient Database database;
	
	//parameterless constructor (compsition)
	public Librarian() {
		database = new Database();
	}
	
	//setters
	public void setName(String name) { this.name = name; }
	public void setUser(String user) { this.user = user; }
	public void setPasswd(String passwd) { this.passwd = passwd; }
	public void setPhoneNo(int phone) { this.phoneNo = phone; }
	public void setAddress(String address) { this.address = address; }
	public void setPassportNo(String passport) { this.passportNo = passport; }
	
	//getters
	public String getName() { return this.name; }
	public String getUser() { return this.user; }
	public String getPassword() { return this.passwd; }
	public int getPhoneNo() { return this.phoneNo; }
	public String getAddress() { return this.address; }
	public String getPassportNo() { return this.passportNo; }
	
	//validate input 
	public boolean validate(String user, String pwd, String name, String phone, String passport, String address) {
		boolean isTrue = false;
		
		if(v.validateName(user)) {

			if(v.validatePass(pwd)) {

				if(v.validateName(name)) {

					if(v.validatePhone(phone)) {

						if(v.validatePassport(passport)) {

							if(v.validateAddress(address)) {
								isTrue = true;
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
		
		return isTrue;
	}
	
	public void createObj(Object obj) {
		create(obj);
	}
	
	@Override
	public void create(Object obj) {
		
		try {
			CloneObject c = new CloneObject();
			byte[] data = (byte[]) c.cloneObject(obj);
			//execute method to create librarian account
			database.createLibrarian(data, this.getPassportNo());
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error! Please try again.");
			//System.exit(1);
		}
	}
	
	public boolean changePass(Object obj, String action, String pass) {
		boolean isTrue = false;
		isTrue = super.changePass(null,action,pass);
		return isTrue;
	}
	
}
