package basic.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Users {
	private SimpleStringProperty name;
	private SimpleStringProperty email;
	private SimpleStringProperty phonenumber;
	private SimpleIntegerProperty age;
	
	public Users(String name, String email, String phonenumber, int age) {
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.phonenumber = new SimpleStringProperty(phonenumber);
		this.age = new SimpleIntegerProperty(age);
	}
	
	public String getName() {
		return this.name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getEmail() {
		return this.email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	
	public String getPhoneNumber() {
		return this.phonenumber.get();
	}
	public void setPhoneNumber(String phonenumber) {
		this.phonenumber.set(phonenumber);
	}
	
	
	public int getAge() {
		return this.age.get();
	}
	public void setAge(int age) {
		this.age.set(age);
	}
	
	

}
