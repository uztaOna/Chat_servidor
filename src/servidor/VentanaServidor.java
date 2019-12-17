package servidor;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaServidor extends JFrame{
	
	private static final long serialVersionUID = 6289509716153512736L;
	
	private static JTextField textField;
	private static JTextArea textArea;
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
		btnSalir.setBounds(170, 240, 100, 20);
		getContentPane().add(btnSalir);
		
		//Iniciar el hilo del servidor
		servidor = new Servidor(textArea, textField);
		servidor.start();
		
		initListeners();
	}
	
	private void initListeners() {
		btnSalir.addActionListener(new ListenerBotones());
	}

	private class ListenerBotones implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String accion = e.getActionCommand();
			switch (accion) {
			case "Salir":
				//Stop Thread *- Parar los hilos
				System.exit(getDefaultCloseOperation());
				break;
			}
		}
	}

	public static void main(String[] args) {
		new VentanaServidor().setVisible(true);
		new Servidor(textArea, textField);
	}
}
