package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
	Condition setMessage, sendMessage;
	Thread t; //Thread in  dem das Programm läuft
	//Fehlt Objekt für Ausgabe
	
	public TCPConnector(int p){
		tcpPort = p;
		setMessage = lock.newCondition();
		sendMessage = lock.newCondition();
//		t = new Thread(this);
//		t.start();
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
		lock.lock();
		System.out.println(m.getName() + "MessagE");
		if(message != null){
			synchronized (message) {
				message=m;
			}
		}
		else
			message = m;
		
//		lock.notify();
		
		sendMessage.signal();
		try {
			setMessage.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}

	@Override
	public void run() {
//		synchronized (this) {
//		lock.lock();
			try {
				
				while(true){
					try{
			
						lock.lock();
					System.out.println("Senden");
					if(message == null){
						try {
//							lock.wait();
							System.out.println("Message da");
							setMessage.signalAll();
							sendMessage.await();
							
							continue;
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							System.out.println("got message");
						}
					}
					else{
						System.out.println("1");	
						
						ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
						objectOutput.writeObject(message);
						ObjectInputStream input = new ObjectInputStream(s.getInputStream());
						String s="";
//						try {
////							t.sleep(0);
//						} catch (InterruptedException e2) {
//							// TODO Auto-generated catch block
//							e2.printStackTrace();
//						}
						try {
							s = (String)input.readObject();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println(s);
//						try {
//							this.wait();
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						try {
//							lock.wait();
							setMessage.signalAll();
							sendMessage.await();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							System.out.println("got message");
						}
						
						
					finally{
						System.out.println("unlock");
						lock.unlock();
						message=null;
					}
						message=null;
//						while(true){}
					}
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
			
			
//		}


		
	}

}
