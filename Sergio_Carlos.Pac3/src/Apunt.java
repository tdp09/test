
import java.io.Serializable;
import java.sql.Date;

public class Apunt implements Serializable {
 	private static final long serialVersionUID = 1L;
 	private int id;
 	private int idIncidencia;
 	private int idUser;
 	private Date creationDate;
 	private String description;
 	
 	public Apunt(){
 		super();
 	}
 	
 	public Apunt(int id, int idIncidencia, int idUser,  Date creationDate, String description){
 		super();
 		this.id=id;
 		this.idIncidencia=idIncidencia;
 		this.idUser=idUser;
 		this.creationDate=creationDate;	
 		this.description=description;
 	}
 	
 	public int getid() {
 		return id;
 	}
 	public void setid(int id) {
 		this.id = id;
 	}
 	public int getidIncidencia() {
 		return idIncidencia;
 	}
 	public void setidIncidencia(int idIncidencia) {
 		this.idIncidencia = idIncidencia;
 	}
 	public int getidUser() {
 		return idUser;
 	}
 	public void setidUser(int idUser) {
 		this.idUser = idUser;
 	}	
 	public Date getCreationDate() {
 		return creationDate;
 	}
 	public void setCreationDate(Date creationDate) {
 		this.creationDate = creationDate;
 	}
 	public String getDescription() {
 		return description;
 	}
 	public void setDescription(String description) {
 		this.description = description;
 	}
 	public String toString(){
		String cadena = new String(description);
 		return cadena;
 	}

}
