package connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.User;

/**
 * Schickt an alle User aus einer ArrayList eine Nachricht.
 * Versenden passiert mittels UDP-Paketen.
 * 
 * @author Daniel Reichmann
 * @version 10-12-2013
 */
public class UDPNotifier implements Notifier{
	
	public void notify(ArrayList<User> al, String message){
		DatagramSocket ds;
		byte[] buf = message.getBytes();
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		for (User user : al) {
			try {
				System.out.println("send" + user.getAdresse()+":"+user.getUdpPort());
				ds = new DatagramSocket();
				InetAddress address = InetAddress.getByName(user.getAdresse());
				dp = new DatagramPacket(buf, buf.length, address,user.getUdpPort());
				ds.send(dp);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				System.out.println("Hostname could not be resolved");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Could not send Paket");
				e.printStackTrace();
			}
		}
	}
}
