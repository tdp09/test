package Servidor;

import java.util.Date;

public class Booking {
	private int bookingId; 
	private String userId; 
	private int spaceId; 
	private Date startTime; 
	private Date endTime; 
	private int pax; 
	private String status; 
	private Date creationTime; 
	private Date cancelTime; 
	private String cancelUser; 
	private String cancelReason; 
	
	
	public Booking(int bookingId, String userId, int spaceId, Date startTime, Date endTime, int pax, String status, Date creationTime, Date cancelTime, String cancelUser, String cancelReason) {
		this.bookingId = bookingId; 
		this.userId = userId; 
		this.spaceId = spaceId; 
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.pax = pax; 
		this.status = status; 
		this.creationTime = creationTime; 
		this.cancelTime = cancelTime; 
		this.cancelUser  = cancelUser; 
		this.cancelReason = cancelReason; 
	}
	
	/* public Booking(int bookingId, String userId, int spaceId, Date startTime, Date endTime, int pax, String status, Date creationTime) {
		this.bookingId = bookingId; 
		this.userId = userId; 
		this.spaceId = spaceId; 
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.pax = pax; 
		this.status = status; 
		this.creationTime = creationTime; 
		this.cancelTime = null; 
		this.cancelUser = null; 
	}*/ 
	
	public int getBookingId() { return this.bookingId; } 
	public String getUserId() {return this.userId;}
	public int getSpaceId() {return this.spaceId; }
	public Date getStartTime() {return this.startTime;}
	public Date getEndTime() {return this.endTime; }
	public int getCapacity() {return this.pax; }
	public Date getCreationTime() {return this.creationTime;}
	public Date getCancelTime() {return this.cancelTime;}
	public String getCancelUser() {return this.cancelUser;}
	public String getStatus() {return this.status;}
	
}
