package server;


import model.Auction;
import model.CreateMessage;
import model.Message;
import model.User;

/**
 * Diese Klasse ist fuer eine Funktion des Servers verantwortlich
 * Wenn eine CreateMessage in der Methode request uebergeben wird wird dieser ServerCreate 
 * aufgerufen.
 * Anschliessend wird eine neue Auktion mit den Parametern der CreateMessage erstellt.
 * @author Tobias
 * @version 2014-01-05
 */
public class ServerCreate implements ServerAction{

	/**
	 * Hier wird die neue Auktion mit den Parametern der CreateMessage erstellt
	 * @param server der Server auf dem gearbeitet wird
	 * @param message die CreateMessage mit allen Parametern
	 * @return die Error oder Succesmeldung
	 */
	@Override
	public String doOperation(Message message, Server server) {
		CreateMessage create = (CreateMessage) message;
		User creater = null;
		for(int i=0;i < server.getUser().size();i++) { //Suchen des Users der es erstellt hat
			if(create.getName().equals(server.getUser().get(i).getName())) {
				creater = server.getUser().get(i);
			}
		}
		if (creater == null) { //wenn der User nicht existiert wird abgebrochen mit Fehlermeldung.
			return "This User doesn't exists please log in first!";
		}
		//Hinzufuegen der Auktion und Erfolg ausgeben.
		Auction hilf = new Auction(creater, create.getDesc(),  create.getDuration() , server.getAuction().size() );
		server.getAuction().add(hilf);
		server.notify(server.getUser(),"An auction '"+hilf.getDescription()+"' with the ID: "
				+hilf.getId()+" has been created and will end on "
				+hilf.getDeadline()+".");
		return "You have succesfully created a new auction!";	
	}

}
