package Client;
/**
 * This class executes all the actions done by the user.
 * This actions are transmitted via the network to the server
 * 
 * @author Dominik Valka
 * @version 2014-01-07
 */
public class TaskExecuter {
	public void login(String username){
		System.out.println("Login "+username);
	}
	public void logout(){
		System.out.println("logout");
	}
	public void bid(int id,double amount){
		System.out.println("Bid "+id+" "+amount);
	}
	public void create(Long duration,String description){
		System.out.println("Create "+duration+" "+description);
	}
	public void list(){
		System.out.println("List");
	}
}