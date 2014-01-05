package server;

import java.util.ArrayList;

import model.Auction;
import model.CreateMessage;
import model.Message;
import model.User;

public class ServerCreate implements ServerAction{

	private Server server;
	
	public ServerCreate(Server server) {
		this.server = server;
	}

	@Override
	public String doOperation(Message message, Server server) {
		CreateMessage create = (CreateMessage) message;
		User creater = null;
		for(int i=0;i < server.getUser().size();i++) {
			if(create.getName().equals(server.getUser().get(i).getName())) {
				creater = server.getUser().get(i);
			}
		}
		if (creater == null) {
			return "This User doesn't exists!";
		}
		/////////////////////////////////////////////////////////////////////////
		//		TODO hier wirklich gut geloest???
		ArrayList<Auction> hilf = server.getAuction();
		hilf.add(new Auction(creater, create.getDesc(),  create.getDuration() , hilf.size() ));
		server.setAuction(hilf);
		/////////////////////////////////////////////////////////////////////////
		return "You have created a new auction!";	
	}

}
