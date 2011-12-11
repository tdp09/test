package Servidor;

import java.util.Date;

public class Payment {
	private int bookingId; 
	private Date paymentDate; 
	private double totalPrice; 
	private Date recordDate; 
	
	public Payment(int bookingId, Date paymentDate, double totalPrice, Date recordDate) {
		this.bookingId = bookingId; 
		this.paymentDate = paymentDate; 
		this.totalPrice = totalPrice; 
		this.recordDate = recordDate; 
	}
	
	public int getBookingId() {return this.bookingId;}
	public Date getPaymentDate()  {return this.paymentDate; }
	public double getTotalPrice() {return this.totalPrice; }
	public Date getRecordDate() {return this.recordDate;}
	public void setTotalPrice(double price) {
		this.totalPrice = price;
	}
}
