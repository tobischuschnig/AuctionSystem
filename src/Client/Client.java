package Client;

import java.util.Scanner;

/**
 * Repraesentiert den Client
 * @author Dominik Valka
 * @version 2013-12-10
 */
public class Client implements Runnable{
	String username;
	boolean loggedIn;
	String host;
	int tcpPort,udpPort;
	
	public static void main(String[] args) {
		Client c=new Client();
		c.run();
	}
	/**
	 * Die Methode leitet die Login Daten an den Server weiter
	 */
	public void login(){
		System.out.println("Login");
	}
	/**
	 * Die Methode holt eine Liste mit allen Auktionen vom Server
	 */
	public void list(){
		System.out.println("LISTE");
	}
	/**
	 * Die Methode leitet das Gebot an den Server weiter
	 * @param id
	 * @param amount
	 */
	public void bid(int id,double amount){
		System.out.println("BID");
	}
	/**
	 * Loggt den Benutzer aus
	 */
	public void logout(){
		System.out.println("Logout");
	}
	/**
	 * Holt dauernd die Eingabe des Users und leitet sie an den Server
	 * ueber entsprechende Methoden weiter
	 */
	@Override
	public void run() {
		while(true){
		Scanner in=new Scanner(System.in);
			System.out.print(">");
			String eingabe=in.nextLine();
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
						logout();
				}else{
					System.out.println("Please enter User like:\n!login Username");
				}
				login();
			}else if(eingabe.startsWith("!logout")){
				logout();
			}else{
				System.out.println("Could not recognize input\nPlease try again");
			}
		}
	}
}
