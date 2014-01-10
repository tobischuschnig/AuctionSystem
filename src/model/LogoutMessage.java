package model;

public class LogoutMessage implements Message{
	private String name;
	
	public LogoutMessage(String name){
		this.name=name;
	}
	@Override
	public String getName() {
		return name;//null wurde vorher zurueckgeben -huang
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}