package util;

/**
 * Interface to demonstrate the Observer pattern.
 * @author srollins
 *
 */
public interface Observer {

	/**
	 * Receive an update that state has changed.
	 * @param o
	 */
	public void update(Object o);
	
}
