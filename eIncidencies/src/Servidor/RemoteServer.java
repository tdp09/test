package Servidor;

import java.io.IOException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
 
public class RemoteServer {
	public static void main(String args[]) throws IOException{
		new RemoteServer(); 
	}
	
	public RemoteServer() {
		try{
			System.out.println("Server: Registrant objecte");
			Servidor.ServerObjInterface ri = new Servidor.ServerObjInterfaceImpl(); 
			Naming.rebind("//localhost/eIncidencies", ri); 
			System.out.println("Servidor iniciat.");
		} catch (RemoteException e) {
			System.out.println("Excepció: RemoteException ");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Excepció: MalformedURL"); 
			e.printStackTrace(); 
		} catch (Exception e) {
			System.out.println("Excepció: Exception"); 
			e.printStackTrace(); 
		}
	}
}
