import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;


public class Errors{

	private JFrame frmConnexiRmi;

	private JButton btnNewButton = new JButton();
	private JLabel lblNewLabel = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(final String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String m = new String(args);
					Errors window = new Errors(m);
					window.frmConnexiRmi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Errors (String message){
		this.lblNewLabel.setText(message);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		TDSLanguageUtils.setDefaultLanguage("i18n/messages");		
		
		frmConnexiRmi = new JFrame();
		frmConnexiRmi.setTitle(TDSLanguageUtils.getMessage("titleError"));
		frmConnexiRmi.setBounds(100, 100, 450, 190);
		frmConnexiRmi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnexiRmi.getContentPane().setLayout(null);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmConnexiRmi.dispose();
				frmConnexiRmi.setEnabled(false);
			}
		});
		
		btnNewButton.setText(TDSLanguageUtils.getMessage("accept"));
		btnNewButton.setBounds(188, 118, 89, 23);
		frmConnexiRmi.getContentPane().add(btnNewButton);
		
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(24, 25, 406, 81);
		frmConnexiRmi.getContentPane().add(lblNewLabel);
	}
}
