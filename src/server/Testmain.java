package server;

import java.util.ArrayList;
import java.util.Date;

import model.*;

public class Testmain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//User erstellen
//		User user = new User();
//		user.setActive(true);
//		user.setAdresse("123");
//		user.setName("name");
//		ArrayList<User> users = new ArrayList();
//		users.add(user);
		////////////////////////////
		
		
//		Auction auction = new Auction(user,"laptop1",duration);
//		Auction auction1 = new Auction(user,"laptop",duration);
//		ArrayList<Auction> auctions = new ArrayList();
//		auctions.add(auction1);
//		auctions.add(auction);
		
		//Server erstellen
		Server server = new Server();
//		server.setAuction(auctions);
//		server.setUser(users);
		///////////////////////
		
		//User erstellen mit LoginMessage
		LoginMessage login = new LoginMessage();
		login.setName("name");
		
		String wert3 = server.request(login);
		System.out.println(wert3);
		///////////////////////////
		
		
		//Auctionen erstellen mit CreateMessage
		Long duration = (long) 1531235;
		CreateMessage create = new CreateMessage();
		create.setDesc("laptop");
		create.setName("name");
		create.setDuration(duration);
		/////
		String wert1 = server.request(create);
		System.out.println(wert1);
		
		create.setDesc("Hand");
		wert1 = server.request(create);
		System.out.println(wert1);
		////////////////////////
		
		//Bieten mit BidMessage
		BidMessage bid = new BidMessage();
		
		bid.setAmount(123.123);
		bid.setId(1);
		bid.setName("name");
		///

		String wert = server.request(bid);
		System.out.println(wert);
		///////////////////////
		
		//Auflisten mit ListMessage
		ListMessage list = new ListMessage();
		list.setName("name");
		
		String wert2 = server.request(list);
		System.out.println(wert2);
		///////////////////////
	}

}