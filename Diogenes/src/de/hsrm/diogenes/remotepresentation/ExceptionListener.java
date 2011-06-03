package de.hsrm.diogenes.remotepresentation;

public class ExceptionListener {

	private Throwable exception;
	
	public ExceptionListener() {
		exception = null;
	}
	
	public void notifyException(Throwable t) {
		this.exception = t;
	}
	
	public void throwException() throws Throwable {
		if (exception == null) {
			System.out.println("ExceptionListener: throwing nothing");
			return;
		} else {
			// save occurred exception
			Throwable result = exception;
			// reset local exception for next round
			exception = null;
			// throw the actual occurred exception
			System.out.println("ExceptionListener: throwing Exception: " + result.getMessage());
			throw result;
		}
	}
	
}
