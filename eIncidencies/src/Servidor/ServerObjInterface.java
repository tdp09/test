package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerObjInterface extends Remote{
	// Tots els paràmetres i valors d'aquests metodes han de ser tipus primitius de java, 
	//o bé classes que implementin "Serializable", pk? 
	//recordeu que ha de viatjar per la xarxa...
	public int Suma(int a, int b) throws RemoteException;
	public int Multiplicacio(int a, int b) throws RemoteException; 
}
