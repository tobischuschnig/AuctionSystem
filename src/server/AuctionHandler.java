package server;

import java.util.ArrayList;
import java.util.Date;

import model.*;

public class AuctionHandler implements Runnable {
	private ArrayList<Auction> auction; //TODO brauch ich eig net da ich mir die Auctions immer neu 
	//holen muss
	private Server server;

	public AuctionHandler(Server server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		while(true) {
			Date now = new Date();
			//System.out.println(now);
			for(int i=0;i< server.getAuction().size();i++) {
				if(server.getAuction().get(i).getDeadline().compareTo(now) <= 0 && 
						server.getAuction().get(i).isFinished() == false) {
					server.getAuction().get(i).setFinished(true);
					//System.out.println(server.getAuction().get(i).getDescription()+ " is over.");
					
					if(server.getAuction().get(i).getLastUser() != null) {
						
						String wert = "The auction '"+server.getAuction().get(i).getDescription()+
								"' has ended. "+server.getAuction().get(i).getLastUser().getName()+" won with "+
								server.getAuction().get(i).getHighestBid()+". \n";
					
						ArrayList<User> al = new ArrayList();
						for(int ii = 0; ii < server.getUser().size(); ii++) {
							if((server.getUser().get(ii).getName().equals(server.getAuction().get(i).getLastUser()))==false) {
								al.add(server.getUser().get(ii));
							}
						}
						server.notify(al,wert);


						String wert1 = "The auction '"+server.getAuction().get(i).getDescription()+
								"' has ended. You won with "+
								server.getAuction().get(i).getHighestBid()+". \n";

						al = new ArrayList();
						al.add(server.getAuction().get(i).getLastUser());
						server.notify(al, wert1);
					}
					else {
						server.notify(server.getUser(),"The auction '"
							+server.getAuction().get(i).getDescription()+"' hast ended. Nobody bidded.");
					}
					//TODO an die Richtigen leute uebergeben via notify UDP
				}
			}
		}
	}
}
