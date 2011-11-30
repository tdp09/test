package Servidor;

import java.io.Serializable;
import java.util.Date;

public class Area implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -360029076604407497L;
	private int areaId; 
	private String areaName; 
	private Date areaCreationDate;
	
	public Area(int areaId, String areaName, Date areaCreationDate) {
		this.areaId = areaId; 
		this.areaName = areaName; 
		this.areaCreationDate = areaCreationDate; 
	}
	
	public int areaId(){
		return this.areaId; 
	}
	public String areaName() {
		return this.areaName; 
	}
	public Date areaCreationDate() {
		return this.areaCreationDate; 
	}
	public String toString(){
		return this.areaName; 
	}
}
