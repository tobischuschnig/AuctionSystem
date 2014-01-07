package connect.test;

import connect.ReceiveConnection;

public class ConnectTest {
	public static void main(String[] args) {
		ReceiveConnection r = new ReceiveConnection(5000, null);
		Thread t = new Thread(r);
		t.start();
	}
}
