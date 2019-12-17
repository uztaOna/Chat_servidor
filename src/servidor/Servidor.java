package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends Thread{
	
	static final int Puerto=5000;
	static final int MAXIMO_CONEXIONES=5;
	public ArrayList<ObjectOutputStream> lista;
    public JTextField texto;
    public JTextArea textArea;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private ServerSocket servidor;
    private Socket socket;
	
	public Servidor(JTextArea textArea,JTextField texto){
		this.texto=texto;
		this.textArea=textArea;
		
//		run();
	}
		
	
	public void run() {
		int numCli = 0;
		try {
			servidor = new ServerSocket(Puerto, MAXIMO_CONEXIONES);
			System.out.println("Escucho al puerto: " + Puerto + "\n"
					+ "Número de conexiones actuales: " + numCli);
			
			while(true) {
				textArea.setText("Servidor a la espera de conexiones...");
				System.out.println("Servidor a la espera de conexiones...");
				socket = servidor.accept();
				textArea.setText("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");
				System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");
				
//				texto.setText("");
//				lista=new ArrayList<ObjectOutputStream>(lista);
				
				HiloRecibir hilo = new HiloRecibir(entrada, salida, textArea, texto);
				hilo.start();
			}
		}
		catch (IOException ex){
			System.out.println("Error: " + ex.getMessage());
		}finally {
			desconectar();
		}
	}
	
	public void desconectar() {
		try {
			socket.close();
			servidor.close();
		} catch(IOException ex) {
			System.out.println("Error al cerrarse el servidor: " + ex.getMessage());
		}
	}
}
