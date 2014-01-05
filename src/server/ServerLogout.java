package server;

import java.util.ArrayList;

import model.LogoutMessage;
import model.Message;
import model.User;

public class ServerLogout implements ServerAction{

	private Server server;
	
	public ServerLogout(Server server) {
		this.server = server;
	}
	
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
			//loger.setActive(false);
			
			/////////////////////////////////////////////////////////////////////////
			//TODO hier wirklich gut geloest???
			ArrayList<User> hilf = server.getUser();
			hilf.get(ii).setActive(false);
			server.setUser(hilf);
			/////////////////////////////////////////////////////////////////////////
			
			//TODO schliessen der Verbindungen
			return "Succesfully loged out as: "+loger.getName()+server.getUser().get(ii).isActive();
		}
		return "Error you must log in first!";
	}

}
