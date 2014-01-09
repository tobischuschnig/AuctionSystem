package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.LoginMessage;

public class TestConnector {
	public static void main(String[] args) {
		TCPConnector tcp = new TCPConnector(5000, new CLI());
//		Thread t = new Thread(tcp);
//		t.start();
		while(true){
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("You can write now");
			try {
				String name = r.readLine();
				LoginMessage m = new LoginMessage();
				m.setName(name);
				tcp.sendMessage(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
