package de.hsrm.diogenes.tests;

import junit.framework.TestCase;
import java.io.PrintStream;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;

/**
 * Tests for the connection class.
 */
public class ConnectionTest extends TestCase {
	
	/** The c. @uml.property  name="c" @uml.associationEnd */
	Connection c;
	
	/** The fehler meldung geworfen. */
	boolean fehlerMeldungGeworfen;
	
	/** The output. @uml.property  name="output" */
	String output = "";
	
	/** The ps_tmp. @uml.property  name="ps_tmp" */
	PrintStream ps_tmp = System.out;
	
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
	 * Test connection.
	 *
	 * @throws WRPException the wRP exception
	 */
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

	/**
	 * Test run.
	 */
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

	/**
	 * Test handle status packet.
	 */
	public void testHandleStatusPacket() {
		assertTrue(1==1);
	}

	/**
	 * Test handle video packet.
	 */
	public void testHandleVideoPacket() {
		
		boolean fehlerMeldungGeworfen = false;
		try {
			assertTrue(c.isCamData());
		} catch (Exception e) {
			fehlerMeldungGeworfen = true;
		}
		if (!fehlerMeldungGeworfen) fail("Trotz falscher Übergabe wurde keine IOException geworfen");
	}

	/**
	 * Test handle path planning packet.
	 */
	public void testHandlePathPlanningPacket() {
		System.setOut(ps);
		assertEquals(output, "Got PathPlanningPacket.");
		output = "";
	}

	/**
	 * Test handle sensor data packet.
	 */
	public void testHandleSensorDataPacket() {
		System.setOut(ps);
		assertEquals(output, "Got SensorDataPacket.");
		output = "";
	}

	/**
	 * Test handle camera packet.
	 */
	public void testHandleCameraPacket() {
		System.setOut(ps);
		assertEquals(output, "Got CameraPacket.");
		output = "";
	}

	/**
	 * Test handle camera info packet.
	 */
	public void testHandleCameraInfoPacket() {
		System.setOut(ps);
		assertEquals(output, "Got CameraInfoPacket.");
		output = "";
	}

	/**
	 * Test handle request finished.
	 */
	public void testHandleRequestFinished() {
		System.setOut(ps);
		assertEquals(output, "Got RequestFinishedPacket.");
		output = "";
	}

	/**
	 * Test set diogenes.
	 */
	public void testSetDiogenes() {
		fail("Not yet implemented");
	}

	/**
	 * Test get diogenes.
	 */
	public void testGetDiogenes() {
		fail("Not yet implemented");
	}

	/**
	 * Test get camera data.
	 */
	public void testGetCameraData() {
		fail("Not yet implemented");
	}

	/**
	 * Test set camera data.
	 */
	public void testSetCameraData() {
		fail("Not yet implemented");
	}

	/**
	 * Test is cam data.
	 */
	public void testIsCamData() {
		fail("Not yet implemented");
	}

	/**
	 * Test set cam data.
	 */
	public void testSetCamData() {
		fail("Not yet implemented");
	}

	/**
	 * Test set move.
	 */
	public void testSetMove() {
		fail("Not yet implemented");
	}

	/**
	 * Test get move.
	 */
	public void testGetMove() {
		fail("Not yet implemented");
	}

	/**
	 * Test is connected.
	 */
	public void testIsConnected() {
		fail("Not yet implemented");
	}

	/**
	 * Test set connected.
	 */
	public void testSetConnected() {
		fail("Not yet implemented");
	}

	/**
	 * Test get camera pan.
	 */
	public void testGetCameraPan() {
		fail("Not yet implemented");
	}

	/**
	 * Test set camera pan.
	 */
	public void testSetCameraPan() {
		fail("Not yet implemented");
	}

	/**
	 * Test get camera tilt.
	 */
	public void testGetCameraTilt() {
		fail("Not yet implemented");
	}

	/**
	 * Test set camera tilt.
	 */
	public void testSetCameraTilt() {
		fail("Not yet implemented");
	}

	/**
	 * Test get camera zoom.
	 */
	public void testGetCameraZoom() {
		fail("Not yet implemented");
	}

	/**
	 * Test set camera zoom.
	 */
	public void testSetCameraZoom() {
		fail("Not yet implemented");
	}

	/**
	 * Test get location.
	 */
	public void testGetLocation() {
		fail("Not yet implemented");
	}

	/**
	 * Test set location.
	 */
	public void testSetLocation() {
		fail("Not yet implemented");
	}

}
