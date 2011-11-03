package Client; 
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Servidor.ServerObjInterface;



public class Client {
	
	private ServerObjInterface RI;
 	private final String url = new String("rmi://localhost/eIncidencies");
	
 	public static void main(String args[]) throws IOException{
 		Client cl = new Client(); 
 		cl.testSuma(); 
 		cl.testMult(); 
 	}
 	
	public Client() {
		try {
			System.out.println("Connectant al servidor..."); 
			RI = (ServerObjInterface) Naming.lookup(url); 
			System.out.println("Connectat"); 
		} catch (NotBoundException e) {
			System.out.println("Client Excepció: NotBoundException"); 
			e.printStackTrace(); 
			System.exit(0); 
		} catch(RemoteException e) {
			System.out.println("Client Excepció: RemoteException ");
			e.printStackTrace();
		} catch(MalformedURLException e) {
			System.out.println("Client Excepció: MalformedURL"); 
			e.printStackTrace(); 
		} catch (Exception  e){
			System.out.println("Client Excepció: Exception"); 
			e.printStackTrace(); 
		}
	}
	
	private void testSuma() {
		System.out.println("Executant metode suma..."); 
		try {
			System.out.println(RI.Suma(2, 3)); 
		} catch (RemoteException e) {
			System.out.println("Ha ocorregut un error sumant"); 
			e.printStackTrace(); 
		}
	}
	private void testMult() {
		System.out.println("Executant metode multiplicacio..."); 
		try {
			System.out.println(RI.Multiplicacio(2, 3)); 
		}catch (RemoteException e) {
			System.out.println("Ha ocorregut un error multiplicant");
			e.printStackTrace(); 
		}
	}
}
