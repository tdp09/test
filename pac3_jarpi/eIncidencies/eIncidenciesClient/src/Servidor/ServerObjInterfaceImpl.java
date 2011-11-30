package Servidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ServerObjInterfaceImpl extends UnicastRemoteObject implements ServerObjInterface, Serializable {
	
	private static final long serialVersionUID = 1L; // Definit dins la classe UnicastRemoteObject 
	DBConnection objDBConnection = null;
	int requestIdCurrentValue = 0; 
	int noteIdNextValue = 0; 
	
	protected ServerObjInterfaceImpl() throws RemoteException {
		super();
		this.objDBConnection = new DBConnection(); 
		// TODO Auto-generated constructor stub
	}

	@Override 
	public int SayHello() {
		return 1; 
	}
	
	@Override
	public ArrayList<Area> getAreas() throws RemoteException{
		ArrayList<Area> result = new ArrayList<Area>();
		String sql ="SELECT * FROM tb_area";
		ResultSet rs = this.objDBConnection.executeSelect(sql);
		try {
			while (rs.next()) {
				int areaId = rs.getInt("area_id");
				String areaName = rs.getString("area_name");
				Date areaCreationDate = rs.getDate("creation_date"); 
				Area a = new Area(areaId, areaName,areaCreationDate); 
				result.add(a); 
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
		return result;
	}

	@Override
	public ArrayList<User> getUsers() throws RemoteException{
		ArrayList<User> result = new ArrayList<User>();
		String sql ="SELECT * FROM tb_user";
		ResultSet rs = this.objDBConnection.executeSelect(sql);
		try {
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				Date userCreationDate = rs.getDate("creation_date"); 
				User a = new User(userId, userName,userCreationDate); 
				result.add(a); 
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
		return result;
	}

	@Override
	public int getNextRequestIdValue() throws RemoteException{
		this.getCurrentNumberSequenceRequest(); 
		return requestIdCurrentValue; 
	}

	@Override
	public boolean saveRequest(Request r) throws RemoteException{
		String sql = "INSERT INTO tb_request VALUES("+r.requestId()+","+r.userId()+","+r.areaId()+",now(),'" + r.description()+"','"+r.isSolved()+"')"; 
		int rs = this.objDBConnection.executeInsert(sql); 
		boolean isInserted = false; 
		if (rs == 1){
			isInserted = true; 
		} else {
			isInserted = false; 
		}
		return isInserted;
	}

	@Override
	public boolean updateSolvedRequest(Request r) throws RemoteException{
		boolean isUpdated = false; 
		String sql = "UPDATE tb_request SET is_request_solved = 'Y' WHERE request_id=" + r.requestId(); 
		int rs = this.objDBConnection.executeInsert(sql);
		if (rs == 1) {
			isUpdated = true; 
		} else {
			isUpdated = false; 
		}
		return isUpdated;
	}

	@Override
	public int getNextNoteIdValue() throws RemoteException{
		this.getCurrentNumberSequenceNote(); 
		return noteIdNextValue; 
	}

	@Override
	public boolean saveNote(Note n) throws RemoteException{
		boolean isInserted = false; 
		String sql = "INSERT INTO tb_request_note VALUES("+n.noteId()+","+n.noteRequestId()+","+n.noteUserId()+",'"+n.noteCreationDate()+"','"+n.noteDescription()+"')";
		int rs = this.objDBConnection.executeInsert(sql); 
		if (rs == 1) {
			isInserted = true; 
		} else {
			isInserted = false; 
		}
		return isInserted;
	}

	@Override
	public ArrayList<Request> getRequests() throws RemoteException{
		ArrayList<Request> result = new ArrayList<Request>(); 
		String sql = "SELECT * FROM tb_request WHERE is_request_solved='N'";
		ResultSet rs = this.objDBConnection.executeSelect(sql); 
		
		try {
			while (rs.next()) {
				int requestId = rs.getInt("request_id");
				int requestUserId = rs.getInt("user_id"); 
				int requestAreaId = rs.getInt("area_id");
				Date requestCreationDate = rs.getDate("creation_date");
				String requestDescription = rs.getString("description"); 
				String requestIsSolved = rs.getString("is_request_solved"); 
				Request r = new Request(requestId, requestUserId,requestAreaId,requestCreationDate,requestDescription, requestIsSolved); 
				result.add(r); 
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
		return result;
	}
	private void getCurrentNumberSequenceNote() throws RemoteException{
		String sql = "SELECT nextval('sq_request_note')"; 
		ResultSet rs = this.objDBConnection.executeSelect(sql); 
		try {
			while (rs.next()) {
				this.noteIdNextValue = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception looping result set"); 
			e.printStackTrace();
		}catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
	}
	private void getCurrentNumberSequenceRequest() throws RemoteException{
		String sql = "SELECT nextval('sq_request')"; 
		ResultSet rs = this.objDBConnection.executeSelect(sql); 
		try {
			while (rs.next()) {
				this.requestIdCurrentValue = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception looping result set"); 
			e.printStackTrace();
		}catch (Exception e){
			System.out.println("An error ocurred"); 
			e.printStackTrace(); 
		}
	}

	@Override
	public ArrayList<Request> getRequestsByState(String state) throws RemoteException {
		String sql = "";
		if (state.isEmpty()) {
			sql = "SELECT * FROM tb_request ORDER BY request_id";
		}else {
			sql = "SELECT * FROM tb_request WHERE is_request_solved='" +state + "' ORDER BY request_id";
		}
		ResultSet rs = this.objDBConnection.executeSelect(sql); 
		ArrayList<Request> result = new ArrayList<Request>(); 
		try {
			while (rs.next()) {
				int requestId = rs.getInt("request_id");
				int requestUserId = rs.getInt("user_id"); 
				int requestAreaId = rs.getInt("area_id");
				Date requestCreationDate = rs.getDate("creation_date");
				String requestDescription = rs.getString("description"); 
				String requestIsSolved = rs.getString("is_request_solved"); 
				Request r = new Request(requestId, requestUserId,requestAreaId,requestCreationDate,requestDescription, requestIsSolved);
				result.add(r); 
			}
		} catch(SQLException e) {
			System.out.println("An error ocurred while executing query getRequestsByState");
			e.printStackTrace(); 
		}
		return result;
	}

	@Override
	public ArrayList<Note> getNotesByRequestId(int requestId) throws RemoteException {
			String sql = "SELECT * FROM tb_request_note WHERE request_id=" + requestId;
			ResultSet rs = this.objDBConnection.executeSelect(sql);
			ArrayList<Note> result = new ArrayList<Note>(); 
			try {
				while (rs.next()){
					int noteId = rs.getInt("check_id");
					int noteRequestId = requestId; 
					int noteUserId = rs.getInt("user_id");
					Date noteCreationDate = rs.getDate("creation_date");
					String noteDescription = rs.getString("description");
					Note n = new Note(noteId,noteRequestId,noteUserId,noteCreationDate,noteDescription);
					result.add(n); 
				}
			} catch (SQLException e) {
				System.out.println("An error ocurred while executing query");
				e.printStackTrace();
			}
		return result;
	}
	
}
