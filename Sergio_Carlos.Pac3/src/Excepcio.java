
public class Excepcio extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = new String();
	
	public static void main(String args[]){
		
	}

	public Excepcio(String msg) {
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
		message = TDSLanguageUtils.getMessage(msg); 			
		Errors.main(message);
	}
	
}
