package Client;

import java.util.Scanner;

/**
 * Repraesentiert den Client
 * @author Dominik Valka
 * @version 2013-12-10
 */
public class Client{
	String username;
	boolean loggedIn;
	String host;
	int tcpPort,udpPort;
	String eingabe;
	Scanner in;
	
	
	public Client(){
		eingabe="";
		loggedIn=false;
	}
	public static void main(String[] args) {
		Client c=new Client();
		c.run();
	}
	
	/**
	 * Die Methode leitet die Login Daten an den Server weiter
	 */
	public void login(){
		System.out.println("Login");
		if(loggedIn==false){
				//Login-Methode aufrufen
			loggedIn=true;
		}else{
			System.out.println("Already logged in, please logout first!");
		}
	}
	/**
	 * Die Methode holt eine Liste mit allen Auktionen vom Server
	 */
	public void list(){
		System.out.println("LISTE");
		//List-Methode aufrufen
	}
	/**
	 * Die Methode leitet das Gebot an den Server weiter
	 * @param id
	 * @param amount
	 */
	public void bid(int id,double amount){
		System.out.println("BID");
		//Bid-Methode aufrufen
	}
	public void create(Long duration,String description){
		System.out.println("CREATE");
		//Create-Methode aufrufen
	}
	/**
	 * Loggt den Benutzer aus
	 */
	public void logout(){
		if(loggedIn==true){
				//Logout-Methode aufrufen
			loggedIn=false;
		}else{
			System.out.println("Logout not possible, not logged in!");
		}	
	}
	/**
	 * Holt dauernd die Eingabe des Users und leitet sie an den Server
	 * ueber entsprechende Methoden weiter
	 */
	public void run() {
		
		while(true){
			in=new Scanner(System.in);
			System.out.print(">");
			String eingabe=in.nextLine().toLowerCase();
		
			if(eingabe.startsWith(" ")) eingabe=eingabe.substring(1);
			
			if(eingabe.startsWith("!list")){
				list();
			}else if(eingabe.startsWith("!bid")){
				String[] werte=eingabe.split(" ");
				if(werte.length==3){
					try{
						bid(Integer.parseInt(werte[1]),Double.parseDouble(werte[2]));
					}catch(NumberFormatException e){
						System.out.println("ID or Amount entered incorrect");
					}
				}else{
					System.out.println("Please enter ID and Amount like:\n!bid ID Amount");
				}
			}else if(eingabe.startsWith("!login")){
				String[] werte=eingabe.split(" ");
				if(werte.length==2){
						login();
				}else{
					System.out.println("Please enter User like:\n!login Username");
				}
			}else if(eingabe.startsWith("!create")){
				String[] werte=eingabe.split(" ");
				if(werte.length==3){
					try{
					create(Long.parseLong(werte[1]),werte[2]);
					}catch(NumberFormatException e){
						System.out.println("Duration was entered incorrect, not a number!");
					}
				}else{
				System.out.println("Please enter Create like:\n!create duration description");
			}
			}else if(eingabe.startsWith("!logout")){
				logout();
			}else if(eingabe.startsWith("!end")){
				in.close();
				System.exit(0);
			}
			else{
				System.out.println("Could not recognize input\nPlease try again");
			}
		}
	}
}