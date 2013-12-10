package connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.Server;

/**
 * Wartet auf eingehende Verbindungen zum Server.
 * Bekommt er eine Verbindung, so leitet er diese an einen neuen Thread weiter (UserHandler)
 * Dieser ist fortan für die Verbindung verantwortlich.
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
	
	public ReceiveConnection(int tcp, Server serv){
		tcpPort = tcp;
		server = serv;
	}
	
	/**
	 * Wartet auf eingehende Verbindungen.
	 * Wird eine Verbindung aufgenommen, so wird diese an einen UserHandler weitergeleitet
	 */
	public void run(){
		ServerSocket ss;
		try {
			ss = new ServerSocket(tcpPort);
		} catch (IOException e) {
			System.out.println("Could not listen on specififc port");
			e.printStackTrace();
		}
		while(listen){
			Socket client = ss.accept();
			new UserHandler(client);
		}
		ss.close();
	}
}
