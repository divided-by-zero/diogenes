package de.hsrm.diogenes.connection;

import java.io.IOException;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.logic.Movement;
import de.hsrm.diogenes.map.Map;

public class Main {

	public static void main(String[] args) throws WRPException, IOException {
		Connection c = new Connection();
		// settings for the simulator
		c.run("localhost", 33333);
		// settings for the robbie
		//c.run("10.18.72.254", 33333);
		Map map = new Map(c);
		Movement move = new Movement(c);
		
		//move.moveForward(5);
		//move.moveBackward(5);
		//move.turnLeft(5);
		//move.turnRight(5);
		move.moveTo(180, 70);
		c.getDiogenes().disconnect();
		//roboterbewegung?
		//c.turnLeft(23);
	}	
	
}
