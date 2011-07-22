package de.hsrm.diogenes.logic;

import java.awt.Point;
import java.util.List;

import de.fhwiesbaden.webrobbie.wrp.WRPCmd;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCommand;
import de.hsrm.diogenes.connection.Connection;

/**
 * @author Dirk Stanke
 * 
 * The Class Movement.
 * Everything related to the robot movement
 */
public class Movement {

	/** The connection object. @uml.property  name="c" @uml.associationEnd  multiplicity="(1 1)" */
	private Connection c;
	
	/** If the robot is moving or not. @uml.property  name="robiMoving" */
	private boolean robiMoving;
	
	/**
	 * Instantiates a new movement object.
	 *
	 * @param c the connection
	 */
	public Movement(Connection c) {
		this.c = c;
		this.robiMoving = false;
	}
	
	/**
	 * A method to move the robot to an absolute position.
	 *
	 * @param x The x-coordinate on the map
	 * @param y The y-coordinate on the map
	 * @throws WRPException the wRP exception
	 */
	public void moveTo(int x, int y) throws WRPException {
		
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, x, y));
		this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		this.robiMoving = false;
	}
	
	/**
	 * A method to move the robot forward.
	 *
	 * @param d The distance to travel 
	 * @throws WRPException the WRP exception
	 */
	public void moveForward(int d) throws WRPException {
	
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.MOVE_FORWARD, d));
		this.c.getDiogenes().waitFor(WRPCmd.MOVE_FORWARD);
		this.robiMoving = false;
	}
	
	/**
	 * A method to move the robot backward.
	 *
	 * @param d The distance to travel
	 * @throws WRPException the wRP exception
	 * @throws InterruptedException the interrupted exception
	 */
	public void moveBackward(int d) throws WRPException, InterruptedException {
		
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.MOVE_BACKWARD, d));
		this.c.getDiogenes().waitFor(WRPCmd.MOVE_BACKWARD);
		this.robiMoving = false;
	}
	
	/**
	 * A method to turn the robot left.
	 *
	 * @param x The angle the robot turns
	 * @throws WRPException the wRP exception
	 */
	public void turnLeft(int x) throws WRPException {
	
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.ROTATE_LEFT, x));
		this.c.getDiogenes().waitFor(WRPCmd.ROTATE_LEFT);
		this.robiMoving = false;
	}
	
	/**
	 * A method to turn the robot right.
	 *
	 * @param x The angle the robot turns
	 * @throws WRPException the wRP exception
	 * @throws InterruptedException the interrupted exception
	 */
	public void turnRight(int x) throws WRPException, InterruptedException {
		
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.ROTATE_RIGHT, x));
		this.c.getDiogenes().waitFor(WRPCmd.ROTATE_RIGHT);
		this.robiMoving = false;
	}
	
	/**
	 * Lets the robot wander around visiting the given points.
	 *
	 * @param p the points the robot has to visit
	 * @throws WRPException the wRP exception
	 */
	public void wander(Point... p) throws WRPException {
		
		this.robiMoving = true;
		
		for(Point pp : p) {
			this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, (int)pp.getX(), (int)pp.getY()));
			this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		}
		this.robiMoving = false;
	}
	
	/**
	 * Lets the robot wander around visiting the given points.
	 * Overloaded for a List of points
	 *
	 * @param points the points
	 * @throws WRPException the wRP exception
	 */
	public void wander(List<Point> points) throws WRPException {

		this.robiMoving = true;
		
		for(Point pp : points) {
			this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, (int)pp.getX(), (int)pp.getY()));
			this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		}
		this.c.setWanderFinished(true);		
		this.robiMoving = false;
	}
	

	/**
	 * Checks if the robi is moving.
	 *
	 * @return true, if is robi moving
	 * @uml.property  name="robiMoving"
	 */
	public boolean isRobiMoving() {
		return robiMoving;
	}

	/**
	 * Sets wether the robi is moving or not.
	 *
	 * @param robiMoving the new robi moving
	 * @uml.property  name="robiMoving"
	 */
	public void setRobiMoving(boolean robiMoving) {
		this.robiMoving = robiMoving;
	}
	
	
}