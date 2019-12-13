package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends Thread{
	
	static final int Puerto=5000;
	static final int MAXIMO_CONEXIONES=3;
	public ArrayList<ObjectOutputStream> lista;
    public JTextField texto;
    public JTextArea textArea;
    private DataInputStream entrada;
    private DataOutputStream salida;
	
	public Servidor(JTextArea textArea,JTextField texto){
		
	}
		
	
	public void run() {
		int numCli = 0;
		try {
			ServerSocket socketServidor = new ServerSocket(Puerto);
			socketServidor.accept();
			System.out.println("Escucho al puerto: " + Puerto + "\n"
					+ "Número de conexiones actuales: " + numCli);
			while(true) {
				Socket cliente = socketServidor.accept();
				System.out.println("Sirvo al cliente "+ numCli);
				
				lista=new ArrayList<ObjectOutputStream>(lista);
				
				
				
//				Thread hilo=new Thread();
//				hilo.start();
//				
//				OutputStream aux = cliente.getOutputStream();
//				DataOutputStream flujo = new DataOutputStream(aux);
//				flujo.writeUTF("Hola cliente " + numCli);
//				cliente.close();
				System.out.println("Demasiados clientes por hoy");
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void desconectar() {
		
	}
}
