package connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.Server;
import server.UserHandler;

/**
 * WWaits for incoming TCP Connections.
 * If a Connection is established, the server forwards to Socket to
 * a Dispatcher Thread (UserHandler)
 * 
 * 
 * @author Daniel Reichmann
 * @version 10-12-2013
 *
 */
public class ReceiveConnection implements Runnable{
	private Server server;
	private int tcpPort;
	private boolean listen = true;
	
	/**
	 * Constructor
	 * @param tcp		TCP Port 
	 * @param serv		Server
	 */
	public ReceiveConnection(int tcp, Server serv){
		tcpPort = tcp;
		server = serv;
	}
	
	@Override
	public void run(){
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(tcpPort);
		} catch (IOException e) {
			System.out.println("Could not listen on specififc port");
			e.printStackTrace();
		}
		while(listen){
			System.out.println("Ready to Listen");
			Socket client = null;
			try {
				client = ss.accept();
								
			} catch (IOException e) {			}
			new UserHandler(client, server);
		}
		try {
			ss.close();
		} catch (IOException e) {		}
	}
}
