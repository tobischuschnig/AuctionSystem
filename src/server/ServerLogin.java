package server;

import java.util.ArrayList;

import model.LoginMessage;
import model.Message;
import model.User;

/**
 * Diese Klasse ist fuer eine Funktion des Servers verantwortlich
 * Wenn eine LoginMessage in der Methode request uebergeben wird wird dieser ServerLogin
 * aufgerufen.
 * Anschliessend wird der User angelegt oder auf active gesetzt
 * @author Tobias
 * @version 2014-01-05
 */
public class ServerLogin implements ServerAction {
	
	/**
	 * Mithifle der LoginMessage den User anlegen oder einloggen
	 * @param server der Server auf dem gearbeitet wird
	 * @param message die LoginMessage mit allen Parametern
	 * @return die Error oder Succesmeldung
	 */
	@Override
	public String doOperation(Message message, Server server) {
		LoginMessage bid = (LoginMessage) message;
		User loger = null;
		for(int i=0;i < server.getUser().size();i++) { //Suchen des Users
			if(bid.getName().equals(server.getUser().get(i).getName())) {
				loger = server.getUser().get(i);
			}
		}
		if(loger == null) { // Wenn nicht vorhanden anlegen
			loger = new User();
			loger.setName(bid.getName());
			loger.setAdresse("123"); //TODO aus LoginMessage hohlen
			loger.setTcpPort(123); //TODO Port?
			loger.setUdpPort(123); //TODO Port?
			loger.setActive(true);
			loger.setMessages(new ArrayList<String>());
			server.getUser().add(loger);
			return "Succesfully suscribed and loged in as: "+loger.getName();
		}
		else if (loger != null && loger.isActive()==false){ //sonst active setzen 
			loger.setAdresse(""); //TODO aus LoginMessage hohlen
			loger.setTcpPort(123);
			loger.setUdpPort(123);
			loger.setActive(true);
			return "Succesfully loged in as: "+loger.getName();
		}
		return "This User is allready loged in please log out first!";
		
	}
}
