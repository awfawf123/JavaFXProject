package basic.example;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RootController2 implements Initializable {
	@FXML
	TableView<Student> tableView;
	@FXML
	Button btnAdd, btnBarChart;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Student> list = FXCollections.observableArrayList();
		
		TableColumn<Student, ?> tc = tableView.getColumns().get(0); // 첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));
		
		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));
		
		
		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		
		tc = tableView.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		tableView.setItems(list);
		
		
		
		
		
		
		
		
		
		}
	public ObservableList<Student2> getStulist(){
	
		
		Connection conn = ConnectionDB.getDB();
		String sql = "select * from student";
		ObservableList<Student2> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Student2 = new Student2(rs.getString("id"),rs.getString("name"),
	                     rs.getInt("korean"),rs.getInt("math"),rs.getInt("engilsh"));
				return list;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	

}
