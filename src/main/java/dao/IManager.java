package dao;

import java.util.List;

import models.Manager;

public interface IManager {
	public Manager getManager(int id);
	public Manager getManager(String username);
	public List<Manager> allManagers();
	public boolean deleteManager(Manager manager);
	public boolean updateManager(Manager manager);
	public boolean createManager(Manager manager);

}
