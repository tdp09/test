package Servidor;

import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.ArrayList;

public interface ServerAulesInterface extends Remote{

	int SayHello() throws RemoteException;
	// Metodes del subsistema Aules 
	// ArrayList<Booking> getAllRequests() throws RemoteException;
	// Space getSpace() throws RemoteException; 
	ArrayList<Booking> getAllBookings() throws RemoteException; 
	Booking getBookingById(int idBooking) throws RemoteException; 
	ArrayList<Booking> getBookingByUser(User u) throws RemoteException; 
	ArrayList<Booking> getBookingBySpace(Space s) throws RemoteException;
	ArrayList<Booking> getPendingBooking() throws RemoteException;
	ArrayList<Booking> getConfirmedBooking() throws RemoteException;  
	boolean testSpaceToBooking(Booking request) throws RemoteException; 
	boolean confirmBooking(Booking request) throws RemoteException,BookingExceptionStatusNotPending;  // Do a protected method to save Protected objBooking.save()
	boolean cancelBooking(int bookingId, String user) throws RemoteException;  
	double calcFee(Booking request) throws RemoteException; 
}
