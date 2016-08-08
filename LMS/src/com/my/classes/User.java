package com.my.classes;

//abstraction class
public abstract class User {
	
	private Database base = new Database();
	
	public boolean changePass(Object obj, String action, String pass) {
		boolean isTrue = false;
		isTrue = base.changePass(null, action, pass);
		return isTrue;
	}
	
	//abstract method to create accounts
	public abstract void create(Object obj);
}
