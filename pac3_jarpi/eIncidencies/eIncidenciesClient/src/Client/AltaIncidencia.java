package Client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Servidor.Area;
import Servidor.Request;
import Servidor.ServerObjInterface;
import Servidor.ShowDialogBox;
import Servidor.User;

public class AltaIncidencia extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5740753019679701386L;

	JComboBox cmbUsers; 
	JComboBox cmbAula; 
	JButton btnGravar; 
	JTextArea txtIncidencia; 
	ServerObjInterface objServidor; 
	HashMap<String,Area> hashArea = new HashMap<String,Area>();
	HashMap<String,User> hashUser = new HashMap<String,User>(); 
	ResourceBundle rb; 
	/**
	 * Create the panel.
	 */
	public AltaIncidencia(ServerObjInterface objServidor, ResourceBundle rb) {
		
		this.objServidor = objServidor; 
		this.rb = rb; 
		setBounds(100, 100, 450, 276);
		this.setLayout(null); 
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, this.rb.getString("alta.titol"), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 442, 266);
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setLayout(null);
		this.add(panel);
		
		JLabel lblEspai = new JLabel(this.rb.getString("alta.espai"));
		lblEspai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEspai.setBounds(232, 25, 38, 19);
		panel.add(lblEspai);
		
		JLabel lblUsuari = new JLabel(this.rb.getString("alta.usuari"));
		lblUsuari.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuari.setBounds(220, 70, 50, 19);
		panel.add(lblUsuari);
		
		this.txtIncidencia = new JTextArea();
		this.txtIncidencia.setBounds(10, 133, 422, 88);
		panel.add(this.txtIncidencia);
		
		this.btnGravar = new JButton(this.rb.getString("alta.boto"));
		this.btnGravar.setBounds(20, 232, 400, 23);
		this.btnGravar.addActionListener(this); 
		panel.add(btnGravar);
		
		this.cmbAula = new JComboBox();
		this.cmbAula.setBounds(306, 25, 126, 22);
		panel.add(this.cmbAula);
		
		this.cmbUsers = new JComboBox();
		this.cmbUsers.setBounds(306, 72, 126, 19);
		panel.add(this.cmbUsers);
		
		JLabel lblIncidncia = new JLabel(this.rb.getString("alta.incidencia"));
		lblIncidncia.setBounds(10, 108, 80, 14);
		panel.add(lblIncidncia);
		
		try {
			this.loadAreas(objServidor.getAreas());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return; 
		}
		try {
			this.loadUsers(objServidor.getUsers());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return; 
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == this.btnGravar) {
			// Save request
			String note = this.txtIncidencia.getText();
			if (note.equals((""))) {
				new ShowDialogBox(this.rb.getString("alta.missatgeBuit"));
				return; 
			} 
			
			int requestId = 0;
			try {
				requestId = objServidor.getNextRequestIdValue();
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				System.out.println("Error executant Request Next Value"); 
				e2.printStackTrace();
				return; 
			}
			int userId = this.hashUser.get(this.cmbUsers.getSelectedItem().toString()).UserId();
			int areaId = this.hashArea.get(this.cmbAula.getSelectedItem().toString()).areaId(); 
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			String date = sdf.format(c.getTime());
			Date today;
			try {
				today = sdf.parse(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error parsing date"); 
				e1.printStackTrace();
				return; 
			} 
			Request r = new Request(requestId,userId, areaId,today, note.toString(),"N");
			boolean result = false;
			try {
				result = objServidor.saveRequest(r);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			if (result) {
				new ShowDialogBox(this.rb.getString("alta.inserit")); 
			}else {
				new ShowDialogBox(this.rb.getString("alta.Noinserit")); 
			}
			this.txtIncidencia.setText(""); 
		}
	}
	
	private void loadAreas(ArrayList<Area> arrayAreas) {
		try {
			Iterator<Area> it =  arrayAreas.iterator();
			while (it.hasNext()) {
				Area a = it.next();
				this.cmbAula.addItem(a);  
				this.hashArea.put(a.toString(), a); 
			}
		} catch (Exception arrEx) {
			System.out.println("Can't iterate through arrayAreas");
			return; 
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
			System.out.println("Can't iterate through arrayUsers");
			return; 
		}
	}
}
