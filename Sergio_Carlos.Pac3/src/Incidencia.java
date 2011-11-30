
 import java.io.Serializable;
 import java.sql.Date;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;

 public class Incidencia implements Serializable {
 	private static final long serialVersionUID = 1L;
 	private int id;
 	private int idUser;
 	private int idAula;
 	private Date creationDate;
 	private String description;
 	private String isSolved;
 	private final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 	
	
 	public Incidencia(){
 		super();
 	}
 	
 	public Incidencia(int id, int idUser, int idAula, Date creationDate, String description, String isSolved){
 		super();
 		this.id=id;
 		this.idUser=idUser;
 		this.idAula=idAula;
 		this.creationDate=creationDate;	
 		this.description=description;
 		this.isSolved=isSolved;	
 	}
 	
 	public int getid() {
 		return id;
 	}
 	public void setid(int id) {
 		this.id = id;
 	}
 	public int getidUser() {
 		return idUser;
 	}
 	public void setidUser(int idUser) {
 		this.idUser = idUser;
 	}
 	public int getidAula() {
 		return idAula;
 	}
 	public void setidAula(int idAula) {
 		this.idAula = idAula;
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
 	public String getisSolved() {
 		return isSolved;
 	}
 	public void setisSolved(String isSolved) {
 		this.isSolved = isSolved;
 	}
	public String toString(){
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
		return TDSLanguageUtils.getMessage("incidence")+" #"+id+" ("+sdf.format(creationDate)+")";
 	}

}
