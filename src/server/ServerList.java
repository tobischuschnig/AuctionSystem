package server;

import model.ListMessage;
import model.Message;

/**
 * Diese Klasse ist fuer eine Funktion des Servers verantwortlich
 * Wenn eine ListMessage in der Methode request uebergeben wird wird dieser ServerList
 * aufgerufen.
 * Anschliessend werden alle Auktionen zurueck gegeben.
 * @author Tobias
 * @version 2014-01-05
 */
public class ServerList implements ServerAction {
	
	/**
	 * Alle Auktionen werden aufgelistet und zurueckgegeben
	 * @param server der Server auf dem gearbeitet wird
	 * @param message die BidMessage mit allen Parametern
	 * @return die Error oder Succesmeldung
	 */
	@Override
	public String doOperation(Message message, Server server) {
		ListMessage bid = (ListMessage) message;
		String out = "";
		for(int i=0;i< server.getAuction().size();i++) { // durchgehen der Auktionen in out schreiben
			if(server.getAuction().get(i).isFinished() == false) {
				String hilf;
				if (server.getAuction().get(i).getLastUser() == null) { //Wenn es keinen Bieter gibt
					hilf = "none";
				}
				else {
					hilf=server.getAuction().get(i).getLastUser().getName();
				}
				out+=   "ID: "+ server.getAuction().get(i).getId()+ 
						"    Description: " +server.getAuction().get(i).getDescription()+ 
						"    Highestbid: " + server.getAuction().get(i).getHighestBid() + 
						"    from: "+hilf+"\n";
			}
		}
		return out;
		
	}

}
