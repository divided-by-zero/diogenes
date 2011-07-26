package de.hsrm.diogenes.tests;

import java.io.PrintStream;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.logic.Movement;
import junit.framework.TestCase;

/**
 * Movement related tests
 */
public class MovementTest extends TestCase {
	
	/** The connection object. @uml.property  name="c" @uml.associationEnd */
	Connection c;
	
	/** The output string. @uml.property  name="output" */
	String output = "";
	
	/** The ps. @uml.property  name="ps" */
	PrintStream ps = new PrintStream(System.out) {
		public void println(String x) {
			output += x;
		}
		public void print(String x) {
			output += x;
		}
	};
	
	/** The ip. @uml.property  name="ip" */
	String ip = "10.18.72.254";
	
	/** The port. @uml.property  name="port" */
	int port = 33333;
	
	

	/**
	 * Test movement.
	 *
	 * @throws WRPException the wRP exception
	 */
	public void testMovement() throws WRPException {
		
		System.setOut(ps);
		String[] str = {};
		c = new Connection(ip, port);
		Movement move = new Movement(c);
		
		boolean fehlerMeldungGeworfen = false;
		
		try {
			c.setMove(move);
			for (String s : str) {
				assertEquals(output, s);
			}
		} catch (Exception e) {
			fehlerMeldungGeworfen = true;
		}
		if (!fehlerMeldungGeworfen) fail("Trotz falscher Übergabe wurde keine WRPException geworfen");
		fail("Not yet implemented");
		
		output = "";
	}

	/**
	 * Test move to.
	 */
	public void testMoveTo() {
		fail("Not yet implemented");
	}

	/**
	 * Test move forward.
	 */
	public void testMoveForward() {
		fail("Not yet implemented");
	}

	/**
	 * Test move backward.
	 */
	public void testMoveBackward() {
		fail("Not yet implemented");
	}

	/**
	 * Test turn left.
	 */
	public void testTurnLeft() {
		fail("Not yet implemented");
	}

	/**
	 * Test turn right.
	 */
	public void testTurnRight() {
		fail("Not yet implemented");
	}

	/**
	 * Test wander.
	 */
	public void testWander() {
		fail("Not yet implemented");
	}

}
