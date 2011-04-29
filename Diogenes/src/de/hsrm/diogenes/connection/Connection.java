package de.hsrm.diogenes.connection;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
import de.fhwiesbaden.webrobbie.wrp.packet.WRPPathPlanningPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPSensorDataPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPStatusPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;
import de.hsrm.diogenes.camera.CameraData;

public class Connection implements WRPPacketListener {

	/**
	 * Reference to the robot-connection
	 */
	//private Diogenes diogenes;
	private WRPConnection diogenes;
	private Diogenes diogenesconn;
	private CameraData cameraData;
	/**
	 * Creates an instance of the client
	 * @throws WRPException 
	 */
	public Connection() throws WRPException {
		//run("10.18.72.254", 33333);
	}
	
	/**
	 * Runs the robot
	 * 
	 * @param ip The IP of the robot
	 * @param port The open port on the robot
	 * @throws WRPException 
	 */
	public void run(String ip, int port) throws WRPException {
		try {
			//this.setDiogenes(DiogenesImpl.connect(ip, port));
			this.diogenes = WRPConnection.connect(ip,port,ip,port);
			this.diogenes.addPacketListener(this);
			//this.diogenes.sendCommand(new WRPCommand(WRPCmd.GET_VIDEO));
			//this.diogenes.wait
		} catch (WRPException e) {
			System.err.println("Couldn't run diogenes:");
			e.printStackTrace();
		}
		/*finally{
			this.diogenes.disconnect();
		
		}*/
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
	
	
	
	@Override
	public void handleVideoPacket(WRPVideoPacket packet) {
		this.cameraData = new CameraData(packet);
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

	public void setDiogenes(WRPConnection diogenes) {
		this.diogenes = diogenes;
	}

	public WRPConnection getDiogenes() {
		return diogenes;
	}

	public Diogenes getDiogenesconn() {
		return diogenesconn;
	}

	public void setDiogenesconn(Diogenes diogenesconn) {
		this.diogenesconn = diogenesconn;
	}

	public CameraData getCameraData() {
		return cameraData;
	}

	public void setCameraData(CameraData cameraData) {
		this.cameraData = cameraData;
	}

	
	
}
