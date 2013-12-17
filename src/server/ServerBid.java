package server;

import model.BidMessage;
import model.User;

public class ServerBid {
	
	private Server server;
	
	public ServerBid(Server server) {
		this.server = server;
	}

	public String bid(BidMessage bid) {
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

}
