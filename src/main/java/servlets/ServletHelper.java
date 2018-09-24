package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.EmployeeDAO;
import models.Employee;
import service.EmployeeService;

public class ServletHelper {
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String uri = request.getRequestURI();
		System.out.println(request.getRequestURI());
		if(uri.equals("/ProjectOne/stark.do")) {
			Employee stark = EmployeeService.getEmployee(0);
			System.out.println(stark.fullName());
//			JSONObject obj = new JSONObject();
//			obj.put("FullName", stark.fullName());
			response.getWriter().append(stark.fullName());
			System.out.println(response.toString());
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
