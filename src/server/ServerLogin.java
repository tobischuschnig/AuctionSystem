package server;

import java.util.ArrayList;

import model.User;

public class ServerLogin {

	private Server server;
	
	public ServerLogin(Server server) {
		this.server = server;
	}
	
	public String login (String name) {
		User loger = null;
		for(int i=0;i < user.size();i++) {
			if(name.equals(user.get(i).getName())) {
				loger = user.get(i);
			}
		}
		if(loger == null) {
			loger = new User();
			loger.setName(name);
			loger.setAdresse("");
			loger.setTcpPort(123);
			loger.setUdpPort(123);
			loger.setActive(true);
			loger.setMessages(new ArrayList<String>());
			return "Succesfully loged in as: "+loger.getName();
		}
		else if (loger.isActive()==false){
			loger.setAdresse("");
			loger.setTcpPort(123);
			loger.setUdpPort(123);
			loger.setActive(true);
			return "Succesfully loged in as: "+loger.getName();
		}
		return "You are allready loged in please log out first!";
		
	}
}
