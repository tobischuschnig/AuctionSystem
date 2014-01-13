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
		String ret="";
		User loger = null;
		for(int i=0;i < server.getUser().size();i++) { //Suchen des Users
			if(bid.getName().equals(server.getUser().get(i).getName())) {
				loger = server.getUser().get(i);
			}
		}
		if(loger == null) { // Wenn nicht vorhanden anlegen
			loger = new User();
			loger.setName(bid.getName());
			loger.setAdresse(bid.getAdresse()); 
			loger.setTcpPort(bid.getTcpPort()); 
			loger.setUdpPort(bid.getUdpPort()); 
			loger.setActive(true);
			loger.setMessages(new ArrayList<String>());
			server.getUser().add(loger);
			return "Successfully suscribed and loged in as: "+loger.getName();
		}
		else if (loger != null && loger.isActive()==false){ //sonst active setzen 
			loger.setAdresse(bid.getAdresse()); 
			loger.setTcpPort(bid.getTcpPort()); 
			loger.setUdpPort(bid.getUdpPort()); 
			loger.setActive(true);
			if(loger.getMessages().size() != 0){
				ret += loger.getMessages().toString();
			}
			else
				ret ="No Messages";
			return "Successfully loged in as: "+loger.getName()+"\nUnread messages: "+ret;
		}
		return "This User is allready loged in please log out first!";
		
	}
}
