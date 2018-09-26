package service;

import dao.UserDAO;
import models.User;

public class UserService {
	public static User getUser(int id) {
		return new UserDAO().getUser(id);
	}

	public static User getUser(String username) {
		return new UserDAO().getUser(username);
	}
	
	public static boolean updateUser(User user) {
		return new UserDAO().updateUser(user);
	}

}
