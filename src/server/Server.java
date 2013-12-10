package server;

import java.util.ArrayList;

import model.Auction;
import model.User;

public class Server implements Runnable{

	private int tcpPort;
	private ArrayList<User> user;
	private ArrayList<Auction> auction;
	private String todo;
	private AuctionHandler ahandler;
	
	public Server() {
		
	}
	
	
	@Override
	public void run() {
		if(todo.equals("!bid")) {
			
		}
		
	}


	private void notify(Message message) {
		
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
