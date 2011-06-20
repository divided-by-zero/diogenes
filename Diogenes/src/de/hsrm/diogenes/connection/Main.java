package de.hsrm.diogenes.connection;

import java.io.IOException;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.logic.Movement;
import de.hsrm.diogenes.map.ConvertMap2Image;
import de.hsrm.diogenes.map.Map;

public class Main {

	public static void main(String[] args) throws WRPException, IOException {
//		Connection c = new Connection("10.18.72.254", 33333);
		Connection c = new Connection("localhost", 33333);

		Map map = new Map(c);
	 	ConvertMap2Image cv = new ConvertMap2Image(map.getMap(),310);
	 		
		Movement move = new Movement(c);
		//move.moveTo(3000, -1500);
		//move.moveForward(50);
		//move.moveBackward(10);
		//move.turnLeft(50);
		//move.turnRight(50);
		//move.wander(new Point(3367, -1747), new Point(6274, 1620), new Point(-334, 1570));

		c.getDiogenes().disconnect();
	}	
	
}
