package servlets;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import models.User;
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
		System.out.println(user.getHashedPassword());
		System.out.println(oldPass);
		System.out.println(newPass);
		System.out.println(confPass);
		if (user.getHashedPassword() == oldPass && newPass == confPass) {
			System.out.println("true");
			user.setHashedPassword(newPass);
			UserService.updateUser(user);
			response.setIntHeader("valid-passwords", 1);
			response.setHeader("user", JSONConverter.convert(user));
		} else {
			System.out.println("false");
			response.setIntHeader("valid-passwords", 0);
		}

	}

}
