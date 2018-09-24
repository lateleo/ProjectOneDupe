package dao;

import java.util.List;

import models.Employee;


public interface IEmployee {
	public Employee getEmployee(int id);
	public  Employee getEmployee(String username);
	public  List<Employee> allEmployees();
	public  boolean deleteEmployee(Employee employee);
	public  boolean updateEmployee(Employee employee);
	public  boolean createEmployee(Employee employee);
}
