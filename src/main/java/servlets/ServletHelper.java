package servlets;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import service.UserService;
import util.JSONConverter;

public class ServletHelper {
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		if(uri.equals("/ProjectOne/login.do")) {
			User user = UserService.getUser(request.getParameter("username"));
			if (user != null && Objects.hash(user.getUsername(),request.getParameter("password")) == user.getHashedPassword()) {
				response.setHeader("user", JSONConverter.convert(user));
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
