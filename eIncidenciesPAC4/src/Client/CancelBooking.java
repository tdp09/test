package Client;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import Servidor.User;

public class CancelBooking extends JPanel {

	/**
	 * Create the panel.
	 */
	public CancelBooking(Booking b, User u) {
		setLayout(null);
		
		JLabel lblPleaseTypeYour = new JLabel("Please type your reason to cancel this booking:");
		lblPleaseTypeYour.setBounds(6, 17, 414, 16);
		add(lblPleaseTypeYour);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(16, 45, 288, 187);
		add(textArea);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(6, 265, 117, 29);
		add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(187, 265, 117, 29);
		add(btnCancel);

	}
	
	public void actionPerformed(ActionEvent e) {
		Object botoPolzat = e.getSource(); 
		// if botoPolzat... 
		
	}
	
}
