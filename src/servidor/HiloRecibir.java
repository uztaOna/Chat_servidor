package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class HiloRecibir extends Thread{
    private DefaultListModel<String> charla; //Lista en la que se guarda toda la charla
    private Socket socket; //Socket al que está conectado el cliente
    private DataInputStream entrada; // Para lectura de datos en el socket
    private DataOutputStream salida; // Para escritura de datos en el socket
    public JTextArea textArea;
    public JTextField texto;

    public HiloRecibir(DataInputStream entrada,DataOutputStream salida,JTextArea textArea,JTextField texto){
        this.charla = charla;
        this.socket = socket;
        
        try{
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
//            charla.addListDataListener((ListDataListener) this);
        } catch (IOException ex){
            System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
        }
    }
    
	@Override
	public void run() {
		String mensajeRecibido;
		try{
            while (true){
            	mensajeRecibido = entrada.readUTF();
                synchronized (charla){
                    charla.addElement(mensajeRecibido);
                    System.out.println(mensajeRecibido);
                }
            }
        } catch (IOException ex2){
        	System.out.println("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
        }
	}

	public void intervalAdded(ListDataEvent e) {
		String texto = (String) charla.getElementAt(e.getIndex0()); //Obtener el texto del chat
        try{
            salida.writeUTF(texto); //Mandarlo por el socket
        } catch (IOException ex){
        	System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
        }
	}

	public void intervalRemoved(ListDataEvent e) {}
	public void contentsChanged(ListDataEvent e) {}
}
