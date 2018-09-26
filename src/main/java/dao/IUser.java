package dao;

import models.User;

public interface IUser {
	public User getUser(int id);
	public  User getUser(String username);
	public boolean updateUser(User user);
}
