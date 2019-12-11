package servidor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaServidor extends JFrame{
	
	private static final long serialVersionUID = 6289509716153512736L;
	
	private JTextField textField;
	private JTextArea textArea;
	private final JButton btnSalir;
	private JPanel panel;
	private Servidor servidor;
	
	public VentanaServidor() {
		setTitle("Chat Servidor");
		setResizable(false);
		setBounds(100, 100, 450, 350);
		panel=new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 11, 410, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 42, 410, 187);
		getContentPane().add(textArea);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(getDefaultCloseOperation());
			}
		});
		btnSalir.setBounds(170, 240, 100, 20);
		getContentPane().add(btnSalir);
		
		//Iniciar el hilo del servidor
		servidor = new Servidor();
		servidor.start();
		
	}

	public static void main(String[] args) {
		new VentanaServidor().setVisible(true);
	}

}
