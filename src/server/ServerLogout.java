package server;

import model.User;

public class ServerLogout {

	private Server server;
	
	public ServerLogout(Server server) {
		this.server = server;
	}
	
	public String logout(String name) {
		User loger = null;
		for(int i=0;i < user.size();i++) {
			if(name.equals(user.get(i).getName())) {
				loger = user.get(i);
			}
		}
		if (loger != null) {
			loger.setActive(false);
			//TODO schliessen der Verbindungen
			return "Succesfully loged out as: "+loger.getName();
		}
		return "Error you must log in first!";
	}

}
