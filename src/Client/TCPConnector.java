package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

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
	ReentrantLock lock = new ReentrantLock();
	Condition con; //Thread wait until message is set
	Thread t; //Thread in  dem das Programm läuft
	//Fehlt Objekt für Ausgabe
	ObjectOutputStream objectOutput; //Strem for Output
	ObjectInputStream input; //Stream for Input
	UI ui; //Output into CLI/GUI
	
	public TCPConnector(int p, UI ui){
		tcpPort = p;
		this.ui = ui;
		con = lock.newCondition();
//		con.await();
		t = new Thread(this);
		
		System.out.println("Connector started");
		try {
			s = new Socket("localhost",tcpPort);
			objectOutput = new ObjectOutputStream(s.getOutputStream());
			input = new ObjectInputStream(s.getInputStream());
			objectOutput.writeObject(null); //Initialise stream
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.start();
	}
	/**
	 * Übergeben der Message
	 */
	public void sendMessage(Message m){
		lock.lock();
		message = m;
		con.signal();
		lock.unlock();
	}

	@Override
	public void run() {
		try {			
			while(true){
				try{
					lock.lock();
					System.out.println("Senden initiiert");	
					if(message==null){
						try {
							System.out.println("No Message yet");
							con.await();							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					objectOutput.writeObject(message);					 
					 System.out.println(input.toString());
					String s="";
					try {
						s = (String)input.readObject();
						ui.out(s);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					message=null;
				}finally{
					lock.unlock();
				}
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
