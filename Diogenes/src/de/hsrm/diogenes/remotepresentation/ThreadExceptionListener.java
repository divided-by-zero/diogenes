package de.hsrm.diogenes.remotepresentation;

public class ThreadExceptionListener {

	private Throwable exception;
	
	public ThreadExceptionListener() {
		exception = null;
	}
	
	public void notifyException(Throwable t) {
		this.exception = t;
	}
	
	public void getException() throws Throwable {
		if (exception == null) {
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
