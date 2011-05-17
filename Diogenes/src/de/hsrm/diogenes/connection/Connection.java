package de.hsrm.diogenes.connection;

import java.io.IOException;
import de.fhwiesbaden.webrobbie.wrp.WRPCmd;
import de.fhwiesbaden.webrobbie.wrp.WRPConnection;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.fhwiesbaden.webrobbie.wrp.WRPPacketListener;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCameraInfoPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCameraPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCommand;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPFinishedPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPPathPlanningPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPSensorDataPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPStatusPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;
import de.hsrm.diogenes.camera.CameraData;
import de.hsrm.diogenes.logic.Movement;

public class Connection implements WRPPacketListener {

	/**
	 * Reference to the robot-connection
	 * @uml.property  name="diogenes"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private WRPConnection diogenes;
	
	/**
	 * Represents the camera
	 * @uml.property  name="cameraData"
	 * @uml.associationEnd  
	 */
	private CameraData cameraData;
	
	/**
	 * Shows if any camera data is given
	 * @uml.property  name="camData"
	 */
	private boolean camData;
	
	private Movement move;
	
	private boolean connected;
	
	private int cameraPan;
	
	private int cameraTilt;
	
	private int cameraZoom;
	
	private WRPStatusPacket location;
	/**
	 * Creates an instance of the client
	 * @throws WRPException 
	 */
	public Connection(String ip, int port) throws WRPException {
		this.camData = false;
		run(ip, port);
		connected = true;
		this.move =  new Movement(this);
	}
	
	/**
	 * Runs the robot or the simulator
	 * @param ip The IP of the robot or the simulator
	 * @param port The open port on the robot or the simulator
	 * @throws WRPException 
	 */
	public void run(String ip, int port) throws WRPException {
		try {
			this.diogenes = WRPConnection.connect(ip,port,ip,port);
			this.diogenes.addPacketListener(this);
			this.diogenes.sendCommand(new WRPCommand(WRPCmd.GET_CAMERA_INFO));
			this.diogenes.sendCommand(new WRPCommand(WRPCmd.GET_VIDEO));
			//this.diogenes.waitFor(WRPCmd.GET_VIDEO);
		} catch (WRPException e) {
			System.err.println("Couldn't run diogenes:");
			e.printStackTrace();
		}
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
	
	@Override
	public void handleStatusPacket(WRPStatusPacket packet) {
		System.out.println("RobotInfo:"
				+ "  X="     + packet.getX() 
				+ "  Y="     + packet.getY() 
				+ "  Angle=" + packet.getAngle());
	}	
	
	@Override
	public void handleVideoPacket(WRPVideoPacket packet) {
		this.camData = true;
		try {
			this.cameraData = new CameraData(packet, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		this.cameraPan = packet.getPan();
		this.cameraTilt = packet.getTilt();
		this.cameraZoom = packet.getZoom();
	}

	@Override
	public void handleCameraInfoPacket(WRPCameraInfoPacket arg0) {
		System.out.println("Got CameraInfoPacket.");
		System.out.println(arg0.getPan());
		System.out.println(arg0.getTilt());
		System.out.println(arg0.getZoom());
	}

	@Override
	public void handleRequestFinished(WRPFinishedPacket arg0) {
		System.out.println("Got RequestFinishedPacket.");
	}

	/**
	 * Sets a WRPConnection-Object
	 * @param diogenes The new WRPConnection-Object
	 * @uml.property  name="diogenes"
	 */
	public void setDiogenes(WRPConnection diogenes) {
		this.diogenes = diogenes;
	}

	/**
	 * Gets the WRPConnection-Object
	 * @return The current WRPConnection-Object
	 * @uml.property  name="diogenes"
	 */
	public WRPConnection getDiogenes() {
		return this.diogenes;
	}

	/**
	 * Gets the camera-data
	 * @return The current camera-data
	 * @uml.property  name="cameraData"
	 */
	public CameraData getCameraData() {
		return cameraData;
	}

	/**
	 * Sets the camera-data
	 * @param cameraData The new camera-data
	 * @uml.property  name="cameraData"
	 */
	public void setCameraData(CameraData cameraData) {
		this.cameraData = cameraData;
	}

	/**
	 * Checks if there has been any camera-data yet
	 * @return true if there is data, false if not
	 * @uml.property  name="camData"
	 */
	public boolean isCamData() {
		return this.camData;
	}

	/**
	 * Sets the indicator if there has been any camera-data yet
	 * @param camData
	 * @uml.property  name="camData"
	 */
	public void setCamData(boolean camData) {
		this.camData = camData;
	}

	public void setMove(Movement move) {
		this.move = move;
	}

	public Movement getMove() {
		return move;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public int getCameraPan() {
		return cameraPan;
	}

	public void setCameraPan(int cameraPan) {
		this.cameraPan = cameraPan;
	}

	public int getCameraTilt() {
		return cameraTilt;
	}

	public void setCameraTilt(int cameraTilt) {
		this.cameraTilt = cameraTilt;
	}

	public int getCameraZoom() {
		return cameraZoom;
	}

	public void setCameraZoom(int cameraZoom) {
		this.cameraZoom = cameraZoom;
	}

	public WRPStatusPacket getLocation() {
		return location;
	}

	public void setLocation(WRPStatusPacket location) {
		this.location = location;
	}
	
}
