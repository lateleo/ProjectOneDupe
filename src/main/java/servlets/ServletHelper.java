package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.EmployeeDAO;
import models.Employee;
import models.User;
import service.EmployeeService;
import service.ManagerService;
import service.UserService;

public class ServletHelper {
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		if(uri.equals("/ProjectOne/login.do")) {
			User user = UserService.getUser(request.getParameter("username"));
			if (user != null && Objects.hash(user.getUsername(),request.getParameter("password")) == user.getHashedPassword()) {
				File file = new File("src/main/webapp/main_page.html");
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = br.readLine();
				StringBuilder content = new StringBuilder(br.readLine());
				while (line != null) {
					content.append(line);
					line = br.readLine();
				}
				br.close();
				response.getWriter().append(content.toString());
			}
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
