package server;

import java.util.ArrayList;

import connect.UDPNotifier;

import model.*;

/**
 * Der Server mit allen Funktionalitaeten sowie den Daten der User.
 * Hat eine request Methode auf der alle Anfragen angenommen werdne und weitergeleitet werden
 * @author Tobias
 * @version 2013-01-05
 */
public class Server {

	private int tcpPort;
	private ArrayList<User> user;
	private ArrayList<Auction> auction;
	private String todo; //TODO brauch ich nicht mehr
	private AuctionHandler ahandler;
	private RequestHandler rhandler; 
	private UDPNotifier udp;
	
	/**
	 * Der Standartkonstruktor hier werden alle Attribute die spaeter verwendet werden
	 * initialisiert.
	 */
	public Server() {
		user=new ArrayList<User>();
		auction=new ArrayList<Auction>();
		ahandler = new AuctionHandler(this);
		rhandler = new RequestHandler();
		Thread athread = new Thread();
		athread.setPriority(Thread.MIN_PRIORITY);
		new Thread(ahandler).start();
	}

	/**
	 * Hier werden alle Anfragen angenommen und an den RequestHandler weitergegeben
	 * @param message die Message die alle Parameter beinhaltet und was gemacht werden soll
	 * @return das Ergebniss der Operation das via TCP an den Client weitergegeben werden soll
	 */
	public String request(Message message) {
		return rhandler.execute(message, this);
	}
	
	/**
	 * Hier wird die notify Methode des UDPNotifiers aufgerufen der die Nachricht via
	 * UDP an die entsprechenden Clients schickt
	 * @param al die betroffenen User die die Message erhalten sollen
	 * @param message was geschickt werden soll
	 */
	public void notify(ArrayList<User> al, String message) {
		//udp.notify(al,message);
		System.out.println(message); //TODO nach tests entfernen und wechseln
	}
	
	
	
	
	/**
	 * @return the tcpPort
	 */
	public int getTcpPort() {
		return tcpPort;
	}


	/**
	 * @param tcpPort the tcpPort to set
	 */
	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}


	/**
	 * @return the user
	 */
	public ArrayList<User> getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(ArrayList<User> user) {
		this.user = user;
	}


	/**
	 * @return the auction
	 */
	public ArrayList<Auction> getAuction() {
		return auction;
	}


	/**
	 * @param auction the auction to set
	 */
	public void setAuction(ArrayList<Auction> auction) {
		this.auction = auction;
	}


	/**
	 * @return the todo
	 */
	public String getTodo() {
		return todo;
	}


	/**
	 * @param todo the todo to set
	 */
	public void setTodo(String todo) {
		this.todo = todo;
	}
}
