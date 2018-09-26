package dao;

import java.util.List;

import models.Request;
import models.Employee;

public interface IRequest {
	public Request getRequest(int id);
	public List<Request> allRequests(Employee employee);
	public List<Request> pendingRequests();
	public List<Request> resolvedRequests();
	public boolean deleteRequest(Request request);
	public boolean updateRequest(Request request);
	public boolean createRequest(Request request);

}
