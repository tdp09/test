package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Servidor.ServerObjInterface;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import java.awt.CardLayout;

public class ApplicationMainWindow implements ActionListener {

	private JFrame frame;
	private ServerObjInterface RI;
 	private final String url = new String("rmi://localhost/eIncidencies");
 	private boolean isConnected = false; 
 	private JMenu mnGestor = null; 
 	private JPanel currentPanel; 
 	private String lang; 
 	private String country; 
 	ResourceBundle rb;
 	JMenuItem mntmConnectar; 
 	JMenuItem mntmAlta;
 	JMenuItem mntmApunt;
 	JMenuItem mntmConsulta;
 	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationMainWindow window = new ApplicationMainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationMainWindow() {
		getLang();
		int isLoaded =  loadLangFields(); 
		if (isLoaded != -1) initialize();
		// this.isConnected = ConnectToServer(url); 
	}

	private void getLang() {
		Properties properties = new Properties();
		try {
			InputStream in = getClass().getResourceAsStream("configuration.properties");
		    properties.load(in);
		    in.close(); 
		} catch (IOException e) {
			System.out.println("Can't open properties file"); 
			e.printStackTrace(); 
		}
		this.lang = properties.getProperty("lang");
		this.country = properties.getProperty("country");
	}
	private int loadLangFields() {
		Locale aLocale = new Locale(this.lang,this.country);
			try {
				this.rb = ResourceBundle.getBundle("Client.message",aLocale); 
			} catch (MissingResourceException e) {
				e.printStackTrace(); 
				return -1; 
			}
			return 1; 
		// String msg = rb.getString("frm.titol"); 
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(rb.getString("frm.titol"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnInici = new JMenu(this.rb.getString("menu.inici"));
		menuBar.add(mnInici);
		
		mntmConnectar = new JMenuItem(this.rb.getString("menu.connectar"));
		mntmConnectar.addActionListener(this); 
		mnInici.add(mntmConnectar);
		
		mnGestor = new JMenu(this.rb.getString("menu.gestor.titol"));
		menuBar.add(mnGestor);
		
		mntmAlta = new JMenuItem(this.rb.getString("menu.gestor.alta"));
		mntmAlta.addActionListener(this); 
		mnGestor.add(mntmAlta);
		
		mntmApunt = new JMenuItem(this.rb.getString("menu.gestor.apunt"));
		mntmApunt.addActionListener(this);
		mnGestor.add(mntmApunt);
		
		mntmConsulta = new JMenuItem(this.rb.getString("menu.gestor.historic"));
		mntmConsulta.addActionListener(this);
		mnGestor.add(mntmConsulta);
		mnGestor.setEnabled(false);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
	}
	
	protected boolean ConnectToServer() {
		boolean isConnected = false; 
		try { 
			this.RI = (ServerObjInterface) Naming.lookup(this.url); 
			if (this.RI.SayHello() == 1){ 
				isConnected = true;
			} else {
				isConnected = false; 
			}
		} catch (NotBoundException e) {
			System.out.println("Client Excepció: NotBoundException"); 
			e.printStackTrace(); 
			System.exit(0); 
		} catch(RemoteException e) {
			System.out.println("Client Excepció: RemoteException ");
			e.printStackTrace();
		} catch(MalformedURLException e) {
			System.out.println("Client Excepció: MalformedURL"); 
			e.printStackTrace(); 
		} catch (Exception  e){
			System.out.println("Client Excepció: Exception"); 
			e.printStackTrace(); 
		}
		return isConnected; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object botoPolzat = e.getSource(); 
		 
		
		if (botoPolzat == this.mntmConnectar) {
			this.isConnected  = ConnectToServer(); 
			if (this.isConnected)	mnGestor.setEnabled(true);  
			this.frame.setTitle("eIncidencies Client - Connectat"); 
		} else if (botoPolzat == this.mntmAlta) {
			frame.getContentPane().removeAll();
			this.currentPanel = new AltaIncidencia(this.RI, this.rb); 
			frame.getContentPane().add(currentPanel,BorderLayout.CENTER);
			frame.setBounds(100, 100, 450, 350);
			frame.invalidate(); 
			frame.validate(); 
		} else if (botoPolzat == this.mntmApunt) {
			frame.getContentPane().removeAll();
			this.currentPanel = new ResolucioIncidencia(this.RI,this.rb);
			frame.getContentPane().add(currentPanel, BorderLayout.CENTER);
			frame.setBounds(100, 100, 450, 350);
			frame.invalidate(); 
			frame.validate(); 
		} else if (botoPolzat == this.mntmConsulta) {
			frame.getContentPane().removeAll();
			this.currentPanel = new Historic(this.RI, this.rb); 
			frame.getContentPane().add(currentPanel,BorderLayout.CENTER); 
			frame.setBounds(100, 100, 450, 350);
			frame.invalidate(); 
			frame.validate(); 
		}
	}
}
