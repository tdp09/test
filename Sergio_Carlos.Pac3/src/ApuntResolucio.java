import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ApuntResolucio {

	private JFrame frmEaules;
	private GestorBBDDInterface remoto;
	private static String message = new String();
 	private final String url = new String("rmi://localhost/GestorBBDD");

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApuntResolucio window = new ApuntResolucio();
					window.frmEaules.setVisible(true);
				} catch (Exception e) {
					message = TDSLanguageUtils.getMessage("errorUnespected"); 			
		 			Errors.main(message);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApuntResolucio() {
		try {
			remoto=(GestorBBDDInterface)Naming.lookup(url);
			initialize();
		} catch (MalformedURLException e) {
			message = TDSLanguageUtils.getMessage("errorUrl"); 			
 			Errors.main(message);
		} catch (RemoteException e) {
			message = TDSLanguageUtils.getMessage("errorRmi"); 			
 			Errors.main(message);
		} catch (NotBoundException e) {
			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 			Errors.main(message);
		}
	}

	public javax.swing.ComboBoxModel comboIncidencies() throws RemoteException {
		  Vector<String> options1 = new Vector<String>();  
		  options1.addElement(TDSLanguageUtils.getMessage("chooseOption"));
		  List<Incidencia> lista1 = remoto.getListaIncidenciesByStatus("N");
		  Iterator<Incidencia> iter1 = lista1.iterator();   
		  while (iter1.hasNext()) 
			  options1.addElement(iter1.next().toString());   
		  	  javax.swing.ComboBoxModel elements1 = new javax.swing.DefaultComboBoxModel(options1);
		  return elements1;
	}
	
	public javax.swing.ComboBoxModel comboUsuaris() throws RemoteException {
		  Vector<String> options2 = new Vector<String>();  
		  options2.addElement(TDSLanguageUtils.getMessage("chooseOption"));
		  List<Usuari> lista2 = remoto.getListaUsuaris();
		  Iterator<Usuari> iter2 = lista2.iterator();   
		  while (iter2.hasNext()) 
			  options2.addElement(iter2.next().toString());    
		  	  javax.swing.ComboBoxModel elements2 = new javax.swing.DefaultComboBoxModel(options2);
		  return elements2;
	}
	
	private class ControladorBoton implements ActionListener 
	{ 
		// controlar evento de bot—n 
		public void actionPerformed( ActionEvent evento ) 
		{ 	
			int requestId = 0;
			try{
				if (txtpnFdsf.getText().length() > 300){
					throw new Excepcio("error300characters");
				}
				if (comboBox.getSelectedIndex()<=0){
					throw new Excepcio("errorselectIncidencia");
				}
				if (comboBox_2.getSelectedIndex()<=0){
					throw new Excepcio("errorselectUsuari");
				}
				if (txtpnFdsf.getText().length() <= 0){
					throw new Excepcio("errorinsertDescription");
				}
				if (comboBox_1.getSelectedIndex()<=0){
					throw new Excepcio("errorselectResolucio");
				}
				Apunt ap = new Apunt ();
				requestId = remoto.getNextValApunt(); 
				ap.setid(requestId);
				ap.setidIncidencia(comboBox.getSelectedIndex());
				ap.setidUser(comboBox_2.getSelectedIndex());
				ap.setCreationDate(new java.sql.Date(new Date().getTime()));
				ap.setDescription(txtpnFdsf.getText());
				remoto.insertaApunt(ap);
				if (comboBox_1.getSelectedIndex() == 1){
					remoto.actualitzaIncidenciaResoltaById(ap.getidIncidencia());
				}
			}catch(Excepcio e){
			}catch(Exception e){
	 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
	 			Errors.main(message);
 	 		}	
		} 
  	} // fin de la clase interna privada ControladorBoton 
	
	/**
	 * Initialize the contents of the frame.
	 * @throws RemoteException 
	 */
	private void initialize() throws RemoteException {
		
		//Carreguem el arxiu d'idioma
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");
		
		frmEaules = new JFrame();
		frmEaules.setTitle(TDSLanguageUtils.getMessage("titleWindow"));
		frmEaules.setBounds(100, 100, 800, 600);
		frmEaules.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmEaules.setJMenuBar(menuBar);
		
		JMenu mnInici = new JMenu(TDSLanguageUtils.getMessage("home"));
		menuBar.add(mnInici);
		
		JMenuItem mntmSortir = new JMenuItem(TDSLanguageUtils.getMessage("exit"));
		mntmSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnInici.add(mntmSortir);
		
		JMenu mnNewMenu = new JMenu(TDSLanguageUtils.getMessage("incidenceManager"));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAltaNovaIncidncia = new JMenuItem(TDSLanguageUtils.getMessage("newIncidence"));
		mntmAltaNovaIncidncia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaNova.main();
				frmEaules.dispose();
				frmEaules.setEnabled(false);
			}
		});
		mnNewMenu.add(mntmAltaNovaIncidncia);
		
		JMenuItem mntmApuntOResoluci = new JMenuItem(TDSLanguageUtils.getMessage("noteIncidence"));
		mntmApuntOResoluci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApuntResolucio.main();
				frmEaules.dispose();
				frmEaules.setEnabled(false);
			}
		});
		mnNewMenu.add(mntmApuntOResoluci);
		
		JMenuItem mntmConsultaDhistric = new JMenuItem(TDSLanguageUtils.getMessage("consultingHistorical"));
		mntmConsultaDhistric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Historic.main();
				frmEaules.dispose();
				frmEaules.setEnabled(false);	
			}
		});
		mnNewMenu.add(mntmConsultaDhistric);
		frmEaules.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton(TDSLanguageUtils.getMessage("saveNote"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 488, 764, 42);
		frmEaules.getContentPane().add(btnNewButton);
		
		ControladorBoton controlador = new ControladorBoton(); 
		btnNewButton.addActionListener( controlador );
		
		txtpnFdsf.setBounds(10, 198, 764, 279);
		frmEaules.getContentPane().add(txtpnFdsf);
		
		comboBox.setBounds(445, 58, 329, 34);
		comboBox.setModel(comboIncidencies());
		frmEaules.getContentPane().add(comboBox);
		
		comboBox_2.setBounds(445, 103, 329, 34);
		comboBox_2.setModel(comboUsuaris());
		frmEaules.getContentPane().add(comboBox_2);
		
		JLabel lblAula = new JLabel(TDSLanguageUtils.getMessage("incidence"));
		lblAula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAula.setBounds(284, 58, 151, 34);
		frmEaules.getContentPane().add(lblAula);
		
		JLabel lblUsuari = new JLabel(TDSLanguageUtils.getMessage("user"));
		lblUsuari.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuari.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuari.setBounds(284, 103, 151, 34);
		frmEaules.getContentPane().add(lblUsuari);
		
		JLabel lblNewLabel = new JLabel(TDSLanguageUtils.getMessage("noteIncidence"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 321, 20);
		frmEaules.getContentPane().add(lblNewLabel);
		
		comboBox_1.setBounds(445, 148, 329, 34);
		frmEaules.getContentPane().add(comboBox_1);
		comboBox_1.addItem(TDSLanguageUtils.getMessage("chooseOption"));
		comboBox_1.addItem(TDSLanguageUtils.getMessage("yes"));
		comboBox_1.addItem(TDSLanguageUtils.getMessage("no"));
		
		
		JLabel lblEsResolLincidncia = new JLabel(TDSLanguageUtils.getMessage("incidenceSolved"));
		lblEsResolLincidncia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEsResolLincidncia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEsResolLincidncia.setBounds(284, 148, 151, 34);
		frmEaules.getContentPane().add(lblEsResolLincidncia);
	}
	JTextPane txtpnFdsf = new JTextPane();
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	JComboBox comboBox_2 = new JComboBox();
}
