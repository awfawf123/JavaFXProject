package basic.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class VideoDAO {
	static ObservableList<Video> VideoList() {
		Connection conn = ConnectionDB.getDB();
		String sql = "select * from VideoList";
		ObservableList<Video> list = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Video video = new Video(rs.getString("TITLE"), rs.getString("DIRECTOR"), rs.getInt("PRICE"));
				list.add(video);

			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//추가
	static void insert(String title, String director, String price) {
		  Connection conn = ConnectionDB.getDB();
		  String sql = "insert into VideoList values(?,?,?)";
		   try {
		         PreparedStatement pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, title);
		         pstmt.setString(2, director);
		         pstmt.setString(3, price);
		         pstmt.executeUpdate();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
		   }
	
	//수정
	static void update(Video video) {
		Connection conn = ConnectionDB.getDB();
		String sql = "update VideoList set TITLE =?, DIRECTOR = ?, PRICE = ? where TITLE = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, video.getTitle());
	         pstmt.setString(2, video.getDirector());
	         pstmt.setInt(3, video.getPrice());
	         pstmt.setString(4, video.getTitle());
	         
	         pstmt.executeUpdate();
	         
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}
	

	}
	
	

		

