package Servidor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerAulesInterfaceImpl extends UnicastRemoteObject implements ServerAulesInterface, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServerAulesInterfaceImpl() throws RemoteException {
		super();
	}

	@Override
	public int SayHello() throws RemoteException { // Met�de per testejar si hi ha connexi� desde el client cap al servidor 
		return 1;
	}
	
}
