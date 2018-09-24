package service;

import java.util.List;

import dao.ManagerDAO;
import models.Manager;

public class ManagerService {
	
	public static Manager getManager(int id) {
		return new ManagerDAO().getManager(id);
	}

	public static Manager getManager(String username) {
		return new ManagerDAO().getManager(username);
	}
	public static List<Manager> allManagers(){
		return new ManagerDAO().allManagers();
	}
	
	public static boolean updateManager(Manager manager) {
		return new ManagerDAO().updateManager(manager);
	}
	
	public static boolean deleteManager(Manager manager) {
		return new ManagerDAO().deleteManager(manager);
	}
	
	public static boolean createManager(Manager manager) {
		return new ManagerDAO().createManager(manager);
	}

}
