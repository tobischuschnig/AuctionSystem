package server;

import java.util.ArrayList;
import java.util.Date;

import model.Auction;

public class AuctionHandler implements Runnable {
	private ArrayList<Auction> auction; //TODO brauch ich eig net da ich mir die Auctions immer neu 
	//holen muss
	private Server server;

	public AuctionHandler(Server server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		boolean con = true;
		while(con == true) { //TODO vill ende?
			Date now = new Date();
			//System.out.println(now);
			for(int i=0;i< server.getAuction().size();i++) {
				if(server.getAuction().get(i).getDeadline().compareTo(now) <= 0) {
					System.out.println(server.getAuction().get(i).getDescription()+ " is over.");
					//TODO richtiger notify
					String wert = "The auction '"+server.getAuction().get(i).getDescription()+
							"' has ended. "+server.getAuction().get(i).getLastUser()+" won with "+
							server.getAuction().get(i).getHighestBid()+". \n";
					String wert1 = "The auction '"+server.getAuction().get(i).getDescription()+
							"' has ended. You won with "+
							server.getAuction().get(i).getHighestBid()+". \n";
					//TODO an die Richtigen leute uebergeben via notify UDP bzw TCP
					con = false;
				}
			}
		}
	}
}
