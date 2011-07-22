package de.hsrm.diogenes.tests;

import junit.framework.TestCase;
import java.io.PrintStream;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;


public class ConnectionTest extends TestCase {
	
	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  
	 */
	Connection c;
	
	boolean fehlerMeldungGeworfen;
	
	/**
	 * @uml.property  name="output"
	 */
	String output = "";
	/**
	 * @uml.property  name="ps_tmp"
	 */
	PrintStream ps_tmp = System.out;
	/**
	 * @uml.property  name="ps"
	 */
	PrintStream ps = new PrintStream(System.out) {
		public void println(String x) {
			output += x;
		}
		public void print(String x) {
			output += x;
		}
	};
	
	/**
	 * @uml.property  name="ip"
	 */
	String ip = "10.18.72.254";
	/**
	 * @uml.property  name="port"
	 */
	int port = 33333;
	
	public void testConnection() throws WRPException {
		
		this.fehlerMeldungGeworfen = false;
		try {
			c = new Connection(ip, port);
			assertTrue(c.isConnected());
		} catch (Exception e) {
			this.fehlerMeldungGeworfen = true;
		}
		//if (!fehlerMeldungGeworfen) fail("Trotz falscher Übergabe wurde keine WRPException geworfen");
	}

	public void testRun() {
		
		boolean fehlerMeldungGeworfen = false;
		try {
			c.connect();
			assertTrue(c.isConnected());
		} catch (Exception e) {
			fehlerMeldungGeworfen = true;
		}
		if (!fehlerMeldungGeworfen) fail("Trotz falscher Übergabe wurde keine WRPException geworfen");
	}

	public void testHandleStatusPacket() {
		assertTrue(1==1);
	}

	public void testHandleVideoPacket() {
		
		boolean fehlerMeldungGeworfen = false;
		try {
			assertTrue(c.isCamData());
		} catch (Exception e) {
			fehlerMeldungGeworfen = true;
		}
		if (!fehlerMeldungGeworfen) fail("Trotz falscher Übergabe wurde keine IOException geworfen");
	}

	public void testHandlePathPlanningPacket() {
		System.setOut(ps);
		assertEquals(output, "Got PathPlanningPacket.");
		output = "";
	}

	public void testHandleSensorDataPacket() {
		System.setOut(ps);
		assertEquals(output, "Got SensorDataPacket.");
		output = "";
	}

	public void testHandleCameraPacket() {
		System.setOut(ps);
		assertEquals(output, "Got CameraPacket.");
		output = "";
	}

	public void testHandleCameraInfoPacket() {
		System.setOut(ps);
		assertEquals(output, "Got CameraInfoPacket.");
		output = "";
	}

	public void testHandleRequestFinished() {
		System.setOut(ps);
		assertEquals(output, "Got RequestFinishedPacket.");
		output = "";
	}

	public void testSetDiogenes() {
		fail("Not yet implemented");
	}

	public void testGetDiogenes() {
		fail("Not yet implemented");
	}

	public void testGetCameraData() {
		fail("Not yet implemented");
	}

	public void testSetCameraData() {
		fail("Not yet implemented");
	}

	public void testIsCamData() {
		fail("Not yet implemented");
	}

	public void testSetCamData() {
		fail("Not yet implemented");
	}

	public void testSetMove() {
		fail("Not yet implemented");
	}

	public void testGetMove() {
		fail("Not yet implemented");
	}

	public void testIsConnected() {
		fail("Not yet implemented");
	}

	public void testSetConnected() {
		fail("Not yet implemented");
	}

	public void testGetCameraPan() {
		fail("Not yet implemented");
	}

	public void testSetCameraPan() {
		fail("Not yet implemented");
	}

	public void testGetCameraTilt() {
		fail("Not yet implemented");
	}

	public void testSetCameraTilt() {
		fail("Not yet implemented");
	}

	public void testGetCameraZoom() {
		fail("Not yet implemented");
	}

	public void testSetCameraZoom() {
		fail("Not yet implemented");
	}

	public void testGetLocation() {
		fail("Not yet implemented");
	}

	public void testSetLocation() {
		fail("Not yet implemented");
	}

}
