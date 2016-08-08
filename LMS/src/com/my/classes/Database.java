package com.my.classes;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.ArrayList;

public class Database {
	private Connection conn;
	private PreparedStatement state;
	private ResultSet rs;
	private static final String DB_URL = "jdbc:mysql://localhost/LMS";
	private static final String USER = "root";
	private static final String PASS = "lolz123";
	public static int t = 0;
	
	public Database() {
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}
		catch(SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Please check your Connection to Database.");
			
		}
	}
	
	public void addAdmin(Object obj) {
		String query = "INSERT INTO Admin (Object) VALUES (?)";
		try {
			state = conn.prepareStatement(query);
			state.setObject(1,obj);
			state.executeUpdate();
		}catch(SQLException ex) {
			//ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public Object getPassword() {
		String query = "SELECT Object FROM Admin WHERE UserID = 1";
		Admin adm = null;
		try {
			state = conn.prepareStatement(query);
			
			rs = state.executeQuery();
			if(rs.next()) {
				ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
				try {
					ObjectInputStream ois = new ObjectInputStream(bais);
					adm = (Admin) ois.readObject();
					ois.close();
					bais.close();
					return adm;
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}
				
			}		
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return adm;
	}
	
	//method to change password.
	public boolean changePass(Object obj, String action, String pass) {
		boolean isTrue = false;
		CloneObject co = new CloneObject();
		String query = "UPDATE Admin SET Object = ? WHERE (UserID = 1)";
		String query0 = "UPDATE Librarian SET Object = ? WHERE (LibPassport = ?)";
		String query1 = "SELECT Object FROM Librarian WHERE (LibPassport = ?)";
		try {
			if(action.equals("Admin")) {
			state = conn.prepareStatement("SELECT Object FROM Admin WHERE UserID = 1");
			rs = state.executeQuery();
			while(rs.next()) {
				ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
				ObjectInputStream ois = new ObjectInputStream(bais);
				
				Admin a = (Admin) ois.readObject();
				ois.close();
				bais.close();
				a.setPass(pass);
				byte[] data = (byte[]) co.cloneObject(a);
				Object t = data;
				
				state = conn.prepareStatement(query);
				state.setObject(1, t);
				int r = state.executeUpdate();
				if(r != 0) {
					isTrue = true;
				}
			}
		}else {
			
			state = conn.prepareStatement(query1);
			state.setString(1, action);
			rs = state.executeQuery();
			if(rs.next()) {
				ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
				ObjectInputStream ois = new ObjectInputStream(bais);
				Librarian lib = (Librarian) ois.readObject();
				ois.close();
				bais.close();
				
				lib.setPasswd(pass);
				byte[] data = (byte[]) co.cloneObject(lib);
				Object r = data;
				state = conn.prepareStatement(query0);
				state.setObject(1, r);
				state.setString(2, action);
				int res = state.executeUpdate();
				if(res != 0) {
					isTrue = true;
				}
				//close(2);
			}
		}
	    }catch(SQLException e) {
	    	JOptionPane.showMessageDialog(null, e.getMessage());
	    }catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, e.getMessage());
	    }
		return isTrue;
	}
	
	//Beta method under testing phase in order to add object in database.
	public void insertBookRecord(int isbn, byte[] book, String act) {
		String query = "INSERT INTO Books VALUES (?, ?)";
		String query0 = "UPDATE Books SET ISBN = ?, Object = ? WHERE (ISBN = ?)";
		
		try {
			if(act.equals("add")) {
				state = conn.prepareStatement(query);
				state.setInt(1, isbn);
				state.setObject(2, book);
			} else {
				state = conn.prepareStatement(query0);
				state.setInt(1, isbn);
				state.setObject(2, book);
				state.setInt(3, isbn);
			}
			state.executeUpdate();

		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		finally {
			//close(3);
		}
	}
	
	public Object readBook(int isbn) throws SQLException {
		Books book = null;
		String query = "SELECT * FROM Books WHERE (ISBN = ?)";
		state = conn.prepareStatement(query);
		state.setInt(1, isbn);
		rs = state.executeQuery();
		
		if(rs.next()) {
			ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
			try {
				ObjectInputStream ois = new ObjectInputStream(bais);
				book = (Books)ois.readObject();
				ois.close();
				bais.close();
				//close(2);
				return book;
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return book;
	}
	
	//method to create librarian account in the database
	public void createLibrarian(Object obj, String passport) {
		String query = "INSERT INTO Librarian VALUES (?, ?)";
		
		try{
			state = conn.prepareStatement(query);
			state.setString(1, passport);
			state.setObject(2, obj);
			state.executeUpdate();
			//close(3);
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}	
	}
	
	//method to create Guest account
	public void createGuest(String passport,Object obj) {
		String query = "INSERT INTO Guest VALUES (?, ?)";
		
		try {
			state = conn.prepareStatement(query);
			state.setString(1, passport);
			state.setObject(2, obj);
			state.executeUpdate();
			//close(3);
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	//method to read guest account from database
	public List<Guest> readGuest() {
		List<Guest> list = null;
		String query = "SELECT Passport FROM Guest";
		
		try {
			state = conn.prepareStatement(query);
			rs = state.executeQuery();
			list = new ArrayList<Guest>();
			while(rs.next()) {
				
				list.add(new Guest(
						rs.getString("Passport"))
						);
			}
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		finally {
			//close(2);
		}
		return list;
	}
	
	//method to check the status of book
	public boolean checkStatus(int isbn) {
		boolean isTrue = false;
		String query = "SELECT EXISTS(SELECT 1 FROM Orders WHERE (ISBN = ?) AND (Fine = 1)) AS ISBN";
		
		try {
			state = conn.prepareStatement(query);
			state.setInt(1, isbn);
			rs = state.executeQuery();
			if(rs.next()) {
				isTrue = (rs.getInt("ISBN") != 0) ? false : true;
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		//close(2);
		return isTrue;
	}
	
	//create order
	public void createOrder(String pass, int Isbn, String libPass, Object obj) {
		String query = "INSERT INTO Orders(Passport, ISBN, LibPassport, Object) VALUES (?, ?, ?, ?)";
		try {
			state = conn.prepareStatement(query);
			state.setString(1, pass);
			state.setInt(2, Isbn);
			state.setString(3, libPass);
			state.setObject(4, obj);
			
			state.executeUpdate();
			//close(3);
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	//method to read Order
	public Object readOrder(int isbn, String action, int price) {
		Transaction obj = null;
		String query = "SELECT Object FROM Orders WHERE (ISBN = ?)";
		String query0 = "UPDATE Orders SET Fine = ? WHERE (ISBN = ?)";
		try {
			if(action.equals("select")) {
				state = conn.prepareStatement(query);
				state.setInt(1, isbn);
				rs = state.executeQuery();
				if(rs.next()) {
					ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
					ObjectInputStream ois = new ObjectInputStream(bais);
					obj = (Transaction) ois.readObject();
					ois.close();
					bais.close();
					//close(2);
					return obj;
				}
			} else {
				state = conn.prepareStatement(query0);
				state.setInt(1, price);
				state.setInt(2, isbn);
				state.executeUpdate();
			}
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		catch(Exception ioe) {
			JOptionPane.showMessageDialog(null, ioe.getMessage());
		}
		return obj;
	}
	
	//method to search book
	public List<Books> searchBook() {
		List<Books> search = null;
		Books s = null;
		String query = "SELECT * FROM Books";
		try {
			state = conn.prepareStatement(query);
			rs = state.executeQuery();
			search = new ArrayList<Books>();
			while(rs.next()) {
				ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
				ObjectInputStream ois = new ObjectInputStream(bais);
				s = (Books) ois.readObject();
				search.add(s);
				ois.close();
				bais.close();
			}
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}catch(Exception exc) {
			JOptionPane.showMessageDialog(null, exc.getMessage());
		}
		
		return search;
	}
	
	//method to read all objects in tables for the report purpose.
	public List<Object> getReport(String action) {
		List<Object> obj = new ArrayList<Object>();
		Object x = null;
		String query = "SELECT L.LibPassport AS LibPassport,L.Object as LObject,G.Passport AS GPassport,"
						+ "G.Object AS GObject,B.ISBN,B.Object AS BObject,O.OrderID,O.Fine,O.Object AS Object FROM Orders AS O, Guest AS G,Books AS B,"
						+ "Librarian AS L WHERE O.Passport = G.Passport AND B.ISBN = O.ISBN";
		try {
			state = conn.prepareStatement(query);
			rs = state.executeQuery();
			
			if(action.equals("lib")) {
				while(rs.next()) {
					ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("LObject"));
					ObjectInputStream ois = new ObjectInputStream(bais);
					x = ois.readObject();
					obj.add(x);
					t = obj.size();
					ois.close();
					bais.close();
				}
			}else if(action.equals("guest")) {
				while(rs.next()) {
					ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("GObject"));
					ObjectInputStream ois = new ObjectInputStream(bais);
					x = ois.readObject();
					obj.add(x);
					t = obj.size();
					ois.close();
					bais.close();
				}
			} else if(action.equals("Order")) {
				while(rs.next()) {
					ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
					ObjectInputStream ois = new ObjectInputStream(bais);
					Transaction t = new Transaction();
					t = (Transaction) ois.readObject();
					t.setFine(rs.getInt("Fine"));
					t.setOrderID(rs.getInt("OrderID"));
					x = (Object) t;
					obj.add(x);
					ois.close();
					bais.close();
				}
			} else if(action.equals("books")) {
				while(rs.next()) {
					ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("BObject"));
					ObjectInputStream ois = new ObjectInputStream(bais);
					x = ois.readObject();
					obj.add(x);
					ois.close();
					bais.close();
				}
			}else {
				JOptionPane.showMessageDialog(null, "Report cannot be obtained at the moment. Please try again");
			}
		}catch(SQLException | IOException | ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return obj;
	}
	
	
	public int totalbooks() {
		String query = "SELECT COUNT(ISBN) AS Total FROM Books";
		int total = 0;
		try {
			state = conn.prepareStatement(query);
			rs = state.executeQuery();
			while(rs.next()) {
				total = rs.getInt("Total");
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return total;
	}
	
	//method to delete book
	public boolean removeBook(int isbn) {
		String query = "DELETE FROM Books WHERE ISBN = ?";
		boolean isTrue = false;
		try {
			state = conn.prepareStatement(query);
			state.setInt(1, isbn);
			state.executeUpdate();
			isTrue = true;
		}catch(SQLException x) {
			JOptionPane.showMessageDialog(null, x.getMessage());
		}
		return isTrue;
	}
	
	//method to read Guest
	public List<Object> getReport() {
		List<Object> obj = new ArrayList<Object>();
		Object x = null;
		String query = "SELECT Object FROM Guest";
		try {
			state = conn.prepareStatement(query);
			rs = state.executeQuery();
			while(rs.next()) {
				ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
				ObjectInputStream ois = new ObjectInputStream(bais);
				x = ois.readObject();
				obj.add(x);
				ois.close();
				bais.close();
			}
		}catch(SQLException | IOException | ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return obj;
	}
	
	//method to get Librarian information e.g user & pass
	public Object readLibrarian(String passport) {
		String query = "SELECT * FROM Librarian WHERE (LibPassport = ?)";
		Librarian lib = null;
		try {
			state = conn.prepareStatement(query);
			state.setString(1,passport);
			rs = state.executeQuery();
			
			if(rs.next()) {
				ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("Object"));
				try {
					ObjectInputStream ois = new ObjectInputStream(bais);
					lib = (Librarian) ois.readObject();
					
					//close streams
					ois.close();
					bais.close();
					return lib;
				}catch (Exception exce) {
					JOptionPane.showMessageDialog(null, exce);
				}
			}
		}
		catch(SQLException exc) {
			JOptionPane.showMessageDialog(null, exc.getMessage());
		}
		return lib;
	}
	
	//method to delete/edit librarian info
	public void deleteEditLibrarian(Librarian obj, String passport, String action) {
		String query = "UPDATE Librarian SET Object = ? WHERE LibPassport = ?";
		String query0 = "DELETE FROM Librarian WHERE LibPassport = ?";
		Librarian lib = new Librarian();
		lib = (Librarian) readLibrarian(passport);
		//JOptionPane.showMessageDialog(null, lib.getName());
		try {
			if(action.equals("del")) {
				state = conn.prepareStatement(query0);
				state.setString(1,  passport);
				
			}else if(action.equals("edit")) {
				state = conn.prepareStatement(query);
				obj.setPasswd(lib.getPassword());
				CloneObject co = new CloneObject();
				byte[] data = (byte[]) co.cloneObject(obj);
				Object objj = new Object();
				objj = data;
				state.setObject(1,  objj);
				state.setString(2, passport);
				
			}else {
				
			}
			state.executeUpdate();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	//method to close connections.
	public void close(int a) {
		if(a == 2) {
			try {
				rs.close();
				conn.close();
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}else {
			try {
				conn.close();
			}
			catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	
}
