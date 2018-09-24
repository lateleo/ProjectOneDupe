package service;

import java.util.List;

import dao.EmployeeDAO;
import models.Employee;


public class EmployeeService {
	
	public static Employee getEmployee(int id) {
		return new EmployeeDAO().getEmployee(id);
	}

	public static Employee getEmployee(String username) {
		return new EmployeeDAO().getEmployee(username);
	}
	public static List<Employee> allEmployees(){
		return new EmployeeDAO().allEmployees();
	}
	
	public static boolean updateEmployee(Employee employee) {
		return new EmployeeDAO().updateEmployee(employee);
	}
	
	public static boolean deleteEmployee(Employee employee) {
		return new EmployeeDAO().deleteEmployee(employee);
	}
	
	public static boolean createEmployee(Employee employee) {
		return new EmployeeDAO().createEmployee(employee);
	}

}
