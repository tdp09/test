package Servidor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	private Connection con = null; 
	private String urlConnection = null;  
	private String username = null; 
	private String password = null; 
	
	public DBConnection() {
		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return; 
			}  
			this.loadConnectionProperties(); 
			this.con = DriverManager.getConnection(
					this.urlConnection, this.username,
					this.password);
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			this.con = null; 
			e.printStackTrace();
		}
	}
	
	private void loadConnectionProperties() {
		Properties properties = new Properties();
		try {
			InputStream in = getClass().getResourceAsStream("configuration.properties");
		    properties.load(in);
		    in.close(); 
		} catch (IOException e) {
			System.out.println("Can't open properties file"); 
			e.printStackTrace(); 
		}
		this.urlConnection = properties.getProperty("url");
		this.username = properties.getProperty("username"); 
		this.password = properties.getProperty("password"); 
		
		
	}

	public Connection getConnection() {
		return this.con; 
	}
	
	public ResultSet executeSelect(String sql) {
		Statement select = null;
		ResultSet rs = null; 
		try {
			select = this.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL exception fired!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("Some error ocurred");
			e.printStackTrace(); 
		}
		try {
			rs = select.executeQuery(sql); 
		} catch (SQLException sqle) {
			System.out.println("SQL exception fired in execute sequence request");
			sqle.printStackTrace(); 
		} catch (Exception e) {
			System.out.println("Some error ocurred firing sequence request"); 
			e.printStackTrace(); 
		}
		return rs; 
	}
	
	public int executeInsert(String sql) {
		Statement select = null;
		int rs = 0; 
		try {
			select = this.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL exception fired!"); 
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("Some error ocurred");
			e.printStackTrace(); 
		}
		try {
			rs = select.executeUpdate(sql);  
		} catch (SQLException sqle) {
			System.out.println("SQL exception fired in execute sequence request");
			sqle.printStackTrace(); 
		} catch (Exception e) {
			System.out.println("Some error ocurred firing sequence request"); 
			e.printStackTrace(); 
		}
		return rs;
	}

	public void dispose() {
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't close connection"); 
			e.printStackTrace();
			return; 
		} catch (Exception ee) {
			System.out.println("See printStack for information"); 
			ee.printStackTrace(); 
			return; 
		}
	}
}