package connect;
/**
 * Returns Refereneces to Notifier Implementations
 * 
 * @author Daniel Reichmann
 * @version 2014-01-07
 */
public class NotifierFactory {
	/**
	 * Returns a UDPNotifier
	 */
	public static Notifier getUDPNotifer(){
		return new UDPNotifier();
	}
}
