package servidor;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends Thread{
	
	static final int Puerto=5000;
	static final int MAXIMO_CONEXIONES=3;
	public ArrayList<ObjectOutputStream> lista;
    public JTextField texto;
    public JTextArea textArea;
	private DefaultListModel charla = new DefaultListModel();
	
	public Servidor(){
		
	}
		
	
	public void run() {
		try {
			ServerSocket socketServidor = new ServerSocket(Puerto);
			System.out.println("Escucho al puerto " + Puerto);
			
			for (int numCli=0; numCli <3 ; numCli++){
				Socket cliente = socketServidor.accept();
				System.out.println("Sirvo al cliente "+ numCli);
				
				Runnable nuevoCliente=new HiloRecibir(charla, cliente);
				Thread hilo=new Thread(nuevoCliente);
				hilo.start();
				
				OutputStream aux = cliente.getOutputStream();
				DataOutputStream flujo = new DataOutputStream(aux);
				flujo.writeUTF("Hola cliente " + numCli);
				cliente.close();
			}
			System.out.println("Demasiados clientes por hoy");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void desconectar() {
		
	}
}
