package Client;

import model.LoginMessage;

public class TestConnector {
	public static void main(String[] args) {
		TCPConnector tcp = new TCPConnector(5000);
		LoginMessage m = new LoginMessage();
		m.setName("Name");
		tcp.sendMessage(m);
		m.setName("Zweite Message");
		tcp.sendMessage(m);
	}
}
