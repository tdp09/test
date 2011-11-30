 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;
 import java.util.Properties;
 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.List;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.rmi.server.UnicastRemoteObject;
 import java.io.Serializable;
import java.rmi.RemoteException;

 public class GestorBBDDImpl extends UnicastRemoteObject 
 	implements GestorBBDDInterface,Serializable{

	private static final long serialVersionUID = 1L;
	
	private Connection conexion;
 	final String propertiesFile = new File(".").getAbsolutePath()+"/properties/configuration.properties";
 	final String driver = "org.postgresql.Driver";
	
	public GestorBBDDImpl() throws RemoteException{
        super();
}
	private String message = new String();
	public boolean conectaBD() throws RemoteException{
 		try {			
 			
 			//Recoger el driver JDBC especifico de Postgre
 			Class.forName(driver);
 			//Recuperar información del fichero de propiedades
 			Properties prop = new Properties();
 			prop.load(new FileInputStream(propertiesFile));       
 			String url = prop.getProperty("url");
 			String usuario = prop.getProperty("username");
 			String clave= prop.getProperty("password");
 			//Montar la conexion a la BBDD
 			conexion = DriverManager.getConnection(url, usuario, clave);
 		} catch (ClassNotFoundException exc) {
 			//No se encuentra el driver JDBC de Postgre en el classpath
 			message = TDSLanguageUtils.getMessage("errorDriverNotFound"); 			
 			Errors.main(message);
 			return false;
 		} catch (FileNotFoundException exc) {
 			//No se encuentra el archivo "configuration.properties"
 			message = TDSLanguageUtils.getMessage("errorFileNotFound"); 			
 			Errors.main(message);
 			return false;
 		} catch (IOException exc) {
 			//El fichero de properties no es correcto
 			message = TDSLanguageUtils.getMessage("errorFileNotCorrect"); 			
 			Errors.main(message);
 			return false;
 		} catch (SQLException exc) {
 			//La base de dades no existe, está parada, o login incorrecto
 			message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);
 			return false;
 		} catch (Exception exc){
 			//Excepción inesperada
 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 			Errors.main(message);
 			return false;
 		} 
 		return true;
	}
	
	public boolean desconectaBD() throws RemoteException{
 		try {						
 			
 			conexion.close();
 		} catch (SQLException exc) {
 			//La base de dades no existe, está parada, o login incorrecto
 			message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);
 			return false;
 		} catch (Exception exc){
 			//Excepción inesperada
 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 			Errors.main(message);
 			return false;
 		} 
 		return true;
	}
 	
	public void insertaIncidencia(Incidencia inc) throws RemoteException{
		
		PreparedStatement st = null;
 	 	conectaBD();
 	 	try {
 	 		String query = "INSERT INTO TB_REQUEST (request_id,user_id,area_id,creation_date,description,is_request_solved) VALUES (?, ?, ?, ?, ?, ?)";	
 	 		st = conexion.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	 		st.setInt(1,inc.getid());
 	 		st.setInt(2,inc.getidUser());
 	 		st.setInt(3,inc.getidAula());
 	 		st.setDate(4,inc.getCreationDate());
 	 		st.setString(5,inc.getDescription());
 	 		st.setString(6,inc.getisSolved());
 	 		st.execute();	
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);		
 	 	}
 	 	finally{
 	 		try{			
 	 			if(st!=null){
 	 				st.close();
 	 			}
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	 }
 	
 	public void insertaApunt(Apunt apu) throws RemoteException{
 		
 		conectaBD();
 		PreparedStatement st = null;		
 	 	try {
 	 		String query = "INSERT INTO TB_REQUEST_NOTE (check_id,request_id,user_id,creation_date,description) VALUES (?, ?, ?, ?, ?)";	
 	 		st = conexion.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	 		st.setInt(1,apu.getid());
 	 		st.setInt(2,apu.getidIncidencia());
 	 		st.setInt(3,apu.getidUser());
 	 		st.setDate(4,apu.getCreationDate());
 	 		st.setString(5,apu.getDescription());
 	 		st.execute();	
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);		
 	 	}
 	 	finally{
 	 		try{			
 	 			if(st!=null){
 	 				st.close();
 	 			}
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	 }
 	
 	public int countIncidenciesByStatus(String status) throws RemoteException{
 		
 		conectaBD();
 		int count = 0;
 		Statement st = null;
 	 	ResultSet rs = null;	 	
 	 	try {
 	 		String query = "SELECT COUNT (*) AS number FROM TB_REQUEST WHERE is_request_solved = '" + status + "'";	
 	 		st = conexion.createStatement();	
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			count = rs.getInt("number");
 	 		}	
 	 }catch (SQLException e) {
 		 	message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 		 	Errors.main(message);		
	 	}
	 	finally{
	 		try{			
	 			if(st!=null){
	 				st.close();
	 			}
	 		}catch(Exception e){
	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
	 			Errors.main(message);
	 		}
	 	}
 	 	desconectaBD();
 	 	return count;
	 }
 	
 	public int countIncidencies() throws RemoteException{
 		
 		conectaBD();
 		int count = 0;
 		Statement st = null;
 	 	ResultSet rs = null;	 	
 	 	try {
 	 		String query = "SELECT COUNT (*) AS number FROM TB_REQUEST";	
 	 		st = conexion.createStatement();	
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			count = rs.getInt("number");
 	 		}	
 	 }catch (SQLException e) {
 		 	message = TDSLanguageUtils.getMessage("errorDataBase"); 			
			Errors.main(message);
	 	}
	 	finally{
	 		try{			
	 			if(st!=null){
	 				st.close();
	 			}
	 		}catch(Exception e){
	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
	 			Errors.main(message);
	 		}
	 	}
 	 	desconectaBD();
 	 	return count;
	 }
 	
 	public int countApunts() throws RemoteException{
 		
 		conectaBD();
 		int count = 0;
 		Statement st = null;
 	 	ResultSet rs = null;	 	
 	 	try {
 	 		String query = "SELECT COUNT (*) AS number FROM TB_REQUEST_NOTE";	
 	 		st = conexion.createStatement();	
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			count = rs.getInt("number");
 	 		}	
 	 }catch (SQLException e) {
 		 	message = TDSLanguageUtils.getMessage("errorDataBase"); 			
			Errors.main(message);		
	 	}
	 	finally{
	 		try{			
	 			if(st!=null){
	 				st.close();
	 			}
	 		}catch(Exception e){
	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
	 			Errors.main(message);
	 		}
	 	}
 	 	desconectaBD();
 	 	return count;
	 }
 	
 	public Incidencia getIncidenciaById(int id) throws RemoteException{
 		
 		conectaBD();
 		Incidencia incidencia = new Incidencia();	
 	 	Statement st = null;
 	 	ResultSet rs = null;
 	 	try {
 	 		st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	 		String query = "SELECT * FROM TB_REQUEST WHERE request_id = '" + id + "'" + "ORDER BY request_id";			
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			incidencia.setid(rs.getInt("request_id"));
 	 			incidencia.setidUser(rs.getInt("user_id"));
 	 			incidencia.setidAula(rs.getInt("area_id"));
 	 			incidencia.setCreationDate(rs.getDate("creation_date"));
 	 			incidencia.setDescription(rs.getString("description"));
 	 			incidencia.setisSolved(rs.getString("is_request_solved"));
 	 		}
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);			
 	 	}
 	 	finally{
 	 		try{
 	 			rs.close();
 	 			st.close();
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	 	return incidencia;
 	}
 	
 	public List<Incidencia> getListaIncidenciesByStatus(String status) throws RemoteException{
 		
 		conectaBD();
 		List<Incidencia> lista = new ArrayList<Incidencia>();	
 	 	Statement st = null;
 	 	ResultSet rs = null;
 	 	try {
 	 		st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	 		String query = "SELECT * FROM TB_REQUEST WHERE is_request_solved = '" + status + "'" + "ORDER BY request_id";			
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			Incidencia incidencia = new Incidencia();
 	 			incidencia.setid(rs.getInt("request_id"));
 	 			incidencia.setidUser(rs.getInt("user_id"));
 	 			incidencia.setidAula(rs.getInt("area_id"));
 	 			incidencia.setCreationDate(rs.getDate("creation_date"));
 	 			incidencia.setDescription(rs.getString("description"));
 	 			incidencia.setisSolved(rs.getString("is_request_solved"));
 	 			lista.add(incidencia);
 	 		}
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);			
 	 	}
 	 	finally{
 	 		try{
 	 			rs.close();
 	 			st.close();
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	 	return lista;
 	}
 	
 	public List<Incidencia> getListaIncidencies() throws RemoteException{
 		
 		conectaBD();
 		List<Incidencia> lista = new ArrayList<Incidencia>();	
 	 	Statement st = null;
 	 	ResultSet rs = null;
 	 	try {
 	 		st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	 		String query = "SELECT * FROM TB_REQUEST ORDER BY request_id";			
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			Incidencia incidencia = new Incidencia();
 	 			incidencia.setid(rs.getInt("request_id"));
 	 			incidencia.setidUser(rs.getInt("user_id"));
 	 			incidencia.setidAula(rs.getInt("area_id"));
 	 			incidencia.setCreationDate(rs.getDate("creation_date"));
 	 			incidencia.setDescription(rs.getString("description"));
 	 			incidencia.setisSolved(rs.getString("is_request_solved"));
 	 			lista.add(incidencia);
 	 		}
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);			
 	 	}
 	 	finally{
 	 		try{
 	 			rs.close();
 	 			st.close();
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	 	return lista;
 	}
 	
 	public List<Aula> getListaAules() throws RemoteException{
 		
 		conectaBD();
 		List<Aula> lista = new ArrayList<Aula>();	
 	 	Statement st = null;
 	 	ResultSet rs = null;
 	 	try {
 	 		st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	 		String query = "SELECT * FROM TB_AREA ORDER BY area_id";			
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			Aula aula = new Aula();
 	 			aula.setid(rs.getInt("area_id"));
 	 			aula.setName(rs.getString("area_name"));
 	 			aula.setCreationDate(rs.getDate("creation_date"));	 			
 	 			lista.add(aula);
 	 		}
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);			
 	 	}
 	 	finally{
 	 		try{
 	 			rs.close();
 	 			st.close();
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	 	return lista;
 	}
 	
 	public List<Usuari> getListaUsuaris() throws RemoteException{
 		
 		conectaBD();
 		List<Usuari> lista = new ArrayList<Usuari>();	
 	 	Statement st = null;
 	 	ResultSet rs = null;
 	 	try {
 	 		st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	 		String query = "SELECT * FROM TB_USER ORDER BY user_id";			
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			Usuari usuari = new Usuari();
 	 			usuari.setid(rs.getInt("user_id"));
 	 			usuari.setName(rs.getString("user_name"));
 	 			usuari.setCreationDate(rs.getDate("creation_date"));	 			
 	 			lista.add(usuari);
 	 		}
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);			
 	 	}
 	 	finally{
 	 		try{
 	 			rs.close();
 	 			st.close();
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	 	return lista;
 	}
 	
 	public List<Apunt> getListaApunts(int id) throws RemoteException{
 		
 		conectaBD();
 		List<Apunt> lista = new ArrayList<Apunt>();	
 	 	Statement st = null;
 	 	ResultSet rs = null;
 	 	try {
 	 		st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 	 		String query = "SELECT * FROM TB_REQUEST_NOTE WHERE request_id = '" + id + "' ORDER BY check_id";			
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			Apunt apunt = new Apunt();
 	 			apunt.setid(rs.getInt("check_id"));
 	 			apunt.setidIncidencia(rs.getInt("request_id"));
 	 			apunt.setidUser(rs.getInt("user_id"));
 	 			apunt.setCreationDate(rs.getDate("creation_date"));
 	 			apunt.setDescription(rs.getString("description"));
 	 			lista.add(apunt);
 	 		}
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorDataBase"); 			
 			Errors.main(message);			
 	 	}
 	 	finally{
 	 		try{
 	 			rs.close();
 	 			st.close();
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	 	return lista;
 	}
 	
 	public int getNextValApunt() throws RemoteException{
 		
 		conectaBD();
 		int nextval = 0;
 		Statement st = null;
 	 	ResultSet rs = null;	 	
 	 	try {
 	 		String query = "SELECT nextval('SQ_REQUEST_NOTE') AS number";	
 	 		st = conexion.createStatement();	
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			nextval = rs.getInt("number");
 	 		}	
 	 }catch (SQLException e) {
 		 message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 		 Errors.main(message);		
	 	}
	 	finally{
	 		try{			
	 			if(st!=null){
	 				st.close();
	 			}
	 		}catch(Exception e){
	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
	 		}
	 	}
 	 	desconectaBD();
 	 	return nextval;
	}
 	
 	public int getNextValIncidencia() throws RemoteException{
 		
 		conectaBD();
 		int nextval = 0;
 		Statement st = null;
 	 	ResultSet rs = null;	 	
 	 	try {
 	 		String query = "SELECT nextval('SQ_REQUEST') AS number";	
 	 		st = conexion.createStatement();	
 	 		rs = st.executeQuery(query);
 	 		while (rs.next()) {
 	 			nextval = rs.getInt("number");
 	 		}	
 	 }catch (SQLException e) {
 		 message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 		 Errors.main(message);	
	 	}
	 	finally{
	 		try{			
	 			if(st!=null){
	 				st.close();
	 			}
	 		}catch(Exception e){
	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
	 		}
	 	}
 	 	desconectaBD();
 	 	return nextval;
	}

 	public void actualitzaIncidenciaResoltaById(int id) throws RemoteException{
 		
 		Statement st = null;
 	 	conectaBD();
 	 	try {
 	 		String query = "UPDATE TB_REQUEST SET is_request_solved = 'Y' WHERE request_id = " + id;	
 	 		st = conexion.createStatement();	
 	 		st.execute(query);	
 	 	}catch (SQLException e) {
 	 		message = TDSLanguageUtils.getMessage("errorUnespected"); 			
	 		Errors.main(message);		
 	 	}
 	 	finally{
 	 		try{			
 	 			if(st!=null){
 	 				st.close();
 	 			}
 	 		}catch(Exception e){
 	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 	 			Errors.main(message);
 	 		}
 	 	}
 	 	desconectaBD();
 	}
 }
