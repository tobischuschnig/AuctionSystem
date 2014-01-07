package model;

public class LoginMessage implements Message{
	private String name;
	private String adresse;
	private int tcpPort, udpPort;
	
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
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the tcpPort
	 */
	public int getTcpPort() {
		return tcpPort;
	}
	/**
	 * @param tcpPort the tcpPort to set
	 */
	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}
	/**
	 * @return the udpPort
	 */
	public int getUdpPort() {
		return udpPort;
	}
	/**
	 * @param udpPort the udpPort to set
	 */
	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}
}