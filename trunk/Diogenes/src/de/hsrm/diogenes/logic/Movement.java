package de.hsrm.diogenes.logic;

import java.awt.Point;
import de.fhwiesbaden.webrobbie.wrp.WRPCmd;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCommand;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPRobotInfoPacket;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPStatusPacket;
import de.hsrm.diogenes.connection.Connection;

public class Movement {

	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Connection c;
	
	public Movement(Connection c) {
		this.c = c;
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
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, x, y));
		this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
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
		 * 		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.MOVE_FORWARD, d));
		 * 		this.c.getDiogenes().waitFor(WRPCmd.MOVE_FORWARD);
		 * here is a workaround using right-angled-triangle-geometry and the GOTO_XY-WRPCmd:
		 */
		// get robot's current position data
		int alpha = c.getLocation().getAngle();
		int x = c.getLocation().getX();
		int y = c.getLocation().getY();
		// calculating delta of x and y coordinates to target 							TODO: WRONG CALCULATION!
		int dy = (int) (Math.cos(alpha) * d);
		int dx = (int) (Math.sin(alpha) * d);
		System.out.println("angle:"+alpha+", x:"+x+", y:"+y+", dx:"+dx+", dy:"+dy);
		// target-position is current coordinates plus delta-coordinates
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, x+dx, y+dy));
		this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
	}
	
	/**
	 * Move backward.
	 *
	 * @param d The distance to travel
	 * @throws WRPException the wRP exception
	 */
	public void moveBackward(int d) throws WRPException {
		/* 
		 * the MOVE_BACKWARD WRPCmd is broken in WRP-3.0.2-beta.jar
		 * this is how it should work:
		 * 		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.MOVE_BACKWARD, d));
		 * 		this.c.getDiogenes().waitFor(WRPCmd.MOVE_BACKWARD);
		 */
		moveForward(-d);
	}
	
	/**
	 * Turn left.
	 *
	 * @param x The angle the robot turns
	 * @throws WRPException the wRP exception
	 */
	public void turnLeft(int x) throws WRPException {
		// TODO: doesn't work at all, WRPCmd broken
//		diogenes.requestStopMoving();
//		diogenes.waitFor(WRPCmd.STOP_MOVING);
//		System.out.println("start rotating");
//		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.ROTATE_LEFT, x));
//		this.c.getDiogenes().waitFor(WRPCmd.ROTATE_LEFT);
//		System.out.println("stopped rotating");
//		System.out.println("Turn Left um" +x);
	}
	
	/**
	 * Turn right.
	 *
	 * @param x The angle the robot turns
	 * @throws WRPException the wRP exception
	 */
	public void turnRight(int x) throws WRPException {
		// TODO: doesn't work at all, WRPCmd broken
//		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.ROTATE_RIGHT, x));
//		this.c.getDiogenes().waitFor(WRPCmd.ROTATE_RIGHT);
//		System.out.println("TurnRight um" +x);
	}
	
	/**
	 * Lets the robot wander around visiting the given points
	 * 
	 * @param p the points the robot has to visit
	 * @throws WRPException
	 */
	public void wander(Point... p) throws WRPException {
		// works fine
		for(Point pp : p){
			this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, (int)pp.getX(), (int)pp.getY()));
			this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		}
		System.out.println("All points visited O_o");
	}
	
	
}