package server;

import java.util.ArrayList;

import model.Auction;
import model.CreateMessage;
import model.Message;
import model.User;

public class ServerCreate implements ServerAction{

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
		Auction hilf = new Auction(creater, create.getDesc(),  create.getDuration() , server.getAuction().size() );
		server.getAuction().add(hilf);
		server.notify(server.getUser(),"An auction '"+hilf.getDescription()+"' with the ID: "
				+hilf.getId()+" has been created and will end on "
				+hilf.getDeadline()+".");
		return "You have succesfully created a new auction!";	
	}

}
