import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IniciClient {

	private JFrame frmEaules;
	private JLabel lblNewLabel = new JLabel();
	private JLabel lblIncidnciesResoltes = new JLabel();
	
	private static String message = new String();
	
	private GestorBBDDInterface remoto;
 	private final String url = new String("rmi://localhost/GestorBBDD");
 	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciClient window = new IniciClient();
					window.frmEaules.setVisible(true);					
				} catch (Exception e) {
					message = TDSLanguageUtils.getMessage("errorUnespected"); 			
		 			Errors.main(message);
				}
			}
		});
	}
	
	public void actualitzaLabels() throws RemoteException{		
		int countsolved = remoto.countIncidenciesByStatus("Y");
		int countinprogress = remoto.countIncidenciesByStatus("N");
		String cs = new String(countsolved+"");
		String ci = new String(countinprogress+"");
		lblNewLabel.setText(TDSLanguageUtils.getMessage("incidencesInProgress") + " " + ci);
		lblIncidnciesResoltes.setText(TDSLanguageUtils.getMessage("incidencesSolved") + " " + cs);		
	}

	/**
	 * Create the application.
	 * @throws RemoteException 
	 */
	public IniciClient() throws RemoteException {
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
	/**
	 * Initialize the contents of the frame.
	 * @throws RemoteException 
	 */
	private void initialize() throws RemoteException{
		
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
		
		actualitzaLabels();
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(180, 507, 216, 23);
		frmEaules.getContentPane().add(lblNewLabel);
		
		lblIncidnciesResoltes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIncidnciesResoltes.setBounds(448, 507, 216, 23);
		frmEaules.getContentPane().add(lblIncidnciesResoltes);
	}
}
