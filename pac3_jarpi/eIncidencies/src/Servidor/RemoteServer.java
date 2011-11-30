package Servidor;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
 
public class RemoteServer {
	/* public static void main(String args[]) throws IOException{
		new RemoteServer(); 
	}*/ 
	ServerObjInterface ri = null; 
	private boolean initialized = false; 
	
	public RemoteServer() {
		try{
			// System.out.println("Server: Registrant objecte");
			this.ri = new Servidor.ServerObjInterfaceImpl(); 
			Naming.rebind("//localhost/eIncidencies", ri); 
			// System.out.println("Servidor iniciat.");
			this.initialized = true; 
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
	
	public boolean isInitialized() {
		return this.initialized; 
	}
	
	public void stopRemoteServer() {
		this.initialized = false; 
		this.ri = null; 
		System.exit(0); 
	}
}
