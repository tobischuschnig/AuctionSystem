package server;

import model.ListMessage;
import model.Message;

public class ServerList implements ServerAction {

	private Server server;
	
	public ServerList(Server server) {
		this.server = server;
	}
	
	@Override
	public String doOperation(Message message, Server server) {
		ListMessage bid = (ListMessage) message;
		String out = "";
		for(int i=0;i< server.getAuction().size();i++) {
			String hilf;
			if (server.getAuction().get(i).getLastUser() == null) {
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
		return out;
		
	}

}
