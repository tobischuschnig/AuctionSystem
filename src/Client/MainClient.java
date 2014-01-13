package Client;

public class MainClient {

	public static void main(String[] args) {
		try{
		String host=args[0];
		int tcpPort=Integer.parseInt(args[1]);
		int udpPort=Integer.parseInt(args[2]);
		Client c=new Client(host,tcpPort,udpPort);
//		Thread not=new Thread(nr);
//		not.run();
		c.run();
		}catch(NumberFormatException e){
			System.out.println("Port(s) is/are not numeric");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
