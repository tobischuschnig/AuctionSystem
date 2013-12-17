package server;

public class ServerList {

	private Server server;
	
	public ServerList(Server server) {
		this.server = server;
	}
	
	public String list () {
		String out = "";
		for(int i=0;i< auction.size();i++) {
			String hilf;
			if (auction.get(i).getLastUser() == null) {
				hilf = "none";
			}
			else {
				hilf=auction.get(i).getLastUser().getName();
			}
			out+= "ID: "+ auction.get(i).getId()+ "Description: " +auction.get(i).getDescription()
					+ "Highestbid: " + auction.get(i).getHighestBid() + " from "+hilf;
		}
		return out;
		
	}

}
