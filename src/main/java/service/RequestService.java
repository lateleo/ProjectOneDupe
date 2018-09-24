package service;

import java.util.List;

import dao.RequestDAO;
import models.Request;
import models.Employee;

public class RequestService {
	public static Request getRequest(int id) {
		return new RequestDAO().getRequest(id);
	}
	public static List<Request> allRequests(Employee employee){
		return new RequestDAO().allRequests(employee);
	}
	
	public static boolean updateRequest(Request request) {
		return new RequestDAO().updateRequest(request);
	}
	

	
	public static boolean deleteRequest(Request request) {
		if (request.getAmount() == 0) {
			return new RequestDAO().deleteRequest(request);
		} else {
			System.out.println("Invalid delete request: cannot delete an request with nonzero balance.");
			return false;
		}
	}
	
	public static boolean createRequest(Request request) {
		return new RequestDAO().createRequest(request);
	}

}
