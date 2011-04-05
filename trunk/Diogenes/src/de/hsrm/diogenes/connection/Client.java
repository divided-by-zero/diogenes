package de.hsrm.diogenes.connection;

import de.fhwiesbaden.webrobbie.wrp.Diogenes;
import de.fhwiesbaden.webrobbie.wrp.DiogenesImpl;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.fhwiesbaden.webrobbie.wrp.WRPPacketListener;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCameraInfoPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCameraPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPFinishedPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPPathPlanningPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPSensorDataPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPStatusPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;

public class Client implements WRPPacketListener {

	/**
	 * Reference to the robot-connection
	 */
	private Diogenes diogenes;
	
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
			//diogenes.requestMove(20, 20);
			diogenes.requestStatus();
			diogenes.requestSensorData();
			diogenes.requestImage();
			
		
			diogenes.waitForAll();
			
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleRequestFinished(WRPFinishedPacket arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
