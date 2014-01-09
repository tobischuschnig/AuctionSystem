package Client;

import model.LoginMessage;

public class TestConnector {
	public static void main(String[] args) {
		TCPConnector tcp = new TCPConnector(5000);
		Thread t = new Thread(tcp);
		t.start();
		int i = 0;
		while(true){
			LoginMessage m = new LoginMessage();
			m.setName(++i+"");
			tcp.sendMessage(m);
			
		}
	}
}
