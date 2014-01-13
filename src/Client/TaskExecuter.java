package Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import model.*;

/**
 * This class executes all the actions done by the user.
 * This actions are transmitted via the network to the server
 * 
 * @author Dominik Valka
 * @version 2014-01-07
 */
public class TaskExecuter {
	Client client;
	TCPConnector tcp;
	public TaskExecuter(Client c){
		client=c;
		tcp=c.getTcp();
	}
	public void login(String username,int tcpPort,int udpPort){
		//System.out.println("Login "+username);
		String ip="";
		try {
			ip=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("Could not resolve ip address!");
		}
		LoginMessage lm=new LoginMessage(username,ip,tcpPort,udpPort);
		tcp.sendMessage(lm);
	}
	public void logout(){
		//System.out.println("logout");
		LogoutMessage lom=new LogoutMessage(client.getUsername());
		tcp.sendMessage(lom);
	}
	public void bid(int id,double amount){
		//System.out.println("Bid "+id+" "+amount);
		BidMessage bm=new BidMessage(client.getUsername(),id,amount);
		tcp.sendMessage(bm);
	}
	public void create(Long duration,String description){
		//System.out.println("Create "+duration+" "+description);
		CreateMessage cm=new CreateMessage(client.getUsername(),description,duration);
		tcp.sendMessage(cm);
	}
	public void list(){
		//System.out.println("List");
		ListMessage lim;
//		if(client.getUsername()==null){
//			lim=new ListMessage(client.getUsername());
//		}else{
//			lim=new ListMessage("");
//		}
		lim=new ListMessage("");
		tcp.sendMessage(lim);
	}
}