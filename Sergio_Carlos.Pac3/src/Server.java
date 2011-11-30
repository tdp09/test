 import java.io.IOException;
 import java.net.MalformedURLException;
 import java.rmi.Naming;
 import java.rmi.RemoteException;

 
 public class Server {
 	 private String message = new String();
	 public Server() {
	 }
	 
	 public void startServer() throws IOException {  
		 try {
	 			GestorBBDDInterface objetoRemoto = new GestorBBDDImpl();
	 			Naming.rebind("//localhost/GestorBBDD", objetoRemoto );	
	 		} catch (RemoteException e) {		
				//No se ha podido conectar con RMI
	 			message = TDSLanguageUtils.getMessage("errorRmi"); 			
	 			Errors.main(message);
	 			//e.printStackTrace();
	 		} catch (MalformedURLException e) {	
				//La URL es incorrecta
	 			message = TDSLanguageUtils.getMessage("errorUrl"); 			
	 			Errors.main(message);
	 			//e.printStackTrace();
	 		} catch (Exception e){		
				//Error inesperado
	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
	 			Errors.main(message);
	 			//e.printStackTrace();
	 		}    
       }  
       
	 public void stopServer() throws Exception {  
    	 try{
    		 Naming.unbind("//localhost/GestorBBDD");
    	 } catch (RemoteException e) {		
				//No se ha podido conectar con RMI
	 			message = TDSLanguageUtils.getMessage("errorRmi"); 			
	 			Errors.main(message);
	 			//e.printStackTrace();
	 		} catch (MalformedURLException e) {	
				//La URL es incorrecta
	 			message = TDSLanguageUtils.getMessage("errorUrl"); 			
	 			Errors.main(message);
	 			e.printStackTrace();
	 		} catch (Exception e){		
				//Error inesperado
	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
	 			Errors.main(message);
	 			e.printStackTrace();
	 		} 
		 
       }   
 }