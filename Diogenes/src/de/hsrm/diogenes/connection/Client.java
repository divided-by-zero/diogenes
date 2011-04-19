package de.hsrm.diogenes.connection;

import de.fhwiesbaden.webrobbie.wrp.Diogenes;
import de.fhwiesbaden.webrobbie.wrp.DiogenesImpl;
import de.fhwiesbaden.webrobbie.wrp.WRPCmd;
import de.fhwiesbaden.webrobbie.wrp.WRPConnection;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.fhwiesbaden.webrobbie.wrp.WRPPacketListener;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCameraInfoPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCameraPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCommand;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPFinishedPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPPathPlanningPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPSensorDataPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPStatusPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;

public class Client implements WRPPacketListener {

	/**
	 * Reference to the robot-connection
	 */
	private Diogenes diogenes;
//	private WRPConnection connection;
	
	/**
	 * Creates an instance of the client
	 */
	public Client() {}
	
	/**
	 * Runs the robot
	 * 
	 * @param ip The IP of the robot
	 * @param port The open port on the robot
	 */
	public void run(String ip, int port) {
		try {
			diogenes = DiogenesImpl.connect(ip, port);
//			connection = WRPConnection.connect("localhost", 33333, "localhost", 33333);
		} catch (WRPException e) {
			System.err.println("Couldn't run diogenes:");
			e.printStackTrace();
		}
	}

	@Override
	public void handleStatusPacket(WRPStatusPacket packet) {
		System.out.println("RobotInfo:"
				+ "  X="     + packet.getX() 
				+ "  Y="     + packet.getY() 
				+ "  Angle=" + packet.getAngle());
	}
	
//	/**
//	 * Returns information about the robot
//	 * 
//	 * @return A string of values like coordinates etc.
//	 */
//	public String getRobotInfo() {
//		return "X = " + connection.getRobotInfo().getX() + 
//					", Y = " + connection.getRobotInfo().getY() + 
//					", Width = " + connection.getRobotInfo().getWidth() + 
//					", Length = " + connection.getRobotInfo().getLength() +
//					", Angle = " + connection.getRobotInfo().getAngle();
//	}
	
	/**
	 * A method to move the robot to an absolute position
	 * 
	 * @param x The x-coordinate on the map
	 * @param y The y-coordinate on the map
	 */
	public void moveTo(int x, int y) {
		try {
			System.out.println("moving...");
			diogenes.requestMove(x, y);
			diogenes.waitFor(WRPCmd.GOTO_XY);
			System.out.println("...moving finished");
		} catch (WRPException e) {
			System.err.println("Couldn't move Diogenes to (" + x + "," + y + "):");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Move forward.
	 *
	 * @param x 
	 * @throws WRPException the wRP exception
	 */
	public void moveForward(int x) throws WRPException{
		diogenes.requestMoveForward(x);
		diogenes.waitFor(WRPCmd.MOVE_FORWARD);
		System.out.println("Move forward um" +x);
	}
	
	/**
	 * Move backward.
	 *
	 * @param x The distance the robot walks
	 * @throws WRPException the wRP exception
	 */
	public void moveBackward(int x) throws WRPException{
		diogenes.requestMoveBackward(x);
		diogenes.waitFor(WRPCmd.MOVE_BACKWARD);
		System.out.println("Move backward um" +x);
	}
	
	/**
	 * Turn left.
	 *
	 * @param x The angle the robot turns
	 * @throws WRPException the wRP exception
	 */
	public void turnLeft(int x) throws WRPException{
//		diogenes.requestStopMoving();
//		diogenes.waitFor(WRPCmd.STOP_MOVING);
		System.out.println("start rotating");
		diogenes.requestRotateLeft(x);
		diogenes.waitFor(WRPCmd.ROTATE_LEFT);
		System.out.println("stopped rotating");
//		System.out.println("Turn Left um" +x);
		
	}
	
	/**
	 * Turn right.
	 *
	 * @param x The angle the robot turns
	 * @throws WRPException the wRP exception
	 */
	public void turnRight(int x) throws WRPException{
		diogenes.requestRotateRight(x);
		diogenes.waitFor(WRPCmd.ROTATE_RIGHT);
		System.out.println("TurnRight um" +x);
	}
	
	@Override
	public void handleVideoPacket(WRPVideoPacket packet) {
		System.out.println("Got VideoPacket.");
	}

	@Override
	public void handlePathPlanningPacket(WRPPathPlanningPacket packet) {
		System.out.println("Got PathPlanningPacket.");
	}

	@Override
	public void handleSensorDataPacket(WRPSensorDataPacket packet) {
		System.out.println("Got SensorDataPacket.");
	}

	@Override
	public void handleCameraPacket(WRPCameraPacket packet) {
		System.out.println("Got CameraPacket.");
	}

	@Override
	public void handleCameraInfoPacket(WRPCameraInfoPacket arg0) {
		System.out.println("Got CameraInfoPacket.");
	}

	@Override
	public void handleRequestFinished(WRPFinishedPacket arg0) {
		System.out.println("Got RequestFinishedPacket.");
	}
	
}
