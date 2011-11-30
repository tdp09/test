package Servidor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Business {
	DBConnection objDBConnection = null;
	int requestIdCurrentValue = 0; 
	int noteIdNextValue = 0; 
	public Business() {
		this.objDBConnection = new DBConnection(); 
	}
	
	public ArrayList<Request> getRequests(){
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
	public ArrayList<Area> getAreas() {
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
	public ArrayList<User> getUsers(){
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
	private void getCurrentNumberSequenceRequest() {
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
	public int getNextRequestIdValue() {
		this.getCurrentNumberSequenceRequest(); 
		return requestIdCurrentValue; 
	}
	private void getCurrentNumberSequenceNote() {
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
	public int getNextNoteIdValue() {
		this.getCurrentNumberSequenceNote(); 
		return noteIdNextValue; 
	}
	public boolean saveRequest(Request r) {
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
	
	public boolean updateSolvedRequest(Request r) {
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
	
	public boolean saveNote(Note n) {
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
	
	public void dispose() {
		this.objDBConnection.dispose(); 
	}
}
