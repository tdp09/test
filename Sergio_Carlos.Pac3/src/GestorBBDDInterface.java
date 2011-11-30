 
 import java.util.List;
 import java.rmi.RemoteException;
import java.rmi.Remote;

 public interface GestorBBDDInterface extends Remote{

 	public boolean conectaBD() throws RemoteException;
 	public boolean desconectaBD() throws RemoteException;
 	public void insertaIncidencia(Incidencia inc) throws RemoteException;
 	public void insertaApunt(Apunt apu) throws RemoteException;
 	public int countIncidenciesByStatus(String status) throws RemoteException;
 	public int countIncidencies() throws RemoteException;
 	public int countApunts() throws RemoteException;
 	public Incidencia getIncidenciaById(int id) throws RemoteException;
 	public List<Incidencia> getListaIncidenciesByStatus(String status) throws RemoteException;
 	public List<Incidencia> getListaIncidencies() throws RemoteException;
 	public List<Aula> getListaAules() throws RemoteException;
 	public List<Usuari> getListaUsuaris() throws RemoteException;
 	public List<Apunt> getListaApunts(int id) throws RemoteException;
 	public int getNextValApunt() throws RemoteException;
 	public int getNextValIncidencia() throws RemoteException;
 	public void actualitzaIncidenciaResoltaById(int id) throws RemoteException;
 	
 }
