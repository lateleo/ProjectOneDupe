package models;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private int hashedPassword;
	private boolean isManager;
	
//----CONSTRUCTORS------------------------------------------------
	public User(int id, String firstName, String lastName, String username, int hashedPassword, boolean isManager) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.isManager = isManager;
	}
	
	public User() {
		super();
	}
	
//----GETTERS AND SETTERS -----------------------------------------
	//NAMES
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return firstName.concat(" " + lastName);
	}
	
	public String getShortName() {
		return String.valueOf(firstName.charAt(0)).concat(". " + lastName);
	}
	
	public String getNameLastFirst() {
		return lastName.concat(", " + firstName);
	}
	
	//USERNAME
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//PASSWORD
	public int getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(int hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//MANAGER
	public boolean getIsManager() {
		return isManager;
	}
	
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", hashedPassword=" + hashedPassword + ", isManager=" + isManager + "]";
	}

}
