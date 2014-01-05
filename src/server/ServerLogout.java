package server;


import model.LogoutMessage;
import model.Message;
import model.User;


/**
 * Diese Klasse ist fuer eine Funktion des Servers verantwortlich
 * Wenn eine LogoutMessage in der Methode request uebergeben wird wird dieser ServerLogout
 * aufgerufen.
 * Anschliessend wird der User auf nicht active gesetzt
 * @author Tobias
 * @version 2014-01-05
 */
public class ServerLogout implements ServerAction{

	/**
	 * Mithifle der LogoutMessage den User abmelden
	 * @param server der Server auf dem gearbeitet wird
	 * @param message die LogoutMessage mit allen Parameter
	 * @return die Error oder Succesmeldung
	 */
	@Override
	public String doOperation(Message message, Server server) {
		LogoutMessage logout = (LogoutMessage) message;
		User loger = null;
		int ii = 0;
		for(int i = 0;i < server.getUser().size();i++) { //User suchen
			if(logout.getName().equals(server.getUser().get(i).getName())) {
				loger = server.getUser().get(i);
				ii = i;
			}
		}
		if(loger == null) { //Fehlermeldung wenn der User nicht existiert
			return "This User doesn't exists!";
		}
		else if (loger != null && server.getUser().get(ii).isActive() == true) { //sonst abmelden
			server.getUser().get(ii).setActive(false);			
			//TODO schliessen der Verbindungen?
			return "Succesfully loged out as: "+loger.getName();
		}
		return "Error you must log in first!";
	}

}
