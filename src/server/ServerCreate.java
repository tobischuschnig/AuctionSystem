package server;

import model.Auction;
import model.CreateMessage;
import model.User;

public class ServerCreate {

	private Server server;
	
	public ServerCreate(Server server) {
		this.server = server;
	}

	public String create(CreateMessage create) {
		User creater = null;
		for(int i=0;i < user.size();i++) {
			if(create.getName().equals(user.get(i).getName())) {
				creater = user.get(i);
			}
		}
		if (creater == null) {
			return "This User doesn't exists!";
		}
		auction.add(new Auction(creater,create.getDesc(),""+create.getDuration()));
		return "You have created a new auction!";	
	}

}
