package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerAulesInterface extends Remote{

	int SayHello() throws RemoteException;
	// Met�des del subsistema Aules 
	
}
