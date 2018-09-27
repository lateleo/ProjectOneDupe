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
	


	public List<Request> pendingRequests(int employee_id) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM requests request WHERE employee_id = ? and status = 0 ORDER BY date_created";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee_id);
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
	
	public List<Request> resolvedRequests(int employee_id) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM requests request WHERE employee_id = ? and status = 1 or 2 ORDER BY date_created";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee_id);
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
	
	public List<Request> pendingRequests() {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM requests request WHERE status = 0 ORDER BY date_created";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
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
	
	public List<Request> resolvedRequests() {
		Connection conn = JDBCconnection.getConnection();
		String sql = "SELECT * FROM requests request WHERE status = 1 or 2 ORDER BY date_created";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
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

	public boolean deleteRequest(Request request) {
		Connection conn = JDBCconnection.getConnection();
		String sql = "DELETE FROM requests WHERE ID = ?";
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
			String sql = "call add_request(?,?,?)";
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
