package com.my.classes;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class CloneObject {

	public Object cloneObject(Object book) {
		byte[] data = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(book);
			oos.flush();
			oos.close();
			baos.close();
			data = baos.toByteArray();
			
			return data;
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return data;
	}
	
}
