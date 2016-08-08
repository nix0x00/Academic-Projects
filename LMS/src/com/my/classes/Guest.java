package com.my.classes;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Guest extends User implements Serializable {

	private static final long serialVersionUID = 8765445678987654L;
	private String name;
	private String email;
	private int phone;
	private String passportNo;
	private int age;
	private String gender;
	private String address;
	private transient Database database = new Database();
	//parameterless constructor
	public Guest() { database = new Database(); }
	public Guest(String passport) { setPassport(passport); }

	//parameterized constructor
	public Guest(String name, String email, int phoneNo, String passport,
						int age, String gender, String address) {
		this.name = name;
		this.email = email;
		this.phone = phoneNo;
		this.passportNo = passport;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
	
	//setters
	public void setName(String name) { this.name = name; }
	public void setEmail(String email) { this.email = email; }
	public void setPhone(int phone) { this.phone = phone; }
	public void setPassport(String passport) { this.passportNo = passport; }
	public void setAge(int age) { this.age = age; }
	public void setGender(String gender) { this.gender = gender; }
	public void setAddress(String address) { this.address = address; }
	
	//getters
	public String getName() { return this.name; }
	public String getEmail() { return this.email; }
	public int getPhoneNo() { return this.phone; }
	public String getPassportNp() { return this.passportNo; }
	public int getAge() { return this.age; }
	public String getGender() { return this.gender; }
	public String getAddress() { return this.address; }
	
	@Override
	public void create(Object obj) {
		CloneObject oc = new CloneObject();
		try {
			byte[] data = (byte[]) oc.cloneObject(obj);
			String s = this.getPassportNp();
			database.createGuest(s, data);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
