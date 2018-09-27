package service;

import java.util.List;

import dao.RequestDAO;
import models.Request;
import models.Employee;

public class RequestService {
	public static Request getRequest(int id) {
		return new RequestDAO().getRequest(id);
	}

	public static List<Request> pendingRequests(){
		return new RequestDAO().pendingRequests();
	}
	
	public static List<Request> pendingRequests(int employee_id){
		return new RequestDAO().pendingRequests(employee_id);
	}
	
	public static List<Request> resolvedRequests(){
		return new RequestDAO().resolvedRequests();
	}
	
	public static List<Request> resolvedRequests(int employee_id){
		return new RequestDAO().resolvedRequests(employee_id);
	}
	
	
	public static boolean updateRequest(Request request) {
		return new RequestDAO().updateRequest(request);
	}
	
	public static boolean deleteRequest(Request request) {
		return new RequestDAO().deleteRequest(request);
	}
	
	public static boolean createRequest(Request request) {
		return new RequestDAO().createRequest(request);
	}

}
