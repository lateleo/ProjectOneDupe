package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Manager;
import util.JDBCconnection;

public class ManagerDAO implements IManager {

	public Manager getManager(int id) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			//IMPORTANT: NEEDS TESTING TO VERIFY FUNCTIONALITY
			Manager manager = null;
			while (rs.next()) manager = new Manager(rs.getInt("id"),
					rs.getString("firstName"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getInt("hashed_password"));
			return manager;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Manager getManager(String username) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			//IMPORTANT: NEEDS TESTING TO VERIFY FUNCTIONALITY
			Manager manager = null;
			while (rs.next()) manager = new Manager(rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getInt("hashed_password"));
			return manager;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Manager> allManagers() {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM users WHERE is_manager = 1 ORDER BY username"; //<--------------IMPORTANT
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ArrayList<Manager> managers = new ArrayList<Manager>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Manager manager = new Manager(rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("username"),
						rs.getInt("hashed_password"));
				managers.add(manager);
			}
			return managers;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteManager(Manager manager) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "DELETE FROM users WHERE ID = ?"; //<--------------IMPORTANT
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, manager.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateManager(Manager manager) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "UPDATE users SET first_name = ?, last_name = ?, hashed_password = ? WHERE ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getFirstName());
			ps.setString(2, manager.getLastName());
			ps.setInt(3, manager.getHashedPassword());
			ps.setInt(4, manager.getId());
			ps.executeQuery();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createManager(Manager manager) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "call add_manager(?,?,?,?)"; // <----------- Must be "call" not "exec" because of JDBC
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, manager.getFirstName());
			cs.setString(2, manager.getLastName());
			cs.setString(3, manager.getUsername());
			cs.setInt(4, manager.getHashedPassword());
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
