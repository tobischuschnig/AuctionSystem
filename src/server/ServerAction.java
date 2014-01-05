package server;

import model.Message;
import server.Server;;

/**
 * Interface fuer alle Serverfunktionalitaeten
 * @author Tobias
 * @version 2013-01-05 
 */
public interface ServerAction {
	/**
	 * In dieser Methode werden die jeweiligen Server Operationen durchgefuert
	 * @param message die Message mit allen Anweisungen
	 * @param server der Server auf dem gearbeitet werden soll
	 * @return das Ergebnis der Operation
	 */
	public String doOperation(Message message,Server server);
}
