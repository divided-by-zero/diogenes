package de.hsrm.diogenes.tests;

import java.io.PrintStream;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.logic.Movement;
import junit.framework.TestCase;

public class MovementTest extends TestCase {
	
	Connection c;
	
	String output = "";
	PrintStream ps = new PrintStream(System.out) {
		public void println(String x) {
			output += x;
		}
		public void print(String x) {
			output += x;
		}
	};
	
	String ip = "10.18.72.254";
	int port = 33333;
	
	

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

	public void testMoveTo() {
		fail("Not yet implemented");
	}

	public void testMoveForward() {
		fail("Not yet implemented");
	}

	public void testMoveBackward() {
		fail("Not yet implemented");
	}

	public void testTurnLeft() {
		fail("Not yet implemented");
	}

	public void testTurnRight() {
		fail("Not yet implemented");
	}

	public void testWander() {
		fail("Not yet implemented");
	}

}
