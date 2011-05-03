package de.hsrm.diogenes.logic;

import java.awt.Point;

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
	
	public Movement(Connection c){
		this.c = c;
	}
	
	/**
	 * A method to move the robot to an absolute position
	 * 
	 * @param x The x-coordinate on the map
	 * @param y The y-coordinate on the map
	 */
	
	public void moveTo(int x, int y) {
		try {
			int[] coordinates = {x,y};
			System.out.println("moving...");
			this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, coordinates));
			//this.getDiogenesconn().requestMove(x, y);
			this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
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
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.MOVE_FORWARD, x));
		this.c.getDiogenes().waitFor(WRPCmd.MOVE_FORWARD);
		System.out.println("Move forward um" +x);
	}
	
	/**
	 * Move backward.
	 *
	 * @param x The distance the robot walks
	 * @throws WRPException the wRP exception
	 */
	public void moveBackward(int x) throws WRPException{
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.MOVE_BACKWARD, x));
		this.c.getDiogenes().waitFor(WRPCmd.MOVE_BACKWARD);
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
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.ROTATE_LEFT, x));
		this.c.getDiogenes().waitFor(WRPCmd.ROTATE_LEFT);
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
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.ROTATE_RIGHT, x));
		this.c.getDiogenes().waitFor(WRPCmd.ROTATE_RIGHT);
		System.out.println("TurnRight um" +x);
	}
	
	/**
	 * Lets the robot wander around visiting the given points
	 * 
	 * @param p the points the robot has to visit
	 * @throws WRPException
	 */
	public void wander(Point... p) throws WRPException{
		
		for(Point pp : p){
			this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GOTO_XY, (int)pp.getX(), (int)pp.getY()));
			this.c.getDiogenes().waitFor(WRPCmd.GOTO_XY);
		}
		System.out.println("All points visited O_o");
		
	}
	
	
}
