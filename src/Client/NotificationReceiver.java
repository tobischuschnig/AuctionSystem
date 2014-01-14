package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Receive UDP packages and prints it on the ouptu of the Client.
 * 
 * @author Daniel Reichman
 * @version 01-10-2014
 */
public class NotificationReceiver implements Runnable{
	private Client client;
	
	/**
	 * Creates the Receiver and starts receiving
	 * @param c	Client where the output is
	 */
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
			//System.out.println(client.getUdpPort());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(client.isActive()){
			byte[] buf = new byte[1024];
			DatagramPacket p=new DatagramPacket(buf, buf.length);
			try {
				ds.receive(p);
				client.getCli().out(new String(p.getData()).trim());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		ds.close();
	}	
}