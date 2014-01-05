package server;

import java.util.ArrayList;

import model.LogoutMessage;
import model.Message;
import model.User;

public class ServerLogout implements ServerAction{

	@Override
	public String doOperation(Message message, Server server) {
		LogoutMessage logout = (LogoutMessage) message;
		User loger = null;
		int ii = 0;
		for(int i = 0;i < server.getUser().size();i++) {
			if(logout.getName().equals(server.getUser().get(i).getName())) {
				loger = server.getUser().get(i);
				ii = i;
			}
		}
		if(loger == null) {
			return "This User doesn't exists!";
		}
		else if (loger != null && server.getUser().get(ii).isActive() == true) {
			server.getUser().get(ii).setActive(false);			
			//TODO schliessen der Verbindungen?
			return "Succesfully loged out as: "+loger.getName();
		}
		return "Error you must log in first!";
	}

}
