package server;

import java.util.ArrayList;

import model.Auction;
import model.BidMessage;
import model.Message;
import model.User;

public class ServerBid implements ServerAction {
	
	private Server server;
	
	public ServerBid(Server server) {
		this.server = server;
	}
	
	@Override
	public String doOperation(Message message, Server server) {
		BidMessage bid = (BidMessage) message;
		User bidder = null;
		for(int i=0;i < server.getUser().size();i++) {
			if(bid.getName().equals(server.getUser().get(i).getName())) {
				bidder = server.getUser().get(i);
			}
		}
		if (bidder == null) {
			return "This User doesn't exists!";
		}
		
		for(int i=0;i< server.getAuction().size();i++) {
			if(bid.getId() == server.getAuction().get(i).getId()) {
				if(server.getAuction().get(i).getHighestBid() < bid.getAmount()) {
					/////////////////////////////////////////////////////////////////////////
					//TODO hier wirklich gut geloest???
					ArrayList<Auction> hilf = server.getAuction();
					hilf.get(i).setHighestBid(bid.getAmount());
					hilf.get(i).setLastUser(bidder);
					server.setAuction(hilf);
					/////////////////////////////////////////////////////////////////////////
					return "You successfully bid with "+bid.getAmount()+" on '"
						+server.getAuction().get(i).getDescription()+"'.";
				}
				else {
					return "Your bid must be higher then the current bid! The current bid ist: "+
								server.getAuction().get(i).getHighestBid();
				}
			}
		}
		return "There is no Auction with this ID!";
	}
}
