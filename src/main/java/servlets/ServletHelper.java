package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.EmployeeDAO;
import models.Employee;
import service.EmployeeService;
import service.ManagerService;

public class ServletHelper {
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("process");
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(uri.equals("/ProjectOne/username.do")) {
			System.out.println(request.getParameter("username"));
//			Employee emp = EmployeeService.getEmployee(request.getParameter("username"));
//			System.out.println(emp.fullName());
//			response.getWriter().append(emp.fullName());
		}
//		
//		if(uri.equals("")) {
//			response.getWriter().append("");
//		}
//		
//		if(uri.equals("")) {
//			response.sendRedirect("");
//		}
		

	}


}
