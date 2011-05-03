package de.hsrm.diogenes.connection;

import java.awt.Point;
import java.io.IOException;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.logic.Movement;
import de.hsrm.diogenes.map.Map;

public class Main {

	public static void main(String[] args) throws WRPException, IOException {
		//Connection c = new Connection("10.18.72.254", 33333);
		Connection c = new Connection("localhost", 33333);
		// settings for the simulator
		//c.run("localhost", 33333);
		// settings for the robbie
	 	Map map = new Map(c);
		Movement move = new Movement(c);
		
		//move.moveForward(50);
		//move.moveBackward(5);
		//move.turnLeft(5);
		//move.turnRight(5);
		move.wander(new Point(3367, -1747), new Point(6274, 1620), new Point(-334, 1570));
		
		//move.moveTo(-334, 1570);
		c.getDiogenes().disconnect();
		//roboterbewegung?
		//c.turnLeft(23);
	}	
	
}