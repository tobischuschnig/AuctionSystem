package connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.Server;
import server.UserHandler;

/**
 * WWaits for incoming TCP Connections.
 * If a Connection is established, the server forwards to Socket to
 * a Dispatcher Thread (UserHandler)
 * 
 * 
 * @author Daniel Reichmann <dreichmann@student.tgm.ac.at>
 * @version 10-12-2013
 *
 */
public class ReceiveConnection implements Runnable{
	private Server server;
	private int tcpPort;
	private  ExecutorService service;
	
	/**
	 * Constructor
	 * @param tcp		TCP Port 
	 * @param serv		Server
	 */
	public ReceiveConnection(int tcp, Server serv){
		tcpPort = tcp;
		server = serv; //TODO hier threadpool initialisieren 
		service = Executors.newFixedThreadPool(10);
	}
	
	/**
	 * Receives a connection an dispatches it
	 */
	@Override
	public void run(){
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(tcpPort);
			ss.setSoTimeout(5000);
		} catch (IOException e) {
			System.out.println("Could not listen on specififc port\nExit with enter.");
			server.setActive(false);
			return;
		}
		System.out.println("Server is listening");
		while(server.isActive()){
			Socket client = null;
			try {
				client = ss.accept();
								
			} catch (IOException e) {			}
//			Thread thread = new Thread(new UserHandler(client, server));
//			service.submit(new UserHandler(client, server)); //TODO Hier Threadpool
			new UserHandler(client, server);
		}
		try {
			if(ss!=null)
				ss.close();
		} catch (IOException e) {		}
	}
}
