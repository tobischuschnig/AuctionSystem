package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.LoginMessage;
import model.Message;

/**
 * Connects from the Client to the server using TCPSockets
 * 
 * @author Daniel Reichman
 * @version 2014-01-07
 */
public class TCPConnector implements Runnable{
	
	int tcpPort;
	Message message; //Message die gesendet werden soll
	Socket s;
	Thread t; //Thread in  dem das Programm läuft
	//Fehlt Objekt für Ausgabe
	
	public TCPConnector(int p){
		tcpPort = p;
		t = new Thread(this);
		t.start();
		System.out.println("Connector started");
		try {
			s = new Socket("localhost",tcpPort);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Übergeben der Message
	 */
	public void sendMessage(Message m){
		message = m;
		t.notify();
	}

	@Override
	public void run() {
		try {
			while(true){
				if(message == null)
					try {
						this.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				s.setKeepAlive(true);
				ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
				objectOutput.writeObject(message);
				ObjectInputStream input = new ObjectInputStream(s.getInputStream());
				String s="";
				try {
					this.wait(1000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					s = (String)input.readObject();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(s);
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				message = null;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
