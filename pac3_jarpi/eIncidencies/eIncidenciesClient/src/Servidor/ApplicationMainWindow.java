package Servidor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApplicationMainWindow {

	private JFrame frame;
	private RemoteServer rs = null; 
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 110);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel lblNewLabel = new JLabel("Pendent d'iniciar el servidor RMI:  Gestor d'incid\u00E8ncies");
		lblNewLabel.setBounds(7, 7, 428, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnIniciarServidor = new JButton("Iniciar Servidor");
		btnIniciarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rs == null){ // Si l'instància és nula, llavors instanciem la classe servidor i l'engeguem 
					rs = new RemoteServer();  
					if (rs.isInitialized()) { // Dins de la classe hi ha una variable que retorna si està inicialitzat el servidor o no
						lblNewLabel.setText("Servidor iniciat correctament."); 
						lblNewLabel.setForeground(Color.blue); 
						frame.invalidate(); 
					}else { // Si l'instància no és nula, pero no està connectat, llavors reinicialitzem la variable de l'instància a null
						rs = null; 
					}
				}
				else {
					new ShowDialogBox("El servidor ja està iniciat!"); 
				}
			}
		});
		btnIniciarServidor.setBounds(10, 40, 202, 31);
		frame.getContentPane().add(btnIniciarServidor);
		
		JButton btnAturarServidor = new JButton("Aturar Servidor");
		btnAturarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rs != null){
					rs.stopRemoteServer(); 
				} else {
					new ShowDialogBox("Servidor no iniciat"); 
				}
				
			}
		});
		btnAturarServidor.setBounds(218, 40, 195, 31);
		frame.getContentPane().add(btnAturarServidor);
	}
}
