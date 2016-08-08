package com.my.classes;

import java.io.Serializable;

public class Admin extends User implements Serializable {
	
	private static final long serialVersionUID = 7765434598765432L;
	private String user;
	private String pass;
	private transient Database base;
	public Admin() {
		this.user = "Admin";
		this.pass = "admin123";
		base = new Database();
	}
	
	public void setUser(String usr) { this.user = usr; }
	public void setPass(String pwd) { this.pass = pwd; }
	
	public String getUser() { return this.user; }
	public String getPass() { return this.pass; }
	
	@Override
	public void create(Object obj) {
		CloneObject co = new CloneObject();
		byte[] data = (byte[]) co.cloneObject(this);
		base.addAdmin(data);
	}
}
