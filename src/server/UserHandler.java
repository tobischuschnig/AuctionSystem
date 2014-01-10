package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import model.LoginMessage;
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
	ObjectInputStream in;
	ObjectOutputStream out;
	private Thread executor; //Fuerht die Aktionen durch.
	/**
	 * 
	 */
	public UserHandler(Socket c,Server s){
		client = c;
		server = s;
		tcpPort = c.getPort();
		try {
			in = new ObjectInputStream( client.getInputStream());
			out = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		host = c.getInetAddress().toString();
		executor = new Thread(this);
		executor.start();
	}
	@Override
	public void run() {
		System.out.println("new input");
				
		Message m;
		while(active){
			Object o = null;
			try {
				
				o = in.readObject();
				System.out.println("Get object");
				m = (Message) o;
//				System.out.println(m.toString());
			} catch(SocketException e){
				System.out.println("Connection to Client lost.");
				break;
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generatedcatch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(o instanceof Message){
				m = (Message) o;
				//If message is Login-Message log user in
				String ret;
				if(m instanceof LoginMessage){
					ret = server.request(m);
					if(ret.startsWith("Succesfully")){
						User tmp = new User();
						tmp.setName(m.getName());;
						user = server.getUser().get(server.getUser().indexOf(tmp));
						System.out.println(user.getName());
					}
				}
				else{
					ret = server.request(m);
				}
				try {
					out.writeObject(ret);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		try {
			client.close();
		} catch (IOException e) {
			System.out.println("Could not close client");
		}
	}
	
}
