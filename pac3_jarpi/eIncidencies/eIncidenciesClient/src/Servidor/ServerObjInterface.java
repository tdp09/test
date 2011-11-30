package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerObjInterface extends Remote{
	// Tots els paràmetres i valors d'aquests metodes han de ser tipus primitius de java, 
	//o bé classes que implementin "Serializable", pk? 
	//recordeu que ha de viatjar per la xarxa...
	public int SayHello() throws RemoteException; 
	public ArrayList<Area> getAreas() throws RemoteException; 
	public ArrayList<User> getUsers() throws RemoteException;
	public int getNextRequestIdValue() throws RemoteException;
	public boolean saveRequest(Request r) throws RemoteException;
	public boolean updateSolvedRequest(Request r) throws RemoteException;
	public int getNextNoteIdValue() throws RemoteException;
	public boolean saveNote(Note n) throws RemoteException;
	public ArrayList<Request> getRequests() throws RemoteException;
	public ArrayList<Request> getRequestsByState(String state) throws RemoteException; 
	public ArrayList<Note> getNotesByRequestId(int requestId) throws RemoteException; 
}
