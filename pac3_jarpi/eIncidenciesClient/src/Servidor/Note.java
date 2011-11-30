package Servidor;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 441246979326385209L;
	private int noteId; 	
	private int noteRequestId; 
	private int noteUserId; 
	private Date noteCreationDate;
	private String noteDescription;

	public Note(int noteId, int noteRequestId, int noteUserId, Date noteCreationDate, String noteDescription) {
		this.noteId = noteId; 
		this.noteRequestId = noteRequestId; 
		this.noteDescription = noteDescription; 
		this.noteUserId = noteUserId; 
		this.noteCreationDate = noteCreationDate; 
	}	
	public int noteId() {
		return this.noteId; 
	}
	public int noteRequestId(){
		return this.noteRequestId; 
	}
	public int noteUserId() {
		return this.noteUserId; 
	}
	public Date noteCreationDate(){
		return this.noteCreationDate; 
	}
	public String noteDescription() {
		return this.noteDescription; 
	}
}
