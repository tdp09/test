package Servidor;

import java.util.Date;

public class User {
	private int userId; 
	private String password; 
	private String name;
	private String surname1; 
	private String surname2; 
	private String company; 
	private String address; 
	private int cp; 
	private String city; 
	private int telephone; 
	private int mobilePhone;
	private int roleId; 
	private String email; 
	private Date creationDate; 
	private String creationUser; 
	private Date inactivationDate; 
	private String inactivationUser; 
	
	public User(int userId, String passwd, String name, String surname1, String surname2, String company, String address, int cp, String city, int telephone,
			int mobilePhone, int roleId, String email, Date creationDate, String creationUser,Date inactivationDate, String inactivationUser) {
		this.userId = userId; 
		this.password = passwd; 
		this.name = name; 
		this.surname1 = surname1;  
		this.surname2 = surname2; 
		this.company = company; 
		this.address = address; 
		this.cp = cp; 
		this.city = city; 
		this.telephone = telephone; 
		this.mobilePhone = mobilePhone;
		this.roleId = roleId; 
		this.email = email; 
		this.creationDate = creationDate; 
		this.creationUser = creationUser; 
		this.inactivationDate = inactivationDate; 
		this.inactivationUser = inactivationUser; 
	}
	
	public int getUserId() {return this.userId; }
	public String getPasswd() {return this.password; }
	public String getName() {return this.name; }
	public String getSurname1() {return this.surname1; }
	public String getSurname2() {return this.surname2; }
	public String getCompany() {return this.company; }
	public String getAddress() {return this.address;}
	public int getPostalCode() {return this.cp;}
	public String getCity() {return this.city;}
	public int getTelephone() {return this.telephone;}
	public int getMobilePhone() {return this.mobilePhone;}
	public int getRoleId() {return this.roleId;}
	public String getEmail() {return this.email;}
	public Date getCreationDate() {return this.creationDate;}
	public String getCreationUser() {return this.creationUser;}
	public Date getInactivationDate() {return this.inactivationDate;}
	public String inactivationUser() {return this.inactivationUser; }
}
