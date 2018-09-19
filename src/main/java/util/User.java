package util;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private int hashedPassword;
	
//----CONSTRUCTORS------------------------------------------------
	public User(int id, String firstName, String lastName, String username, int hashedPassword) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.hashedPassword = hashedPassword;
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
	
	public String fullName() {
		return firstName.concat(" " + lastName);
	}
	
	public String shortName() {
		return String.valueOf(firstName.charAt(0)).concat(". " + lastName);
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

}
