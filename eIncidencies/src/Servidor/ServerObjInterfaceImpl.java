package Servidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerObjInterfaceImpl extends UnicastRemoteObject implements ServerObjInterface, Serializable {

	private static final long serialVersionUID = 1L; // Definit dins la classe UnicastRemoteObject 

	protected ServerObjInterfaceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int Suma(int a, int b) {
		System.out.println("Sumant... a=" + a + " + b " + b); 
		return a+b; 
	}
	
	public int Multiplicacio(int a, int b) {
		System.out.println("Multiplicant..."); 
		return a*b; 
	}
}
