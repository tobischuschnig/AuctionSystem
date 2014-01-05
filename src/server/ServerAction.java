package server;

import model.Message;
import server.Server;;


public interface ServerAction {
	public String doOperation(Message message,Server server);
}
