package basic.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Users {
	private SimpleStringProperty name;
	private SimpleStringProperty email;
	private SimpleStringProperty number;
	private SimpleIntegerProperty age;
	
	public Users(String name, String email, String number, int age) {
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.number = new SimpleStringProperty(number);
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
	
	
	public String getNumber() {
		return this.number.get();
	}
	public void setNumber(String number) {
		this.number.set(number);
	}
	
	
	public int getAge() {
		return this.age.get();
	}
	public void setAge(int age) {
		this.age.set(age);
	}
	
	

}
