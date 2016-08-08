package com.my.classes;
import java.util.Collection;

public class Validation {

	public boolean validateName(String name) {
		return name.matches("^[a-zA-Z ]*$");
	}
	
	public boolean validatePass(String pass) {
		return pass.matches("[a-zA-Z0-9]+|\\d{0,9}+\\b|[0-9a-zA-Z]+");
	}
	
	public boolean validateAddress(String addr) {
		return addr.matches("^[a-zA-Z0-9,_ ]*$");
	}
	
	public boolean validatePhone(String phone) {
		return phone.matches("[0-9]+");
	}
	
	public boolean validateEmail(String email) {
		return email.matches("[a-zA-Z].+[0-9a-zA-Z]@\\b.+\\w");
	}
	
	public boolean validatePassport(String passport) {
		return passport.matches("[a-zA-Z0-9]+");
	}
	
	public boolean validateAge(String age) {
		return age.matches("[0-9]\\d{2}+|\\d{1,2}+");
	}
	
	public boolean validateIsbn(String isbn) {
		return isbn.matches("[0-9]+");
	}
	
	public boolean validatebBook(String book) {
		return book.matches("^[a-zA-Z0-9#_ ]*$");
	}
	public boolean validateEdition(String edition) {
		return edition.matches("[0-9a-zA-Z]+");
	}
	
	public boolean validateDate(String date) {
		return date.matches("\\d{2}/\\d{2}/\\d{4}+");
	}
}
