package de.hsrm.diogenes.remotepresentation;

/**
 * An ExceptionListener-Object can hold an Exception given
 * to it (by a Class) and throws it to the one (usally another
 * Class) invoking the throwException()-method.
 * This has been written to make throwing Exceptions of Threads
 * to their father-processes possible, though it has to be set
 * and read manually.
 * Usage: An Object (e.g. a GUI) holds another Object which runs
 * in an own Thread. The GUI holds the ExceptionListener-Object
 * and also gives this listener to the Thread. This way they are
 * accessing the same listener, and the Thread can "throw" its
 * Exception to the listener and the GUI "reads" it and handles it.
 * You should use this listener as well as a lock, which the Thread
 * locks on perfoming something throwable and releases it afterwards.
 * Meanwhile the GUI can wait() for notify() of the Thread.
 * @author Daniel Ernst
 */
public class ExceptionListener {

	/**
	 * The Exception to be notified to another Class
	 * @uml.property  name="exception"
	 */
	private Throwable exception;
	
	/**
	 * Instantiates the ExceptionListener-Object with
	 * no Exception (null) at startup.
	 */
	public ExceptionListener() {
		exception = null;
	}
	
	/**
	 * Sets the local member to t
	 * @param t The Exception to be notified
	 */
	public void notifyException(Throwable t) {
		this.exception = t;
	}
	
	/**
	 * Throws an Exception if stored locally and resets
	 * the local member to null afterwards.
	 * Doesn't do anything if there is no Exception
	 * at the time.
	 * @throws Throwable Any Throwable saved
	 */
	public void throwException() throws Throwable {
		if (exception == null) {
			// don't throw anything
			return;
		} else {
			// save occurred exception
			Throwable result = exception;
			// reset local exception for next round
			exception = null;
			// throw the actual occurred exception
			throw result;
		}
	}
	
}
