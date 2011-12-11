package Servidor;

import java.util.ArrayList;

// Booking class 
public class Space {
	int spaceId; 
	String spaceDesc; 
	int spaceCapacity;
	double spacePriceHour; 
	double spacePriceDay; 
	String spaceUrlMap; 
	String spaceGet;
	int spacePostalCode; 
	String spaceCity; 
	String spaceAddress; 
	String spaceCreationDate; 
	int spaceCreationUser; 
	String spaceInactivationDate; 
	int  spaceInactivationUser; 
	String spaceRecordDate; 
	ArrayList<Resource> spaceResources; 
	
	public Space(int spaceId, String desc, int capacity, double Phour, double Pday, String url, String get, int cp, String city, String address, String creation, int creationUser,
			String inactivationDate, int inactivationUser) {
		
	}
	
	public Space(int spaceId, String desc, int capacity) {
		this.spaceId = spaceId; 
		this.spaceDesc = desc; 
		this.spaceCapacity = capacity; 
	}
	
	private ArrayList<Resource> loadResources() {
		return null; 
	}
	
	public ArrayList<Resource> getResources() {
		return spaceResources;
	}
	public int getSpaceId() {return this.spaceId; } 
	public String getSpaceDesc() { return this.spaceDesc; } 
	public int getSpaceCapacity() {return this.spaceCapacity; }
	public double getPriceHour(){ return this.spacePriceHour;}
	public double getPriceDay() { return this.spacePriceDay; }
	public String getUrl() {return this.spaceUrlMap; }
	public String getHoToGet() { return this.spaceGet; }
	public int getPostalCode() {return this.spacePostalCode; }
	public String getCity() { return this.spaceCity; }
	public String getAddress() {return this.spaceAddress;}
	public String getCreationDate() {return this.spaceCreationDate;}
	public int getCreationUser() {return this.spaceCreationUser;}
	public String getInacativationDate() {return this.spaceInactivationDate;}
	public int getInacativationUser() {return this.spaceInactivationUser; }
	
	public void setInactivationDate(String date) {
		this.spaceInactivationDate = date; 
	}
	
	public void setInactivationUser(int userId) {
		this.spaceInactivationUser = userId; 
	}
	
}
