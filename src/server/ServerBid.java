package server;

import java.util.ArrayList;

import model.Auction;
import model.BidMessage;
import model.Message;
import model.User;

/**
 * Diese Klasse ist fuer eine Funktion des Servers verantwortlich
 * Wenn eine BidMessage in der Methode request uebergeben wird wird dieser ServerBid 
 * aufgerufen.
 * Anschliessend wird auf eine Auktion mithifle der Paramter der BidMessage geboten
 * @author Tobias
 * @version 2014-01-05
 */
public class ServerBid implements ServerAction {
	
	/**
	 * Diese Methode fuehrt das bieten auf eine Auktion durch
	 * Es wird ueberprueft ob das Gebot niedriger oder hoeher ist und ob der User existiert
	 * Bei einem erfolgreichem gebot wird der letze User informiert das er ueberboten wurde
	 * @param server der Server auf dem gearbeitet wird
	 * @param message die BidMessage mit allen Parametern
	 * @return die Error oder Succesmeldung
	 */
	@Override
	public String doOperation(Message message, Server server) {
		BidMessage bid = (BidMessage) message;
		User bidder = null;
		for(int i=0;i < server.getUser().size();i++) { //Suche nach dem User
			if(bid.getName().equals(server.getUser().get(i).getName())) {
				bidder = server.getUser().get(i);
			}
		}
		if (bidder == null) { //Wenn der User nicht existiert wird abgebrochen
			return "This User doesn't exists!";
		}
		
		for(int i=0;i< server.getAuction().size();i++) {
			if(bid.getId() == server.getAuction().get(i).getId()) {
				if(server.getAuction().get(i).getHighestBid() < bid.getAmount()) { //Ist das gebot hoeher
					User lastUser;
					Auction hilf = server.getAuction().get(i);
					hilf.setHighestBid(bid.getAmount());
					lastUser = server.getAuction().get(i).getLastUser();
					hilf.setLastUser(bidder);
					server.getAuction().set(i,hilf);
					
					if (lastUser != null) { //Wenn es einen vorherigen Bieter gibt wird er informiert
						ArrayList<User> al = new ArrayList();
						al.add(lastUser);
						server.notify(al,"You have been overbid on '" +
							server.getAuction().get(i).getDescription()+"' with the ID: "+
							server.getAuction().get(i).getId()+".");
					}
					
					//Informieren das das gebot erfolgreich war
					return "You successfully bid with "+bid.getAmount()+" on '"
						+server.getAuction().get(i).getDescription()+"'.";
				}
				else { //Fehlermeldung nicht hoeher als das vorherige
					return "Your bid must be higher then the current bid! The current bid ist: "+
								server.getAuction().get(i).getHighestBid();
				}
			}
		}
		return "There is no Auction with this ID!"; //Fehlermeldung wenn die Id nicht vorhanden ist
	}
}
