package models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class Request {
	private int id;
	private int employeeId;
	private int managerId;
	private Timestamp dateCreated;
	private double amount;
	private String reason;
	private int status = 0;
	
//--CONSTRUCTORS-----------------------------------------------
	public Request() {
		super();
	}
	
	public Request(int id, int employeeId, double amount, String reason) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.amount = amount;
		this.reason = reason;
	}
	
	public Request(int id, int employeeId, int managerId, Timestamp dateCreated, double amount, String reason, int status) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.dateCreated = dateCreated;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
	}


//--GETTERS AND SETTERS----------------------------------------

	//ID
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	//EMPLOYEE ID
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	//MANAGER ID
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	//DATE CREATED
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	//AMOUNT
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	//REASON
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	//STATUS
	public String getStatusStr() {
		return Arrays.asList("Pending","Approved","Denied").get(status);
	}
	
	public int getStatusInt() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	//PSUEDO-GETTERS
	public boolean isPending() {
		return (status == 0);
	}
	
	public boolean isApproved() {
		return (status == 1);
	}
	
	public boolean isDenied() {
		return (status == 2);
	}



}
