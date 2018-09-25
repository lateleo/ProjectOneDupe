package models;

public class Employee extends User {
	
	public Employee(int id, String firstName, String lastName, String username, int hashedPassword) {
		super(id, firstName, lastName, username, hashedPassword, false);
	}
	
	public Employee() {
		super();
	}

}
