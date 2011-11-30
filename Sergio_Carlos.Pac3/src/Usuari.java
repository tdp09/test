
 import java.io.Serializable;
 import java.sql.Date;

 public class Usuari implements Serializable {
 	private static final long serialVersionUID = 1L;
 	private int id;
 	private String name;
 	private Date creationDate;
	
 	public Usuari(){
 		super();
 	}
 	
 	public Usuari(int id, String name, Date creationDate){
 		super();
 		this.id=id;
 		this.name=name;
 		this.creationDate=creationDate;		
 	}
 	
 	public int getid() {
 		return id;
 	}
 	public void setid(int id) {
 		this.id = id;
 	}
 	public String getName() {
 		return name;
 	}
 	public void setName(String name) {
 		this.name = name;
 	}
 	public Date getCreationDate() {
 		return creationDate;
 	}
 	public void setCreationDate(Date creationDate) {
 		this.creationDate = creationDate;
 	}
 	
	public String toString(){
 		return name;
 	}

}
