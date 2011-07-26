package de.hsrm.diogenes.remotepresentation;

/**
 * An ExceptionListener-Object can hold an Exception given
 * to it (by a Class) and throws it to the one (usally another
 * Class) invoking the throwException()-method.
 * This has been written to make throwing Exceptions of Threads
 * to their "father-processes" possible, though the Exceptions 
 * have to be set and read manually unlike the standard java-
 * Exceptions.
 * Usage: An Object (e.g. a GUI) holds another Object which runs
 * in an own Thread. You use an ExceptionListenener so that
 * the Thread can give an Exception to the GUI.
 * The GUI holds the ExceptionListener-Object and also gives this 
 * listener (rather a reference) to the Thread . This way they are
 * accessing the exactly the same listener, and the Thread can "throw" 
 * its Exception to the listener and the GUI "reads" it and handles it.
 * An Object of this ExceptionListener can also be used as a lock
 * simultaneously. E.g. the Thread locks the lock on performing
 * something which can cause an exception and unlock it afterwards.
 * If you want to use this synchronization (which is recommended),
 * then your "fatherobject" (e.g. the GUI) needs to wait() for the
 * Thread to notify().
 */
public class ExceptionListener {

	/** The Exception to be notified to another Class. */
	private Throwable exception;
	
	/**
	 * Instantiates the ExceptionListener-Object with
	 * no Exception (null) at startup.
	 */
	public ExceptionListener() {
		exception = null;
	}
	
	/**
	 * Sets the local member to the Throwable t.
	 *
	 * @param t The Exception to be notified
	 */
	public void notifyException(Throwable t) {
		this.exception = t;
	}
	
	/**
	 * Throws an Exception if and resets the local 
	 * member to null afterwards.
	 * Doesn't do anything if there is no Exception
	 * at the time.
	 * @throws Throwable The Throwable which was set
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
