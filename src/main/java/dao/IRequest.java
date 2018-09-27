package dao;

import java.util.List;

import models.Request;

public interface IRequest {
	public Request getRequest(int id);
	public List<Request> pendingRequests(int employee_id);
	public List<Request> resolvedRequests(int employee_id);
	public List<Request> pendingRequests();
	public List<Request> resolvedRequests();
	public boolean deleteRequest(Request request);
	public boolean updateRequest(Request request);
	public boolean createRequest(Request request);

}
