package models;

import util.User;

public class Manager extends User {
	
	public Manager(int id, String firstName, String lastName, String username, int hashedPassword) {
		super(id, firstName, lastName, username, hashedPassword);
	}
	
	public Manager() {
		super();
	}

}
