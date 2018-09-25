package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Employee;
import models.User;
import util.JDBCconnection;

public class UserDAO implements IUser {

	public User getUser(int id) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			//IMPORTANT: NEEDS TESTING TO VERIFY FUNCTIONALITY
			User user = null;
			while (rs.next()) user = new User(rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getInt("hashed_password"),
					(rs.getInt("is_manager") == 1));
			return user;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User getUser(String username) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			//IMPORTANT: NEEDS TESTING TO VERIFY FUNCTIONALITY
			User user = null;
			while (rs.next()) user = new User(rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getInt("hashed_password"),
					(rs.getInt("is_manager") == 1));
			return user;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
