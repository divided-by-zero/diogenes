package de.hsrm.diogenes.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.fhwiesbaden.webrobbie.wrp.WRPCmd;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCommand;
import de.hsrm.diogenes.connection.Connection;

public class Movement {

	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Connection c;
	/**
	 * @uml.property  name="robiMoving"
	 */
	private boolean robiMoving;
	
	public Movement(Connection c) {
		this.c = c;
		this.robiMoving = false;
	}
	
	/**
	 * A method to move the robot to an absolute position
	 * 
	 * @param x The x-coordinate on the map
	 * @param y The y-coordinate on the map
	 * @throws WRPException 
	 */
	public void moveTo(int x, int y) throws WRPException {
		// works fine
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, x, y));
		this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		this.robiMoving = false;
	}
	
	/**
	 * Move forward.
	 *
	 * @param d The distance to travel 
	 * @throws WRPException the WRP exception
	 */
	public void moveForward(int d) throws WRPException {
		/* 
		 * the MOVE_FORWARD WRPCmd is broken in WRP-3.0.2-beta.jar
		 * this is how it should work: 
		 */
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.MOVE_FORWARD, d));
		this.c.getDiogenes().waitFor(WRPCmd.MOVE_FORWARD);
		this.robiMoving = false;
	}
	
	/**
	 * Move backward.
	 *
	 * @param d The distance to travel
	 * @throws WRPException the wRP exception
	 * @throws InterruptedException 
	 */
	public void moveBackward(int d) throws WRPException, InterruptedException {
		/* 
		 * the MOVE_BACKWARD WRPCmd is broken in WRP-3.0.2-beta.jar
		 * this is how it should work:
		 */
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.MOVE_BACKWARD, d));
		this.c.getDiogenes().waitFor(WRPCmd.MOVE_BACKWARD);
		this.robiMoving = false;
	}
	
	/**
	 * Turn left.
	 *
	 * @param x The angle the robot turns
	 * @throws WRPException the wRP exception
	 */
	public void turnLeft(int x) throws WRPException {
		/* 
		 * the TURN_LEFT WRPCmd is broken in WRP-3.0.2-beta.jar
		 * this is how it should work:
		 */
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.ROTATE_LEFT, x));
		this.c.getDiogenes().waitFor(WRPCmd.ROTATE_LEFT);
		this.robiMoving = false;
	}
	
	/**
	 * Turn right.
	 *
	 * @param x The angle the robot turns
	 * @throws WRPException the wRP exception
	 * @throws InterruptedException 
	 */
	public void turnRight(int x) throws WRPException, InterruptedException {
		/* 
		 * the TURN_RIGHT WRPCmd is broken in WRP-3.0.2-beta.jar
		 * this is how it should work:
		 */
		this.robiMoving = true;
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.ROTATE_RIGHT, x));
		this.c.getDiogenes().waitFor(WRPCmd.ROTATE_RIGHT);
		this.robiMoving = false;
	}
	
	/**
	 * Lets the robot wander around visiting the given points
	 * 
	 * @param p the points the robot has to visit
	 * @throws WRPException
	 */
	public void wander(Point... p) throws WRPException {
		// works fine
		this.robiMoving = true;
		
		for(Point pp : p) {
			this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, (int)pp.getX(), (int)pp.getY()));
			this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		}
		this.robiMoving = false;
	}
	
	public void wander(List<Point> points) throws WRPException {
		// works fine
		this.robiMoving = true;
		
		for(Point pp : points) {
			this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, (int)pp.getX(), (int)pp.getY()));
			this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		}
		this.c.setWanderFinished(true);
		
		/*for(Iterator<Point> it = points.iterator(); it.hasNext();){
			Point p = it.next();
			this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, (int)p.getX(), (int)p.getY()));
			this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		}*/
		
		this.robiMoving = false;
	}
	

	/**
	 * @return
	 * @uml.property  name="robiMoving"
	 */
	public boolean isRobiMoving() {
		return robiMoving;
	}

	/**
	 * @param robiMoving
	 * @uml.property  name="robiMoving"
	 */
	public void setRobiMoving(boolean robiMoving) {
		this.robiMoving = robiMoving;
	}
	
	
}