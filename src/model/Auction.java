package model;

import java.util.Date;

public class Auction {
	int id;
	double highestBid;
	User lastUser,owner;
	String description;
	Date deadline;
	
	public Auction(User owner,String description,String duration){
		this.owner=owner;
		this.description=description;
		deadline=new Date();
		deadline.setTime(deadline.getTime()+Long.parseLong(duration)*1000);
	}
	public boolean isActive(){
		return false;
	}
	public boolean bid(User user,double amount){
		return false;
	}
}
