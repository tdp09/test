import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;


public class Servidor {
	
	Server server = new Server();
	
	private JFrame frmIniciaturadaServidor;
	private static String message = new String();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor window = new Servidor();
					window.frmIniciaturadaServidor.setVisible(true);
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
	public Servidor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		TDSLanguageUtils.setDefaultLanguage("i18n/messages_ca_ES");
		
		frmIniciaturadaServidor = new JFrame();
		frmIniciaturadaServidor.setTitle(TDSLanguageUtils.getMessage("titleServer"));
		frmIniciaturadaServidor.setBounds(100, 100, 800, 150);
		frmIniciaturadaServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIniciaturadaServidor.getContentPane().setLayout(null);
		
		btnNewButton.setText(TDSLanguageUtils.getMessage("startServer"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 53, 370, 42);
		frmIniciaturadaServidor.getContentPane().add(btnNewButton);	
		
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnNewButtonActionPerformed(evt);
            }
        });
		
		btnAturarServidor.setText(TDSLanguageUtils.getMessage("stopServer"));
		btnAturarServidor.setEnabled(false);
		btnAturarServidor.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAturarServidor.setBounds(390, 53, 370, 42);
		frmIniciaturadaServidor.getContentPane().add(btnAturarServidor);
		
		btnAturarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAturarServidorActionPerformed(evt);
            }
        });
		
	
		lblNewLabel.setText(TDSLanguageUtils.getMessage("labelPendingStartingServer"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 370, 31);
		frmIniciaturadaServidor.getContentPane().add(lblNewLabel);		
		
	}
	private void btnNewButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			server.startServer();
			btnNewButton.setEnabled(false);
			btnAturarServidor.setEnabled(true);
			lblNewLabel.setText(TDSLanguageUtils.getMessage("labelServerStarted"));
		}
		catch(Exception e){
			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 			Errors.main(message);
		}
	}
	private void btnAturarServidorActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			server.stopServer();
			btnAturarServidor.setEnabled(false);
			btnNewButton.setEnabled(true);
			lblNewLabel.setText(TDSLanguageUtils.getMessage("labelPendingStartingServer"));
		}
		catch(Exception e){
			message = TDSLanguageUtils.getMessage("errorUnespected"); 			
 			Errors.main(message);
		}
	}
	private JButton btnNewButton = new JButton();
	private JButton btnAturarServidor = new JButton() ;
	private JLabel lblNewLabel = new JLabel();
}
