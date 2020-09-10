package basic.test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Video {
	private SimpleStringProperty title;
	private SimpleStringProperty director;
	private SimpleIntegerProperty price; 
	
	public Video(String title, String director, int price) {
		this.title = new SimpleStringProperty(title);
		this.price = new SimpleIntegerProperty(price);
		this.director = new SimpleStringProperty(director);
	}
	
	
	
	
	
	public String getTitle() {
		return this.title.get();
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public String getDirector() {
		return this.director.get();
	}
	public void setDirector(String director) {
		this.director.set(director);
	}
	
	public int getPrice() {
		return this.price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}
	
	

}
