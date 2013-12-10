package server;

import java.util.ArrayList;

import model.*;

public class Server {

	private int tcpPort;
	private ArrayList<User> user;
	private ArrayList<Auction> auction;
//	private String todo;
	private AuctionHandler ahandler;
	
	public Server() {
		
	}
	
	

	private String bid(BidMessage bid) {
		User bidder = null;
		for(int i=0;i < user.size();i++) {
			if(bid.getName().equals(user.get(i).getName())) {
				bidder = user.get(i);
			}
		}
		if (bidder == null) {
			return "This User doesn't exists!";
		}
		
		for(int i=0;i< auction.size();i++) {
			if(bid.getId() == auction.get(i).getId()) {
				if(auction.get(i).getHighestBid() < bid.getAmount()) {
					auction.get(i).setHighestBid(bid.getAmount());
					auction.get(i).setLastUser(bidder);
					return "You successfully bid with "+bid.getAmount()+" on '"
						+auction.get(i).getDescription()+"'.";
				}
				else {
					return "Your bid must be higher then the current bid! The current bid ist: "+auction.get(i).getHighestBid();
				}
			}
		}
		return "There is no Auction with this ID!";
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
