package de.hsrm.diogenes.tests;

import junit.framework.TestCase;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;


public class DataTests extends TestCase {

	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Connection c;
	
	public DataTests(Connection c) throws WRPException{
		this.setC(new Connection("localhost", 33333));
	}
	public void testBlub(){
		
	}
	public void setC(Connection c) {
		this.c = c;
	}
	public Connection getC() {
		return c;
	}
	public static void main(String[] args) {
		

	}

}
