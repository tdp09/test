package Servidor;

import java.io.Serializable;
import java.util.Date;

public class Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6662513325336581283L;
	private int requestId; 
	private int userId; 
	private int areaId; 
	private Date creationDate; 
	private String description; 
	private String isSolved; 
	
	public Request(int requestId, int userId, int areaId, Date creationDate, String description, String isSolved) {
		this.requestId = requestId; 
		this.userId = userId; 
		this.areaId = areaId; 
		this.creationDate = creationDate; 
		this.description = description; 
		this.isSolved = isSolved;
	}
	
	public int requestId() {
		return this.requestId; 
	}
	public int userId() {
		return this.userId; 
	}
	public int areaId(){
		return this.areaId; 
	}
	public Date creationDate() {
		return this.creationDate; 
	}
	public String description() {
		return this.description; 
	}
	public String isSolved() {
		return this.isSolved; 
	}
	public String toString() {
		return "Incidència #" + this.requestId + "(" + this.creationDate + ")"; 
	}
}
