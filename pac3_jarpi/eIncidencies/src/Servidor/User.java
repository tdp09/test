package Servidor;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8049210036202702826L;
	private int userId; 
	private String userName; 
	private Date userCreationDate; 
	
	public User(int userId, String userName, Date userCreationDate) {
		this.userId = userId; 
		this.userName = userName; 
		this.userCreationDate = userCreationDate; 
	}
	
	public int UserId() {
		return this.userId; 
	}
	public String userName() {
		return this.userName; 
	}
	public Date userCreationDate() {
		return this.userCreationDate; 
	}
	
	public String toString() {
		return this.userName; 
	}
}
