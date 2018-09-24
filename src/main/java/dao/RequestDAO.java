package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import models.Request;
import util.JDBCconnection;

public class RequestDAO implements IRequest {
	


	public Request getRequest(int id) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM requests WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Request request = null;
			while (rs.next()) {
				request = new Request(rs.getInt("id"),
						rs.getInt("employee_id"),
						rs.getInt("manager_id"),
						rs.getTimestamp("date_created"),
						rs.getDouble("amount"),
						rs.getString("reason"),
						rs.getInt("status"));
			}
			return request;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	


	public List<Request> allRequests(Employee employee) {
		// TODO Restrict this to pull only requests for a given employee ("SELECT * FROM request WHERE employee_id = ?")
		// TODO TEST THIS
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM requests request WHERE employee_id = ? ORDER BY name"; //<--------------IMPORTANT
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getId());
			ArrayList<Request> requests = new ArrayList<Request>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Request request = new Request(rs.getInt("id"),
						rs.getInt("employee_id"),
						rs.getInt("manager_id"),
						rs.getTimestamp("date_created"),
						rs.getDouble("amount"),
						rs.getString("reason"),
						rs.getInt("status"));
				requests.add(request);
			}
			return requests;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Request> allRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteRequest(Request request) {
		// TODO Auto-generated method stub
		Connection conn = JDBCconnection.getConnection();
		String sql = "DELETE FROM requests WHERE ID = ?"; //<--------------IMPORTANT
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, request.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateRequest(Request request) {
		// TODO TEST THIS
		Connection conn = JDBCconnection.getConnection();
		String sql = "UPDATE requests SET manager_id = ?, status = ? WHERE ID = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, request.getManagerId());
			ps.setInt(2, request.getStatusInt());
			ps.setInt(3, request.getId());
			ps.executeQuery();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createRequest(Request request) {
		// TODO TEST THIS
		try {
			Connection conn = JDBCconnection.getConnection();
			String sql = "call add_request(?,?,?)"; // <----------- Must be "call" not "exec" because of JDBC
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, request.getEmployeeId());
			cs.setDouble(2, request.getAmount());
			cs.setString(3, request.getReason());
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}





}
