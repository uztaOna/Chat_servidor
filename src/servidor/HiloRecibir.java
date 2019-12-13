package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            charla.addListDataListener((ListDataListener) this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
	@Override
	public void run() {
		try{
            while (true){
//                texto = entrada.readUTF();
//                synchronized (charla){
//                    charla.addElement(texto);
//                    System.out.println(texto);
//                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
	}

	public void intervalAdded(ListDataEvent e) {
		String texto = (String) charla.getElementAt(e.getIndex0()); //Obtener el texto del chat
        try{
            salida.writeUTF(texto); //Mandarlo por el socket
        } catch (Exception excepcion){
            excepcion.printStackTrace();
        }
	}

	public void intervalRemoved(ListDataEvent e) {}
	public void contentsChanged(ListDataEvent e) {}
}
