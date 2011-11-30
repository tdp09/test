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
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Historic {

	private JFrame frmEaules;
	private JComboBox comboBox = new JComboBox();
	private JComboBox comboBox_2 = new JComboBox();
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane();
	
	private static String message = new String();
	
	private GestorBBDDInterface remoto;
 	private final String url = new String("rmi://localhost/GestorBBDD");

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historic window = new Historic();
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
	public Historic() {
		try{
			remoto=(GestorBBDDInterface)Naming.lookup(url);
			initialize();
 					
 		}catch (NotBoundException e) {
 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 			Errors.main(message);
 		}catch (RemoteException e) {
 			message = TDSLanguageUtils.getMessage("errorRmi"); 			
 			Errors.main(message);
 		}catch (MalformedURLException e) {
 			message = TDSLanguageUtils.getMessage("errorUrl"); 			
 			Errors.main(message);
 		}catch (Exception e){
 			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 			Errors.main(message);
 		}
	}
	
	public javax.swing.ComboBoxModel getDataCombo2(int i) throws RemoteException {
	
		Vector<String> options = new Vector<String>();
		options.addElement(TDSLanguageUtils.getMessage("chooseOption"));
		if (i == 1){	
			List<Incidencia> lista = remoto.getListaIncidenciesByStatus("Y");
			Iterator<Incidencia> iter = lista.iterator();			
			while (iter.hasNext())
				options.addElement(iter.next().toString());
			
			
		} else if (i == 2) {
			List<Incidencia> lista = remoto.getListaIncidenciesByStatus("N");
			Iterator<Incidencia> iter = lista.iterator();
			while (iter.hasNext())
				options.addElement(iter.next().toString());
		}				
		javax.swing.ComboBoxModel elements = new javax.swing.DefaultComboBoxModel(options);
		return elements;
	}
	
	public void actualitzaTextArea(int i) throws RemoteException{		
		Incidencia incidencia = remoto.getIncidenciaById(i);
		List<Apunt> lista = remoto.getListaApunts(i);
		Iterator<Apunt> iter = lista.iterator();
		Apunt ap = new Apunt();
		String text = new String("");
		text = text + incidencia.getCreationDate().toString() + "\n";
		text = text + incidencia.getDescription() + "\n\n";
		while (iter.hasNext()){ 
			ap = iter.next();
			text = text + ap.getCreationDate().toString() + "\n";
			text = text + ap.getDescription().toString() + "\n\n";
		}
		textArea.setText(text);
	}
	
	public void actualitzaCombo2() throws RemoteException{
		comboBox_2.setModel(getDataCombo2(comboBox.getSelectedIndex()));
	}
	

	/**
	 * Initialize the contents of the frame.
	 * @throws RemoteException 
	 */
	private void initialize() throws RemoteException {
		
		TDSLanguageUtils.setDefaultLanguage("i18n/messages_ca_ES");		
		
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
			public void actionPerformed(ActionEvent e) {
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
		
		comboBox.setBounds(445, 58, 329, 34);
		frmEaules.getContentPane().add(comboBox);
		comboBox.addItem(TDSLanguageUtils.getMessage("chooseOption"));
		comboBox.addItem(TDSLanguageUtils.getMessage("solved"));
		comboBox.addItem(TDSLanguageUtils.getMessage("inProgress"));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					actualitzaCombo2();
				} catch (RemoteException e1) {
					message = TDSLanguageUtils.getMessage("errorRmi"); 			
		 			Errors.main(message);
				}
			}
		});

		comboBox_2.setBounds(445, 103, 329, 34);
		frmEaules.getContentPane().add(comboBox_2);
		comboBox_2.setModel(getDataCombo2(0));
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					actualitzaTextArea(comboBox_2.getSelectedIndex());	
				} catch (RemoteException e1) {
					message = TDSLanguageUtils.getMessage("errorRmi"); 			
		 			Errors.main(message);
				}
			}
		});

		
		JLabel lblAula = new JLabel(TDSLanguageUtils.getMessage("incidenceType"));
		lblAula.setBounds(300, 58, 135, 34);
		lblAula.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAula.setHorizontalAlignment(SwingConstants.RIGHT);
		frmEaules.getContentPane().add(lblAula);
		
		JLabel lblUsuari = new JLabel(TDSLanguageUtils.getMessage("incidence"));
		lblUsuari.setBounds(300, 103, 135, 34);
		lblUsuari.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuari.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmEaules.getContentPane().add(lblUsuari);
		
		JLabel lblNewLabel = new JLabel(TDSLanguageUtils.getMessage("consultingHistorical"));
		lblNewLabel.setBounds(10, 11, 321, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmEaules.getContentPane().add(lblNewLabel);
		
		
		scrollPane.setBounds(10, 153, 764, 377);
		frmEaules.getContentPane().add(scrollPane);
		
		
		textArea.setEditable(false);
		textArea.setEnabled(false);
		scrollPane.setViewportView(textArea);
		textArea.setColumns(10);
	}
	
}
