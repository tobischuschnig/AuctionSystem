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
	static String host;
	static int tcpPort;
	static int udpPort;
	String eingabe;
	Scanner in;
	TaskExecuter t;
	
	
	public Client(){
		eingabe="";
		loggedIn=false;
		t=new TaskExecuter();
		username="";
	}
	public static void main(String[] args) {
		try{
		host=args[0];
		tcpPort=Integer.parseInt(args[1]);
		udpPort=Integer.parseInt(args[2]);
		Client c=new Client();
		c.run();
		}catch(NumberFormatException e){
			System.out.println("Port(s) is/are not numeric");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Holt dauernd die Eingabe des Users und leitet sie an den Server
	 * ueber entsprechende Methoden weiter
	 */
	public void run() {
		
		while(true){
			in=new Scanner(System.in);
			System.out.print(username+">");
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
				String[] werte=eingabe.split(" ");
				if(werte.length==3){
					try{
						t.bid(Integer.parseInt(werte[1]),Double.parseDouble(werte[2]));
					}catch(NumberFormatException e){
						System.out.println("ID or Amount entered incorrect");
					}
				}else{
					System.out.println("Please enter ID and Amount like:\n!bid ID Amount");
				}
				
				//If command is login
			}else if(eingabe.startsWith("!login")){
				String[] werte=original.split(" ");		//Original is used
				if(loggedIn==false){
					if(werte.length==2){
						t.login(werte[1]);
						username=werte[1];
						loggedIn=true;
					}else{
					System.out.println("Please enter User like:\n!login Username");
					}
				}else{
					System.out.println("Already logged in, logout first!");
				}
				//If command is create
			}else if(eingabe.startsWith("!create")){
				String[] werte=original.split(" ");		//Original is used
				String desc="";
				for(int i=2;i<=werte.length-1;i++){
					desc=desc+" "+werte[i];
				}
				t.create(Long.parseLong(werte[1]),desc);
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
					System.out.println("Logout not possible, not logged in!");
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
				System.out.println("Could not recognize input\nPlease try again");
			}
		}
	}
}