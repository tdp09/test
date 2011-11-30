import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**TŽcnicas de Desarrollo de Software
 * Universitat Oberta de Catalunya (UOC) 
 * @author Joan Esteve Riasol    
 */
public class TDSLanguageUtils  {
   	private static ResourceBundle resourceBundle;
	private static String currentBaseName;
	private static Locale currentLocale;

	/**Configura el idioma por defecto en el gestor de idiomas TDSLanguageUtils. 
	 * @param  baseName  direcci—n donde se encuentra el fichero de idiomas
	 * @throws NullPointerException si baseName es nulo
	 * @return un booleano indicando si la operaci—n ha ido bien 
	 */	
	public static synchronized boolean setDefaultLanguage(String baseName) {
		try {
			currentBaseName=baseName;
			Locale locale = Locale.getDefault();
			resourceBundle = ResourceBundle.getBundle(baseName,locale);
			return true;
		}catch(MissingResourceException e){
			e.printStackTrace();
			return false;
		}
	}

/**Configura el idioma indicado por el usuario en el gestor TDSLanguageUtils 
	 * @param baseName direcci—n donde se encuentra el fichero de idiomas
	 * @param locale idioma que se quiere indicar 
	 * @throws NullPointerException si baseName o locale son nulos
	 * @return un booleano indicando si la operaci—n ha ido bien	  
	 */	
	public static synchronized boolean setLanguage(String baseName, Locale locale) {
		try {
			currentBaseName=baseName;
			currentLocale=locale;
			resourceBundle = ResourceBundle.getBundle(baseName,locale);
			return true;
		}catch(MissingResourceException e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**Obtiene el texto del fichero de idiomas segun la etiqueta solicitada	
	 * @param key etiqueta del fichero de idiomas
	 * @param locale idioma que se quiere indicar 
	 * @throws NullPointerException si el gestor no ha sido aœn configurado
	 * @return un String con la traducci—n solicitada  
	 */
	public static String getMessage(String key)  {		
		try {
			return resourceBundle.getString(key);
		}catch(MissingResourceException e) {
			return new String();
		}		
	}
	
	public static String getCurrentBaseName() {
		return currentBaseName;
	}	
	public static Locale getCurrentLocale() {
		return currentLocale;
	}
}
