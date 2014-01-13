package Client;

import java.util.Scanner;

/**
 * This class handles the client input and transfers the commands to the server
 * 
 * @author Dominik Valka
 * @version 2013-12-10
 */
public class Client{
	String username;
	boolean loggedIn;
	String host;
	int tcpPort;
	int udpPort;
	String eingabe;
	Scanner in;
	TaskExecuter t;
	TCPConnector tcp;
	CLI cli;
	NotificationReceiver nr;	
	
	public Client(String host,int tcpPort,int udpPort){
		eingabe="";
		loggedIn=false;
		username="";
		this.host=host;
		this.tcpPort=tcpPort;
		this.udpPort=udpPort;
		cli=new CLI();
		tcp=new TCPConnector(tcpPort, cli, this);
		t=new TaskExecuter(this);
		nr=new NotificationReceiver(this);
	}

	
	/**
	 * Holt dauernd die Eingabe des Users und leitet sie an den Server
	 * ueber entsprechende Methoden weiter
	 */
	public void run() {
		
		while(true){
			in=new Scanner(System.in);
			cli.outln(username+"> ");
			eingabe=in.nextLine();	//The current command saved as String
			
			if(eingabe.startsWith(" ")) eingabe=eingabe.substring(1);
			//If first char of command string is empty, it will be deleted
			
			String original=eingabe;	//Copy of Command, with original Format, lower/upper Case
			eingabe=eingabe.toLowerCase(); 	//To make any lower/upper case writing possible
			
				//If command is list
			if(eingabe.startsWith("!list")){
				t.list();
				
				//If command is bid
			}else if(eingabe.startsWith("!bid")){
				if(loggedIn==true){
					String[] werte=eingabe.split(" ");
					if(werte.length==3){
						try{
							t.bid(Integer.parseInt(werte[1]),Double.parseDouble(werte[2]));
						}catch(NumberFormatException e){
							cli.out("ID or Amount entered incorrect");
						}
					}else{
						cli.out("Please enter ID and Amount like:\n!bid ID Amount");
					}
				}else{
					cli.out("Currently not logged in\nPlease login first");
				}
				//If command is login
			}else if(eingabe.startsWith("!login")){
				String[] werte=original.split(" ");		//Original is used
				if(loggedIn==false){
					if(werte.length==2){
						t.login(werte[1],tcpPort,udpPort);
						//Wait for Server response and then: set Username und loggedIn=true
//						username=werte[1];
//						loggedIn=true;
					}else{
					cli.out("Please enter User like:\n!login Username");
					}
				}else{
					cli.out("Already logged in, logout first!");
				}
				//If command is create
			}else if(eingabe.startsWith("!create")){
				if(loggedIn==true){
				String[] werte=original.split(" ");		//Original is used
				String desc="";
				for(int i=2;i<=werte.length-1;i++){
					desc=desc+" "+werte[i];
				}
				if(desc.startsWith(" ")) desc=desc.substring(1);
				t.create(Long.parseLong(werte[1]),desc);
				}else{
					cli.out("Currently not logged in\nPlease login first");
				}
//				if(werte.length==3){
//					try{
//					t.create(Long.parseLong(werte[1]),werte[2]);
//					}catch(NumberFormatException e){
//						System.out.println("Duration was entered incorrect, not a number!");
//					}
//				}else{
//				System.out.println("Please enter Create like:\n!create duration description");
//			}
				
				//If command is logout
			}else if(eingabe.startsWith("!logout")){
				if(loggedIn==true){
					t.logout();
					loggedIn=false;
					username="";
				}else{
					cli.out("Logout not possible, not logged in!");
				}
				
				//If command is end
			}else if(eingabe.startsWith("!end")){
				in.close();	//Free ressources
				break;		//Exits the loop
				//System.exit(0);
				//TcpConnector isActive:false, NotificationReceiver isActive:false
			}
				//If command is not recognized, another try will be granted
			else{
				cli.out("Could not recognize input\nPlease try again");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public TCPConnector getTcp() {
		System.out.println("getMethode");
		return tcp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public int getUdpPort() {
		return udpPort;
	}
	public CLI getCli() {
		return cli;
	}
	public String getHost() {
		return host;
	}
	public int getTcpPort() {
		return tcpPort;
	}
}