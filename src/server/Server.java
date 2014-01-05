package server;

import java.util.ArrayList;

import connect.UDPNotifier;

import model.*;

public class Server {

	private int tcpPort;
	private ArrayList<User> user;
	private ArrayList<Auction> auction;
	private String todo;
	private AuctionHandler ahandler;
	private RequestHandler rhandler; //TODO brauch ich eig net
	private UDPNotifier udp;
	
	public Server() {
		user=new ArrayList<User>();
		auction=new ArrayList<Auction>();
		ahandler = new AuctionHandler(this);
		rhandler = new RequestHandler();
		Thread athread = new Thread();
		athread.setPriority(Thread.MIN_PRIORITY);
		new Thread(ahandler).start();
	}

	public String request(Message message) {
		return rhandler.execute(message, this);
	}
	
	public void notify(ArrayList<User> al, String message) {
		//udp.notify(al,message);
		System.out.println(message); //TODO nach tests entfernen
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
