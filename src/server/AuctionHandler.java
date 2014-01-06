package server;

import java.util.ArrayList;
import java.util.Date;

import model.*;

/**
 * Hier uerberpruefe ich ob Auktionen abgelaufen sind
 * @author tobi
 * @version 2013-01-05
 */
public class AuctionHandler implements Runnable {
	//private ArrayList<Auction> auction; //TODO brauch ich eig net da ich mir die Auctions immer neu 
	//holen muss
	private Server server;

	/**
	 * Konstruktor mit uebergabe des benoetigten Servers
	 * @param server der Server auf dem ich arbeite
	 */
	public AuctionHandler(Server server) {
		this.server = server;
	}
	
	/**
	 * Uberpruefung mittels eines Threads ob die Auktionen die Deadline noch nicht 
	 * ueberschritten haben.
	 */
	@Override
	public void run() {
		while(true) {
			Date now = new Date();
			//System.out.println(now);
			for(int i=0;i< server.getAuction().size();i++) { //Alle Autkionen durchgehen
				//Schauen ob sie abgelaufen ist
				if(server.getAuction().get(i).getDeadline().compareTo(now) <= 0 && 
						server.getAuction().get(i).isFinished() == false) { //TODO Fehler Zeile 37 NullPointer Exception
					server.getAuction().get(i).setFinished(true);
					//System.out.println(server.getAuction().get(i).getDescription()+ " is over.");

					//Schauen ob jemand auf diese Auktion geboten hat
					if(server.getAuction().get(i).getLastUser() != null) {

						String wert = "The auction '"+server.getAuction().get(i).getDescription()+
								"' has ended. "+server.getAuction().get(i).getLastUser().getName()+" won with "+
								server.getAuction().get(i).getHighestBid()+". \n";

						ArrayList<User> al = new ArrayList();
						//Benachrichtgen aller User ausser dem der gewonnen hat
						for(int ii = 0; ii < server.getUser().size(); ii++) {
							if((server.getUser().get(ii).getName().equals(server.getAuction().get(i).getLastUser()))==false) {
								al.add(server.getUser().get(ii));
								//TODO warum wird hier auch derjenige benachrichtig der usgeschlossen werden soll
							}
						}
						server.notify(al,wert);

						//benachrichtigen des Users der gewonnen hat
						String wert1 = "The auction '"+server.getAuction().get(i).getDescription()+
								"' has ended. You won with "+
								server.getAuction().get(i).getHighestBid()+". \n";

						al = new ArrayList();
						al.add(server.getAuction().get(i).getLastUser());
						server.notify(al, wert1);
					}
					else {
						//Wenn niemand geboten hat
						server.notify(server.getUser(),"The auction '"
								+server.getAuction().get(i).getDescription()+"' hast ended. Nobody bidded.");
					}
				}
			}
		}
	}
}
