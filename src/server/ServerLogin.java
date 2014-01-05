package server;

import java.util.ArrayList;

import model.Auction;
import model.LoginMessage;
import model.Message;
import model.User;

public class ServerLogin implements ServerAction {
	
	@Override
	public String doOperation(Message message, Server server) {
		LoginMessage bid = (LoginMessage) message;
		User loger = null;
		for(int i=0;i < server.getUser().size();i++) {
			if(bid.getName().equals(server.getUser().get(i).getName())) {
				loger = server.getUser().get(i);
			}
		}
		if(loger == null) {
			loger = new User();
			loger.setName(bid.getName());
			loger.setAdresse("123"); //TODO aus LoginMessage hohlen
			loger.setTcpPort(123); //TODO Port?
			loger.setUdpPort(123); //TODO Port?
			loger.setActive(true);
			loger.setMessages(new ArrayList<String>());
			server.getUser().add(loger);
			return "Succesfully suscribed and loged in as: "+loger.getName();
		}
		else if (loger != null && loger.isActive()==false){
			loger.setAdresse(""); //TODO aus LoginMessage hohlen
			loger.setTcpPort(123);
			loger.setUdpPort(123);
			loger.setActive(true);
			return "Succesfully loged in as: "+loger.getName();
		}
		return "This User is allready loged in please log out first!";
		
	}
}
