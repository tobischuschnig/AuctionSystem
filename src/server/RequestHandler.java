package server;

import model.*;

public class RequestHandler {
	private ServerAction serverAction;
	
	public void execute(BidMessage message,Server server) {
		serverAction = new ServerBid(server);
		serverAction.doOperation(message,server);
	}
	public void execute(CreateMessage message,Server server,int wert) {
		
	}
//	public void execute(LoginMessage message,Server server) {
//
//	}
//	public void execute(LogoutMessage message,Server server) {
//
//	}
//	public void execute(ListMessage message,Server server) {
//
//	}
}
