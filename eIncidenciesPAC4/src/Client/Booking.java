package Client;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class Booking extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Create the panel.
	 */
	public Booking(Booking b) {
		setLayout(null);
		
		JLabel lblUsuari = new JLabel("Usuari:");
		lblUsuari.setBounds(6, 22, 61, 16);
		add(lblUsuari);
		
		JLabel lblEspai = new JLabel("Espai:");
		lblEspai.setBounds(6, 50, 61, 16);
		add(lblEspai);
		
		JLabel lblInici = new JLabel("Inici:");
		lblInici.setBounds(6, 78, 61, 16);
		add(lblInici);
		
		JLabel lblFi = new JLabel("Fi;");
		lblFi.setBounds(6, 106, 61, 16);
		add(lblFi);
		
		JLabel lblPersones = new JLabel("Persones:");
		lblPersones.setBounds(6, 134, 61, 16);
		add(lblPersones);
		
		JLabel lblEstat = new JLabel("Estat:");
		lblEstat.setBounds(6, 162, 61, 16);
		add(lblEstat);
		
		JLabel lblDataCreaci = new JLabel("Data creaci\u00F3:");
		lblDataCreaci.setBounds(6, 190, 88, 16);
		add(lblDataCreaci);
		
		JLabel lblDataCancelaci = new JLabel("Data cancelaci\u00F3:");
		lblDataCancelaci.setBounds(6, 218, 110, 16);
		add(lblDataCancelaci);
		
		JLabel lblUsuariCancelaci = new JLabel("Usuari cancelaci\u00F3:");
		lblUsuariCancelaci.setBounds(6, 246, 123, 16);
		add(lblUsuariCancelaci);
		
		JLabel lblMotiuCancelaci = new JLabel("Motiu cancelaci\u00F3:");
		lblMotiuCancelaci.setBounds(303, 24, 123, 16);
		add(lblMotiuCancelaci);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(128, 16, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(128, 44, 134, 28);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(128, 72, 134, 28);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(128, 100, 134, 28);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(128, 128, 134, 28);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(128, 156, 134, 28);
		add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(128, 184, 134, 28);
		add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(128, 212, 134, 28);
		add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(128, 240, 134, 28);
		add(textField_8);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(282, 45, 160, 89);
		add(textPane);
		
		JButton btnCancelThisBooking = new JButton("Cancel this booking");
		btnCancelThisBooking.setBounds(282, 213, 160, 29);
		add(btnCancelThisBooking);
		
		JButton btnBackToSearch = new JButton("Back to search menu");
		btnBackToSearch.setBounds(115, 274, 258, 29);
		add(btnBackToSearch);
		
	}
	
	private void loadBookingDetail(Booking b){
		// Access to space and users object needed 
	}
	
	public void actionPerformed(ActionEvent e) {
			Object botoPolzat = e.getSource(); 
			// if botoPolzat... 
			
	}

}
