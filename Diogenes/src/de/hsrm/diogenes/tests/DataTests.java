package de.hsrm.diogenes.tests;

import junit.framework.TestCase;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;

/**
 * Data related tests
 */
public class DataTests extends TestCase {

	/** The c. @uml.property  name="c" @uml.associationEnd  multiplicity="(1 1)" */
	private Connection c;
	
	/**
	 * Instantiates a new data tests.
	 *
	 * @param c the c
	 * @throws WRPException the wRP exception
	 */
	public DataTests(Connection c) throws WRPException{
		this.setC(new Connection("localhost", 33333));
	}
	
	/**
	 * Test blub.
	 */
	public void testBlub(){
		
	}
	
	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */
	public void setC(Connection c) {
		this.c = c;
	}
	
	/**
	 * Gets the c.
	 *
	 * @return the c
	 */
	public Connection getC() {
		return c;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		

	}

}
