package Client;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Bookings extends JPanel {

	private boolean cmbIsLoading = false; 
	
	/**
	 * Create the panel.
	 */
	public Bookings() {
		setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 22, 166, 27);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(0, 61, 166, 27);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(178, 40, 166, 27);
		add(comboBox_2);
		
		JLabel lblCercaPerReserva = new JLabel("Cerca per reserva");
		lblCercaPerReserva.setBounds(6, 6, 161, 16);
		add(lblCercaPerReserva);
		
		JLabel lblCercaPerUsuari = new JLabel("Cerca per usuari");
		lblCercaPerUsuari.setBounds(10, 48, 161, 16);
		add(lblCercaPerUsuari);
		
		JLabel lblCercaPerEspai = new JLabel("Cerca per espai");
		lblCercaPerEspai.setBounds(188, 22, 161, 16);
		add(lblCercaPerEspai);
		
		JList list = new JList();
		list.setBounds(6, 100, 438, 194);
		add(list);

	}
	
	private void loadUsers() {
		this.cmbIsLoading = true;
		// ArrayList<User> arrUsers = objServidor.users.getAllUsers();
		// Loop through array and load to combo 
	}
	
	private void loadSpaces() {
		this.cmbIsLoading = true; 
		// ArratList<Space> arrSpaces = objServidor.spaces.getAllSpaces(); 
		// Loop through array and load to combo 
	}
	
	private void loadBookings() {
		this.cmbIsLoading = true;
		// ArrayList<Booking> arrBook = objServidor.bookings.gettAllBookings(); 
		// Loop through array and load combo 
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!this.cmbIsLoading) {
			Object botoPolzat = e.getSource(); 
			// if botoPolzat... 
		}
			
	}
}
