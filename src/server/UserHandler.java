package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import model.Message;
import model.User;

/**
 * Behandelt eine eingehende TCP-Verbindung.
 * 
 * Kann mit anonymem User sein. Wird der Befehl zum Anmelden gegeben, so wird 
 * der User geprueft, ob er bereits existiert und gegebenfalls angelegt.
 * UserHandler muss sich beim Server registrieren.
 * 
 * @author Daniel Reichmann
 * @version 10-12-2013
 *
 */
public class UserHandler implements Runnable{

	private String host;
	private int tcpPort;
	private Server server;
	private User user; 
	private Socket client; //Socket-Verbindung mit Client
	private boolean active = true;
	
	private Thread executor; //Fuerht die Aktionen durch.
	/**
	 * 
	 */
	public UserHandler(Socket c,Server s){
		client = c;
		server = s;
		tcpPort = c.getPort();
		host = c.getInetAddress().toString();
		executor = new Thread(this);
		executor.start();
	}
	@Override
	public void run() {
		
		System.out.println("new input");
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream( client.getInputStream());
		
			System.out.println(in.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Decorator
		
		Message m;
		while(active){
			Object o = null;
			try {
				
				o = in.readObject();
				System.out.println("Get objekt");
				m = (Message) o;
				System.out.println(m.toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generatedcatch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(o instanceof Message){
				m = (Message) o;
				System.out.println("Sucessfully cast");
				System.out.println(m.getName());
			}
		}
		try {
			client.close();
		} catch (IOException e) {
			System.out.println("Could not close client");
		}
	}
	
}
