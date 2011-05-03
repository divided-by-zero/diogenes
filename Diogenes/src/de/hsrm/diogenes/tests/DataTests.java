package de.hsrm.diogenes.tests;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import junit.framework.TestCase;

public class DataTests extends TestCase {

	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Connection c;
	
	public DataTests(Connection c) throws WRPException{
		this.c = new Connection("localhost", 33333);
	}
	public void testBlub(){
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
