package server;

import model.*;

public class RequestHandler {
	private ServerAction serverAction;
	
	public String execute(Message message,Server server) {
		String wert = "";
		if(message instanceof BidMessage) {
			//serverAction = new ServerBid();
			ServerBid bid = new ServerBid();
			wert = bid.doOperation(message, server);
			//TODO Nachricht weiterleiten via UDP/TCP
		}
		else if(message instanceof CreateMessage) {
			ServerCreate create = new ServerCreate();
			wert = create.doOperation(message, server);
			//TODO Nachricht weiterleiten via UDP/TCP
		}
		else if(message instanceof ListMessage) {
			ServerList list = new ServerList();
			wert = list.doOperation(message, server);
			//TODO Nachricht weiterleiten via UDP/TCP
		}
		else if(message instanceof LoginMessage) {
			ServerLogin login = new ServerLogin();
			wert = login.doOperation(message, server);
			//TODO Nachricht weiterleiten via UDP/TCP
		}
		else if(message instanceof LogoutMessage) {
			ServerLogout logout = new ServerLogout();
			wert = logout.doOperation(message, server);
			//TODO Nachricht weiterleiten via UDP/TCP
		}
		return wert;
	}
}
