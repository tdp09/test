package Servidor;

public class BookingExceptionStatusNotPending extends Exception {

	private static final long serialVersionUID = 6834847702680451164L;
	private String errorMessage; 
	
	public BookingExceptionStatusNotPending(String msg) {
		super(msg); 
		this.errorMessage = msg; 
	}
	
	public String toString() {
		return this.errorMessage; 
	}
}
