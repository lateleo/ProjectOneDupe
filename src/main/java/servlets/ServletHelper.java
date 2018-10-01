package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import models.Employee;
import models.Request;
import models.User;
import service.EmployeeService;
import service.RequestService;
import service.UserService;
import util.JSONConverter;

public class ServletHelper {
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		if(uri.equals("/ProjectOne/login.do")) {
			login(request, response);
		} else if (uri.equals("/ProjectOne/updateName.do")) {
			updateName(request, response);
		} else if (uri.equals("/ProjectOne/updatePassword.do")) {
			updatePassword(request, response);	
		} else if (uri.equals("/ProjectOne/employeeViewPending.do")) {
			employeeViewPending(request, response);
		} else if (uri.equals("/ProjectOne/employeeViewResolved.do")) {
			employeeViewResolved(request, response);
		} else if (uri.equals("/ProjectOne/managerViewPending.do")) {
			managerViewPending(request, response);
		} else if (uri.equals("/ProjectOne/managerViewResolved.do")) {
			managerViewResolved(request, response);
		} else if (uri.equals("/ProjectOne/approveDenyRequest.do")) {
			updateRequest(request, response);
		} else if (uri.equals("/ProjectOne/createRequest.do")) {
			createRequest(request, response);
		} else if (uri.equals("/ProjectOne/allEmployees.do")) {
			allEmployees(request, response);
		}
	}
	
//	-------------------------------------------------------------------------------
	public static void login(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		User user = UserService.getUser(request.getParameter("username"));
		if (user != null && Objects.hash(user.getUsername(),request.getParameter("password")) == user.getHashedPassword()) {
			response.setIntHeader("valid-login", 1);
			response.setHeader("user", JSONConverter.convert(user));
		} else {
			response.setIntHeader("valid-login", 0);
		}
	}
	
	public static void updateName(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		User user = UserService.getUser(Integer.valueOf(request.getParameter("id")));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		UserService.updateUser(user);
		response.setHeader("user", JSONConverter.convert(user));
	}

	public static void updatePassword(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		User user = UserService.getUser(Integer.valueOf(request.getParameter("id")));
		int oldPass = Objects.hash(user.getUsername(), request.getParameter("oldPass"));
		int newPass = Objects.hash(user.getUsername(), request.getParameter("newPass"));
		int confPass = Objects.hash(user.getUsername(), request.getParameter("confPass"));
		if (user.getHashedPassword() == oldPass && newPass == confPass) {
			user.setHashedPassword(newPass);
			UserService.updateUser(user);
			response.setIntHeader("valid-passwords", 1);
			response.setHeader("user", JSONConverter.convert(user));
		} else {
			response.setIntHeader("valid-passwords", 0);
		}

	}
	
	public static void employeeViewPending(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		List<Request> pending = RequestService.pendingRequests(Integer.valueOf(request.getParameter("employeeId")));
		response.setHeader("pending", JSONConverter.convert(pending));
	}
	
	public static void employeeViewResolved(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		List<Request> resolved = RequestService.resolvedRequests(Integer.valueOf(request.getParameter("employeeId")));
		response.setHeader("resolved", JSONConverter.convert(resolved));
	}
	
	public static void managerViewPending(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		List<Request> pending = RequestService.pendingRequests();
		response.setHeader("pending", JSONConverter.convert(pending));
	}
	
	public static void managerViewResolved(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		List<Request> resolved = RequestService.resolvedRequests();
		response.setHeader("resolved", JSONConverter.convert(resolved));
	}
	
	public static void updateRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Request req = RequestService.getRequest(Integer.valueOf(request.getParameter("id")));
		req.setStatus(Integer.valueOf(request.getParameter("newStatus")));
		req.setManagerId(Integer.valueOf(request.getParameter("managerId")));
		RequestService.updateRequest(req);
	}
	
	public static void createRequest(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		Request newReq = new Request(Integer.valueOf(request.getParameter("empId")),
				Double.valueOf(request.getParameter("amount")),request.getParameter("reason"));
		RequestService.createRequest(newReq);
	}
	
	public static void allEmployees(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		List<Employee> employees = EmployeeService.allEmployees();
		response.setHeader("employees", JSONConverter.convert(employees));
	}
	


}
















