package Servidor;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
 
public class RemoteServer {
	/* public static void main(String args[]) throws IOException{
		new RemoteServer(); 
	}*/ 
	ServerAulesInterface objAules = null; // Creació variable de classe, aules
	private boolean initialized = false; 
	
	public RemoteServer() {
		try{
			System.out.println("Server: Registrant objecte");
			this.objAules = new Servidor.ServerAulesInterfaceImpl(); // Registrar la implementació d'aules 
			Naming.rebind("//localhost/eAules/aules", objAules); // Assignar URL Aules 
			this.initialized = true;  
			this.objAules.SayHello(); 
			// Inicialitzar per a cada subsistema  la seva própia URL 
			
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
		this.objAules = null; 
		System.exit(0); 
	}
}
