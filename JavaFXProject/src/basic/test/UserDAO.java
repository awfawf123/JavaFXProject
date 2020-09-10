package basic.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {
	static ObservableList<Users> UserList(){
		Connection conn1 = ConnectionDB.getDB();
		String sql1 = "select * from UserList";
		ObservableList<Users> list1 = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn1.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Users user = new Users(rs.getString("NAME"),rs.getString("EMAIL"),
						rs.getString("PHONENUMBER"),rs.getInt("AGE"));
						list1.add(user);
						}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return list1;
		
	}
	static void insert(String name, String email, String phonenumber, String age) {
		  Connection conn1 = ConnectionDB.getDB();
		  String sql = "insert into userList values(?,?,?,?)";
		   try {
		         PreparedStatement pstmt = conn1.prepareStatement(sql);
		         pstmt.setString(1, name);
		         pstmt.setString(2, email);
		         pstmt.setString(3, phonenumber);
		         pstmt.setString(4, age);
		         
		         pstmt.executeUpdate();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
		   }

}
