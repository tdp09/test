package Client;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;

import Servidor.Note;
import Servidor.Request;
import Servidor.ServerObjInterface;
import Servidor.ShowDialogBox;
import Servidor.User;

public class ResolucioIncidencia extends JPanel implements ActionListener {

	JComboBox cmbUsers;
	JComboBox cmbRequests;
	JComboBox cmbResolta; 
	JTextPane txtObservacions; 
	JButton btnGravar; 
	ServerObjInterface objServidor;
	HashMap<String,Request> hashRequest = new HashMap<String,Request>();
	HashMap<String,User> hashUser = new HashMap<String,User>();
	ResourceBundle rb; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5422261323459044342L;

	/**
	 * Create the panel.
	 */
	public ResolucioIncidencia(ServerObjInterface objServidor, ResourceBundle rb) {
		
		this.rb = rb; 
		this.objServidor = objServidor;  
		
		setBounds(100, 100, 450, 300);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, this.rb.getString("apunt.titol"), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 450, 300);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblIncidencia = new JLabel(this.rb.getString("apunt.incidencia"));
		lblIncidencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIncidencia.setBounds(90, 46, 79, 19);
		panel.add(lblIncidencia);
		
		JLabel lblUsuari = new JLabel(this.rb.getString("apunt.usuari"));
		lblUsuari.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuari.setBounds(90, 75, 79, 19);
		panel.add(lblUsuari);
		
		JLabel lblEsResolta = new JLabel(this.rb.getString("apunt.resolta"));
		lblEsResolta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEsResolta.setBounds(90, 103, 152, 19);
		panel.add(lblEsResolta);
		
		this.cmbRequests = new JComboBox();
		this.cmbRequests.setBounds(244, 47, 196, 20);
		panel.add(this.cmbRequests);
		
		this.cmbUsers = new JComboBox();
		this.cmbUsers.setBounds(244, 75, 196, 20);
		panel.add(this.cmbUsers);
		
		this.cmbResolta = new JComboBox();
		this.cmbResolta.setBounds(244, 104, 196, 20);
		panel.add(this.cmbResolta);
		this.cmbResolta.addItem("Si"); 
		this.cmbResolta.addItem("No"); 
		
		this.txtObservacions = new JTextPane();
		this.txtObservacions.setBounds(10, 172, 430, 83);
		panel.add(this.txtObservacions);
		
		JLabel lblObservacions = new JLabel(this.rb.getString("apunt.observacions"));
		lblObservacions.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblObservacions.setBounds(10, 155, 95, 14);
		panel.add(lblObservacions);
		
		this.btnGravar = new JButton(this.rb.getString("apunt.boto"));
		this.btnGravar.setBounds(10, 266, 430, 23);
		this.btnGravar.addActionListener(this); 
		panel.add(this.btnGravar);
		
		try {
			this.loadRequests(this.objServidor.getRequests());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return; 
		} 
		try {
			this.loadUsers(this.objServidor.getUsers());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return; 
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == this.btnGravar) {
			if (this.txtObservacions.getText().equals("")){
				new ShowDialogBox(this.rb.getString("apunt.missatgeNoBuit")); 
				return; 
			}
			
			Request r = this.hashRequest.get(this.cmbRequests.getSelectedItem().toString());
			User u = this.hashUser.get(this.cmbUsers.getSelectedItem().toString()); 
			String isSolved = this.cmbResolta.getSelectedItem().toString();  
			boolean isUpdated = false; 
			if (isSolved.equals("Si")) {
				try {
					isUpdated = this.objServidor.updateSolvedRequest(r);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					System.out.println("Error actualitzant request"); 
					e.printStackTrace();
					return; 
				} 
			}
			int idNote = 0;
			try {
				idNote = this.objServidor.getNextNoteIdValue();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("Error obtenint següent valor Notes"); 
				e.printStackTrace();
				return; 
			}
			Note n = new Note(idNote,r.requestId(),u.UserId(),r.creationDate(),this.txtObservacions.getText());
			boolean b = false;
			try {
				b = this.objServidor.saveNote(n);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (b && isUpdated) {
				new ShowDialogBox(this.rb.getString("apunt.inserit")); 
			}
		}
	}
	
	private void loadUsers(ArrayList<User> arrayUsers) {
		try {
			Iterator<User> it =  arrayUsers.iterator();
			while (it.hasNext()) {
				User a = it.next();
				this.cmbUsers.addItem(a);  
				this.hashUser.put(a.toString(), a); 
			}
		} catch (Exception arrEx) {
			System.out.println("Can't iterate through arrayAreas");
			return; 
		}
	}
	
	private void loadRequests(ArrayList<Request> arrayRequests) {
		try {
			Iterator<Request> it = arrayRequests.iterator(); 
			while (it.hasNext()) {
				Request r = it.next(); 
				this.cmbRequests.addItem(r); 
				this.hashRequest.put(r.toString(),r); 
			}
		} catch (Exception arrEx) {
			System.out.println("Can't iterate through arrayRequests"); 
			return; 
		}
	}
}
