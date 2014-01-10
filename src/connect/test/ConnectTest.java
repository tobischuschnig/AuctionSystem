package connect.test;

import server.Server;
import connect.ReceiveConnection;

public class ConnectTest {
	public static void main(String[] args) {
		Server s = new Server();
		s.setTcpPort(5000);
		ReceiveConnection r = new ReceiveConnection(5000, s);
		Thread t = new Thread(r);
		t.start();
	}
}
