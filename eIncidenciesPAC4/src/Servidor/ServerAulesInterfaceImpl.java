package Servidor;
import java.sql.ResultSet;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.io.Serializable;

public class ServerAulesInterfaceImpl extends UnicastRemoteObject implements ServerAulesInterface, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DBConnection objConnection; 
	
	protected ServerAulesInterfaceImpl() throws RemoteException {
		super();
		objConnection = new DBConnection(); 
	}

	@Override
	public int SayHello() throws RemoteException { // Metòde per testejar si hi ha connexió desde el client cap al servidor 
		String sql = "SELECT * FROM SPACES";
		ResultSet rs = this.objConnection.executeSelect(sql);
		ArrayList<Space> arr = new ArrayList<Space>(); 
		try {
			while (rs.next()) {
				int spaceId = rs.getInt("spaceid");
				String desc = rs.getString("spacedescription"); 
				int capacity = rs.getInt("capacity");
				Space spa = new Space(spaceId, desc,capacity); 
				arr.add(spa); 
			}
			rs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't execute query!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
		return 1;
	}


	@Override
	public ArrayList<Booking> getAllBookings() throws RemoteException {
		String sql = "SELECT * FROM bookings";
		ResultSet rs = this.objConnection.executeSelect(sql);
		ArrayList<Booking> arr = new ArrayList<Booking>(); 
		try {
			while (rs.next()) {
				int bookingId = rs.getInt("bookingid");
				String userId = rs.getString("spaceid"); 
				int spaceId = rs.getInt("spaceid"); 
				Date startTime = rs.getDate("start_time"); 
				Date endTime = rs.getDate("end_time"); 
				int pax = rs.getInt("pax"); 
				String status = rs.getString("status"); 
				Date creationTime = rs.getDate("creation_time"); 
				Date cancelTime = rs.getDate("cancel_time"); 
				String cancelUser = rs.getString("cancel_user");
				Booking b = new Booking(bookingId,userId,spaceId,startTime,endTime,pax,status,creationTime,cancelTime,cancelUser); 
				arr.add(b); 
			}
			rs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't execute query!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
		return arr;
	}

	@Override
	public Booking getBookingById(int idBooking) throws RemoteException {
		String sql = "SELECT * FROM bookings WHERE id=" + idBooking; 
		ResultSet rs = this.objConnection.executeSelect(sql);
		Booking b = null; 
		try {
			while (rs.next()) {
				int bookingId = rs.getInt("bookingid");
				String userId = rs.getString("spaceid"); 
				int spaceId = rs.getInt("spaceid"); 
				Date startTime = rs.getDate("start_time"); 
				Date endTime = rs.getDate("end_time"); 
				int pax = rs.getInt("pax"); 
				String status = rs.getString("status"); 
				Date creationTime = rs.getDate("creation_time"); 
				Date cancelTime = rs.getDate("cancel_time"); 
				String cancelUser = rs.getString("cancel_user");
				b = new Booking(bookingId,userId,spaceId,startTime,endTime,pax,status,creationTime,cancelTime,cancelUser); 
			}
			rs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't execute query!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
		return b;
	}

	@Override
	public ArrayList<Booking> getBookingByUser(User u) throws RemoteException {
		String sql = "SELECT * FROM bookings WHERE userid=" + u.getUserId();
		ResultSet rs = this.objConnection.executeSelect(sql);
		ArrayList<Booking> arr = new ArrayList<Booking>(); 
		try {
			while (rs.next()) {
				int bookingId = rs.getInt("bookingid");
				String userId = rs.getString("spaceid"); 
				int spaceId = rs.getInt("spaceid"); 
				Date startTime = rs.getDate("start_time"); 
				Date endTime = rs.getDate("end_time"); 
				int pax = rs.getInt("pax"); 
				String status = rs.getString("status"); 
				Date creationTime = rs.getDate("creation_time"); 
				Date cancelTime = rs.getDate("cancel_time"); 
				String cancelUser = rs.getString("cancel_user");
				Booking b = new Booking(bookingId,userId,spaceId,startTime,endTime,pax,status,creationTime,cancelTime,cancelUser); 
				arr.add(b); 
			}
			rs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't execute query!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
		return arr;
	}

	@Override
	public ArrayList<Booking> getBookingBySpace(Space s) throws RemoteException {
		String sql = "SELECT * FROM bookings WHERE spaceId=" + s.getSpaceId();
		ResultSet rs = this.objConnection.executeSelect(sql);
		ArrayList<Booking> arr = new ArrayList<Booking>(); 
		try {
			while (rs.next()) {
				int bookingId = rs.getInt("bookingid");
				String userId = rs.getString("spaceid"); 
				int spaceId = rs.getInt("spaceid"); 
				Date startTime = rs.getDate("start_time"); 
				Date endTime = rs.getDate("end_time"); 
				int pax = rs.getInt("pax"); 
				String status = rs.getString("status"); 
				Date creationTime = rs.getDate("creation_time"); 
				Date cancelTime = rs.getDate("cancel_time"); 
				String cancelUser = rs.getString("cancel_user");
				Booking b = new Booking(bookingId,userId,spaceId,startTime,endTime,pax,status,creationTime,cancelTime,cancelUser); 
				arr.add(b); 
			}
			rs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't execute query!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
		return arr;
	}

	@Override
	public ArrayList<Booking> getPendingBooking() throws RemoteException {
		String sql = "SELECT * FROM bookings WHERE status='P'";
		ResultSet rs = this.objConnection.executeSelect(sql);
		ArrayList<Booking> arr = new ArrayList<Booking>(); 
		try {
			while (rs.next()) {
				int bookingId = rs.getInt("bookingid");
				String userId = rs.getString("spaceid"); 
				int spaceId = rs.getInt("spaceid"); 
				Date startTime = rs.getDate("start_time"); 
				Date endTime = rs.getDate("end_time"); 
				int pax = rs.getInt("pax"); 
				String status = rs.getString("status"); 
				Date creationTime = rs.getDate("creation_time"); 
				Date cancelTime = rs.getDate("cancel_time"); 
				String cancelUser = rs.getString("cancel_user");
				Booking b = new Booking(bookingId,userId,spaceId,startTime,endTime,pax,status,creationTime,cancelTime,cancelUser); 
				arr.add(b); 
			}
			rs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't execute query!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
		return arr;
	}

	@Override
	public ArrayList<Booking> getConfirmedBooking() throws RemoteException {
		String sql = "SELECT * FROM bookings WHERE status='C'";
		ResultSet rs = this.objConnection.executeSelect(sql);
		ArrayList<Booking> arr = new ArrayList<Booking>(); 
		try {
			while (rs.next()) {
				int bookingId = rs.getInt("bookingid");
				String userId = rs.getString("spaceid"); 
				int spaceId = rs.getInt("spaceid"); 
				Date startTime = rs.getDate("start_time"); 
				Date endTime = rs.getDate("end_time"); 
				int pax = rs.getInt("pax"); 
				String status = rs.getString("status"); 
				Date creationTime = rs.getDate("creation_time"); 
				Date cancelTime = rs.getDate("cancel_time"); 
				String cancelUser = rs.getString("cancel_user");
				Booking b = new Booking(bookingId,userId,spaceId,startTime,endTime,pax,status,creationTime,cancelTime,cancelUser); 
				arr.add(b); 
			}
			rs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't execute query!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
		return arr;
	}

	@Override
	public boolean confirmBooking(Booking request) throws RemoteException, BookingExceptionStatusNotPending {
		// Update 
		// Check if booking status needs to be confirmed
		boolean result = false; 
		if (request.getStatus().equals("P")) {
			String sql = "UPDATE SET status='C' WHERE bookingid=" + request.getBookingId();
			int success = this.objConnection.executeInsert(sql); 
			if (success == 1) result = true; 
		} else {
			throw new BookingExceptionStatusNotPending("Error bookingId: " + request.getBookingId() + "it's not in pending status");
		}
		
		return result;
	}
	
	@Override
	public boolean testSpaceToBooking(Booking request) throws RemoteException {
		// Check if a space is reserved by anyone
		// Falta la validaci— de recursos: Un aula pot tenir qualsevol tipus de recurs el qual Žs finit? Un recurs estˆ associat a una aula en concret i a cap mŽs? 
		boolean result = false; 
		String sql = "SELECT bookingid FROM bookings WHERE spaceid=" + request.getSpaceId() + " AND start_time="+ request.getStartTime() + 
				" AND end_time=" + request.getEndTime() + " AND cancel_time is null";
		ResultSet rs = this.objConnection.executeSelect(sql);
		try {
			if (!rs.next()) {
				result = false; 
			} else {
				result = true; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	@Override
	public boolean cancelBooking(int bookingId, String user) throws RemoteException {
		// Update and calcFee for a space and user
		Booking b = this.getBookingById(bookingId); 
		double fee = this.calcFee(b);  
		 Payment p = this.getPaymentByBookingId(bookingId); 
		 p.setTotalPrice(fee); 
		 this.updatePaymentPrice(p); 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd"); 
		 String sql ="UPDATE booking SET cancel_time=" + Calendar.g AND cancel_user="; 
		
		return false;
	}

	@Override
	public double calcFee(Booking request) throws RemoteException {
		// Calc extra fee if a booking is cancelled 20% if days >= 7 50% if 6 days<= 100% if booking day >= today 
		Payment p = this.getPaymentByBookingId(request.getBookingId()); 
		// Convert dates to milis so can manage it better
		long start = this.toMilis(request.getStartTime()); 
		long today = this.toMilis(Calendar.getInstance().getTime()); 
		// Then calc difference between dates in days 
		int days = this.diffDays(start, today);
		double price=0.0; 
		// Assign total price 
		if (days >= 7){
			// 50% of fee 
			price = ((p.getTotalPrice() * 50) / 100); 
		} else if (days <= 6 && days >=1) {
			// 20% of fee 
			price = ((p.getTotalPrice() * 20) / 100);
		} else if (days < 0){
			// 100% of fee
			price = p.getTotalPrice(); 
		}
		return price;
	}
	
	private long toMilis(Date d) {
		return d.getTime(); 
	}
	
	private int diffDays(long start, long today) {
		long diff = today-start; 
		double days = Math.floor(diff / (1000 * 60 * 60 * 24));
		return (int) days; 
	}
	
	// To be copied in 'Manteniment' 
	public Payment getPaymentByBookingId(int bookingid) {
		String sql = "SELECT * FROM payments WHERE bookingid=" + bookingid; 
		ResultSet rs = this.objConnection.executeSelect(sql); 
		Payment p = null; 
		try {
			while (rs.next()) {
				int bookingId = rs.getInt("bookingid"); 
				Date paymentDate = rs.getDate("payment_date"); 
				double totalPrice = rs.getDouble("total_price"); 
				Date recordDate = rs.getDate("record_date"); 
				p = new Payment(bookingId, paymentDate,totalPrice,recordDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p; 
	}
	// To be copied in 'Manteniment' 
	public boolean updatePaymentPrice(Payment p) {
		boolean result = false; 
		String sql="UPDATE payment SET total_price=" + p.getTotalPrice();
		int success = this.objConnection.executeInsert(sql); 
		if (success == 1) result = true; 
		return result; 
	}

}