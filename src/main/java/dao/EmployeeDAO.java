package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import util.JDBCconnection;

public class EmployeeDAO implements IEmployee {

	public Employee getEmployee(int id) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			//IMPORTANT: NEEDS TESTING TO VERIFY FUNCTIONALITY
			Employee employee = null;
			while (rs.next()) employee = new Employee(rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getInt("hashed_password"));
			return employee;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Employee getEmployee(String username) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM users WHERE username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			//IMPORTANT: NEEDS TESTING TO VERIFY FUNCTIONALITY
			Employee employee = null;
			while (rs.next()) employee = new Employee(rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("username"),
					rs.getInt("hashed_password"));
			return employee;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Employee> allEmployees() {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM users WHERE is_manager = 0 ORDER BY username"; //<--------------IMPORTANT
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ArrayList<Employee> employees = new ArrayList<Employee>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("username"),
						rs.getInt("hashed_password"));
				employees.add(employee);
			}
			return employees;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteEmployee(Employee employee) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "DELETE FROM users WHERE ID = ?"; //<--------------IMPORTANT
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateEmployee(Employee employee) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "UPDATE users SET first_name = ?, last_name = ?, hashed_password = ? WHERE ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setInt(3, employee.getId());
			ps.setInt(4, employee.getHashedPassword());
			ps.executeQuery();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createEmployee(Employee employee) {
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "call add_employee(?,?,?,?)"; // <----------- Must be "call" not "exec" because of JDBC
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, employee.getFirstName());
			cs.setString(2, employee.getLastName());
			cs.setString(3, employee.getUsername());
			cs.setInt(4, employee.getHashedPassword());
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
