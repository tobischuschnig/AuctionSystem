package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class NotificationReceiver implements Runnable{
	private Client client;
	
	public NotificationReceiver(Client c){
		Thread t = new Thread(this);
		t.start();
		client = c;
	}

	@Override
	public void run() {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(client.getUdpPort());
			System.out.println(client.getUdpPort());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			byte[] buf = new byte[1024];
			System.out.println("receie");
			DatagramPacket p=new DatagramPacket(buf, buf.length);
			try {
				ds.receive(p);
				client.getCli().out(new String(p.getData()).trim());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
}