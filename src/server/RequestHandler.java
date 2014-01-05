package server;

import model.*;

/**
 * Hier wird der Request ueberprueft und die entsprechende Server Funktionalitaet aufgerufen
 * @author Tobias
 * @version 2013-01-05
 */
public class RequestHandler {
	private ServerAction serverAction; //TODO soll ich hier das verwenden oder so wie es ist
	
	/**
	 * Uberprueft welche Message uebergeben wurde und ruft dann den entsprechenden Server auf
	 * @param message die Message die alle Parameter beinhaltet und was gemacht werden soll
	 * @param server der Server auf dem gearbeitet werden soll
	 * @return das Ergebniss der Operation das via TCP an den Client weitergegeben werden soll
	 */
	public String execute(Message message,Server server) {
		String wert = "";
		//TODO alle Nachrichten richtig verarbeitet
		if(message instanceof BidMessage) { //Message = BidMessage
			ServerBid bid = new ServerBid();
			wert = bid.doOperation(message, server);
		}
		else if(message instanceof CreateMessage) {  //Message = CreateMessage
			ServerCreate create = new ServerCreate();
			wert = create.doOperation(message, server);
			//TODO hat eig keinen rueckgabewert
		}
		else if(message instanceof ListMessage) { //Message = ListMessage
			ServerList list = new ServerList();
			wert = list.doOperation(message, server);
		}
		else if(message instanceof LoginMessage) { //Message = LoginMessage
			ServerLogin login = new ServerLogin();
			wert = login.doOperation(message, server);
		}
		else if(message instanceof LogoutMessage) { //Message = LogoutMessage
			ServerLogout logout = new ServerLogout();
			wert = logout.doOperation(message, server);
		}
		return wert;
	}
}
