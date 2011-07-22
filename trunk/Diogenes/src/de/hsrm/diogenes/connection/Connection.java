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

/**
 * The Connection class establishes a new connection to the robot,
 * adds itself as a packetlistener.
 */
public class Connection implements WRPPacketListener {
	
	/** Reference to the robot-connection. @uml.property  name="diogenes" @uml.associationEnd  multiplicity="(1 1)" */
	private WRPConnection diogenes;
	
	/** The ip adress of the robot. */
	private String ip;
	
	/** The port where the robot listens */
	private int port;
	
	/** Represents the camera. @uml.property  name="cameraData" @uml.associationEnd */
	private CameraData cameraData;
	
	/** Shows if any camera data is given. @uml.property  name="camData" */
	private boolean camData;
	
	/** The object for the robot movement. @uml.property  name="move" @uml.associationEnd  multiplicity="(1 1)" inverse="c:de.hsrm.diogenes.logic.Movement" */
	private Movement move;
	
	/** Shows wether we are connected or nit. @uml.property  name="connected" */
	private boolean connected;
	
	/** The camera pan given by the camera info packet. @uml.property  name="cameraPan" */
	private int cameraPan;
	
	/** The camera tilt given by the camera info packet. @uml.property  name="cameraTilt" */
	private int cameraTilt;
	
	/** The camera zoom given by the camera info packet. @uml.property  name="cameraZoom" */
	private int cameraZoom;
	/**
	 * The location will be sent by the robot every 100ms (initialized when connecting) to the client and saved in the local member location.
	 * @uml.property  name="location"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Location location;
	
	/** Shows if wander is started or not. @uml.property  name="startWander" */
	private boolean startWander;
	
	/** Shows if wander is finished or not. */
	private boolean wanderFinished;
	
	/**
	 * Creates an instance of connection.
	 *
	 * @param ip the ip
	 * @param port the port
	 * @throws WRPException the wRP exception
	 */
	public Connection(String ip, int port) throws WRPException {
		this.ip = ip;
		this.port = port;
		this.camData = false;
		connect();
		this.connected = true;
		this.startWander = false;
		this.wanderFinished = false;
		this.move =  new Movement(this);
		// initiate location values with starting position of WRPRobotInfoPackets.
		// after a successful run() the robot will send current positioning
		// values using WRPStatusPackets instead
		this.location = new Location(
							diogenes.getRobotInfo().getX(), 
							diogenes.getRobotInfo().getY(), 
							diogenes.getRobotInfo().getAngle());
	}
	
	/**
	 * Connects to the robot and adds itself as an
	 * packetlistener after this we can request data from the robot
	 *
	 * @throws WRPException the wRP exception
	 */
	public void connect() throws WRPException {
		try {
			this.diogenes = WRPConnection.connect(ip, port, ip, port);
			this.diogenes.addPacketListener(this);
			this.diogenes.sendCommand(new WRPCommand(WRPCmd.GET_CAMERA_INFO));
			this.diogenes.sendCommand(new WRPCommand(WRPCmd.GET_VIDEO));
			this.diogenes.sendCommand(new WRPCommand(WRPCmd.GET_STATUS_DATA));
		} catch (WRPException e) {
			System.err.println("Couldn't run diogenes:");
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.wrp.WRPPacketListener#handleStatusPacket(de.fhwiesbaden.webrobbie.wrp.packet.WRPStatusPacket)
	 */
	@Override
	public void handleStatusPacket(WRPStatusPacket packet) {
		location.setAll(packet.getX(), packet.getY(), packet.getAngle());
	}
	
	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.wrp.WRPPacketListener#handleVideoPacket(de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket)
	 */
	@Override
	public void handleVideoPacket(WRPVideoPacket packet) {
		this.camData = true;
		try {
			this.cameraData = new CameraData(packet, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.wrp.WRPPacketListener#handlePathPlanningPacket(de.fhwiesbaden.webrobbie.wrp.packet.WRPPathPlanningPacket)
	 */
	@Override
	public void handlePathPlanningPacket(WRPPathPlanningPacket packet) {
		System.out.println("Got PathPlanningPacket.");
	}

	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.wrp.WRPPacketListener#handleSensorDataPacket(de.fhwiesbaden.webrobbie.wrp.packet.WRPSensorDataPacket)
	 */
	@Override
	public void handleSensorDataPacket(WRPSensorDataPacket packet) {
		System.out.println("Got SensorDataPacket.");
	}

	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.wrp.WRPPacketListener#handleCameraPacket(de.fhwiesbaden.webrobbie.wrp.packet.WRPCameraPacket)
	 */
	@Override
	public void handleCameraPacket(WRPCameraPacket packet) {
		System.out.println("Got CameraPacket.");
		this.cameraPan = packet.getPan();
		this.cameraTilt = packet.getTilt();
		this.cameraZoom = packet.getZoom();
	}

	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.wrp.WRPPacketListener#handleCameraInfoPacket(de.fhwiesbaden.webrobbie.wrp.packet.WRPCameraInfoPacket)
	 */
	@Override
	public void handleCameraInfoPacket(WRPCameraInfoPacket arg0) {
		System.out.println("Got CameraInfoPacket.");
		System.out.println(arg0.getPan());
		System.out.println(arg0.getTilt());
		System.out.println(arg0.getZoom());
	}

	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.wrp.WRPPacketListener#handleRequestFinished(de.fhwiesbaden.webrobbie.wrp.packet.WRPFinishedPacket)
	 */
	@Override
	public void handleRequestFinished(WRPFinishedPacket arg0) {
		System.out.println("Got RequestFinishedPacket.");
	}

	/**
	 * Sets a WRPConnection-Object.
	 *
	 * @param diogenes The new WRPConnection-Object
	 * @uml.property  name="diogenes"
	 */
	public void setDiogenes(WRPConnection diogenes) {
		this.diogenes = diogenes;
	}

	/**
	 * Gets the WRPConnection-Object.
	 *
	 * @return The current WRPConnection-Object
	 * @uml.property  name="diogenes"
	 */
	public WRPConnection getDiogenes() {
		return this.diogenes;
	}
	
	/**
	 * Gets the iP.
	 *
	 * @return the iP
	 */
	public String getIP() {
		return ip;
	}
	
	/**
	 * Sets the iP.
	 *
	 * @param ip the new iP
	 */
	public void setIP(String ip) {
		this.ip = ip;
	}
	
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Gets the camera-data.
	 *
	 * @return The current camera-data
	 * @uml.property  name="cameraData"
	 */
	public CameraData getCameraData() {
		return this.cameraData;
	}

	/**
	 * Sets the camera-data.
	 *
	 * @param cameraData The new camera-data
	 * @uml.property  name="cameraData"
	 */
	public void setCameraData(CameraData cameraData) {
		this.cameraData = cameraData;
	}

	/**
	 * Checks if there has been any camera-data yet.
	 *
	 * @return true if there is data, false if not
	 * @uml.property  name="camData"
	 */
	public boolean isCamData() {
		return this.camData;
	}

	/**
	 * Sets the indicator if there has been any camera-data yet.
	 *
	 * @param camData the new cam data
	 * @uml.property  name="camData"
	 */
	public void setCamData(boolean camData) {
		this.camData = camData;
	}

	/**
	 * Sets the movement object.
	 *
	 * @param move the new move
	 * @uml.property  name="move"
	 */
	public void setMove(Movement move) {
		this.move = move;
	}

	/**
	 * Gets the movement object.
	 *
	 * @return the move
	 * @uml.property  name="move"
	 */
	public Movement getMove() {
		return this.move;
	}

	/**
	 * Checks if we are connected.
	 *
	 * @return true, if is connected
	 * @uml.property  name="connected"
	 */
	public boolean isConnected() {
		return this.connected;
	}

	/**
	 * Sets if we are connected.
	 *
	 * @param connected the new connected
	 * @uml.property  name="connected"
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * Gets the camera pan.
	 *
	 * @return the camera pan
	 * @uml.property  name="cameraPan"
	 */
	public int getCameraPan() {
		return this.cameraPan;
	}

	/**
	 * Sets the camera pan.
	 *
	 * @param cameraPan the new camera pan
	 * @uml.property  name="cameraPan"
	 */
	public void setCameraPan(int cameraPan) {
		this.cameraPan = cameraPan;
	}

	/**
	 * Gets the camera tilt.
	 *
	 * @return the camera tilt
	 * @uml.property  name="cameraTilt"
	 */
	public int getCameraTilt() {
		return cameraTilt;
	}

	/**
	 * Sets the camera tilt.
	 *
	 * @param cameraTilt the new camera tilt
	 * @uml.property  name="cameraTilt"
	 */
	public void setCameraTilt(int cameraTilt) {
		this.cameraTilt = cameraTilt;
	}

	/**
	 * Gets the camera zoom.
	 *
	 * @return the camera zoom
	 * @uml.property  name="cameraZoom"
	 */
	public int getCameraZoom() {
		return this.cameraZoom;
	}

	/**
	 * Sets the camera zoom.
	 *
	 * @param cameraZoom the new camera zoom
	 * @uml.property  name="cameraZoom"
	 */
	public void setCameraZoom(int cameraZoom) {
		this.cameraZoom = cameraZoom;
	}

	/**
	 * Returns the current robot's location values (x, y, angle). The location will be sent by the robot every 100ms (initialized when connecting) to the client and saved in the local member location.
	 * @return  An Object with x and y coordinates and the angle of the   robot within
	 * @uml.property  name="location"
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the start wander.
	 *
	 * @param startWander the new start wander
	 * @uml.property  name="startWander"
	 */
	public void setStartWander(boolean startWander) {
		this.startWander = startWander;
	}

	/**
	 * Checks if wander has been started.
	 *
	 * @return true, if is start wander
	 * @uml.property  name="startWander"
	 */
	public boolean isStartWander() {
		return startWander;
	}

	/**
	 * Checks if wander is finished.
	 *
	 * @return true, if wander is finished
	 */
	public boolean isWanderFinished() {
		return wanderFinished;
	}

	/**
	 * Sets if wander has been finished.
	 *
	 * @param wanderFinished the new wander finished
	 */
	public void setWanderFinished(boolean wanderFinished) {
		this.wanderFinished = wanderFinished;
	}
	
}
