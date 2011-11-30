package Client;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Servidor.Note;
import Servidor.Request;
import Servidor.ServerObjInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Historic extends JPanel implements ActionListener{
	
	private ServerObjInterface objServidor; 
	private JComboBox cmbTipusIncidencia; 
	private JList lstNotes; 
	private JComboBox cmbIncidencia; 
	private HashMap<String,Request> hashRequests = new HashMap<String,Request>(); 
	private Boolean isComboLoading = false; 
	ResourceBundle rb; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6224300615129383049L;

	/**
	 * Create the panel.
	 */
	public Historic(ServerObjInterface objServidor, ResourceBundle rb) {
		
		this.rb = rb; 
		this.objServidor = objServidor;
		
		setBounds(100, 100, 450, 300);
		setBorder(new TitledBorder(null, this.rb.getString("historic.titol"), TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JLabel lblIncidncia = new JLabel(this.rb.getString("historic.incidencia"));
		lblIncidncia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIncidncia.setBounds(131, 57, 69, 14);
		add(lblIncidncia);
		
		JLabel lblIncidencia = new JLabel(this.rb.getString("historic.tipusIncidencia"));
		lblIncidencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIncidencia.setBounds(83, 32, 135, 20);
		add(lblIncidencia);
		
		this.cmbTipusIncidencia = new JComboBox();
		this.cmbTipusIncidencia.setBounds(228, 29, 212, 20); 
		add(this.cmbTipusIncidencia);
		
		this.cmbIncidencia = new JComboBox();
		this.cmbIncidencia.setBounds(228, 54, 212, 20);
		add(this.cmbIncidencia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 117, 430, 172);
		add(scrollPane);
		
		this.lstNotes = new JList();
		this.lstNotes.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023617430137119564L;
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(this.lstNotes);
		this.loadTipusIncidencies(); 
		this.loadIncidencies(this.cmbTipusIncidencia.getItemAt(0).toString()); 
		this.loadNotesIncidencia(this.hashRequests.get(this.cmbIncidencia.getItemAt(0).toString())); 
	}

	private void loadNotesIncidencia(Request r) {
		ArrayList<Note> arrayNotes = null; 
		DefaultListModel m = new DefaultListModel();
		this.lstNotes.setModel(m);
		try {
			arrayNotes = this.objServidor.getNotesByRequestId(r.requestId()); 
		}catch(RemoteException e) {
			System.out.println("Exception while calling getNotesByRequestId"); 
			e.printStackTrace(); 
			return; 
		}
		try {
			Iterator<Note> it =  arrayNotes.iterator();
			DefaultListModel model = new DefaultListModel(); 
			while (it.hasNext()) {
				Note n = it.next();  
				model.addElement(n.noteDescription().toString());
				this.lstNotes.setModel(model);
				this.hashRequests.put(r.toString(), r); 
			}
		} catch (Exception arrEx) {
			System.out.println("Can't iterate through Notes");
			// arrEx.printStackTrace();  
			// return; 
		}
	}

	private void loadIncidencies(String type) {
		String tipus = ""; 
		this.isComboLoading = true; 
		this.cmbIncidencia.removeAllItems(); 
		if (type.equals("Resolta")) {
			tipus = "Y"; 
			
		}else if (type.equals("Oberta")){
			tipus = "N"; 
		}else {
			tipus = ""; 
		}
		ArrayList<Request> arrayRequest = null; 
		try {
			arrayRequest = this.objServidor.getRequestsByState(tipus);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't call getResquestByState"); 
			e.printStackTrace();
			return; 
		}
		try {
			Iterator<Request> it =  arrayRequest.iterator();
			while (it.hasNext()) {
				Request r = it.next();
				System.out.println(r.toString()); 
				this.cmbIncidencia.addItem(r);  
				this.hashRequests.put(r.toString(), r); 
			}
		} catch (Exception arrEx) {
			System.out.println("Can't iterate through requests");
			return; 
		}
		this.isComboLoading = false; 
		this.cmbIncidencia.addActionListener(this);
	}

	private void loadTipusIncidencies() {
		this.isComboLoading = true; 
		this.cmbTipusIncidencia.addItem("Resolta"); 
		this.cmbTipusIncidencia.addItem("Oberta");
		this.cmbTipusIncidencia.addItem("Totes"); 
		this.cmbTipusIncidencia.addActionListener(this);
		this.isComboLoading = false; 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!this.isComboLoading) {
			if (arg0.getSource() == this.cmbTipusIncidencia) {
				this.loadIncidencies(this.cmbTipusIncidencia.getSelectedItem().toString());
				this.loadNotesIncidencia(this.hashRequests.get(this.cmbIncidencia.getItemAt(0).toString())); 
			}
			else if (arg0.getSource() == this.cmbIncidencia) {
				this.loadNotesIncidencia(this.hashRequests.get(this.cmbIncidencia.getSelectedItem().toString()));
			}
		}
	}
}
