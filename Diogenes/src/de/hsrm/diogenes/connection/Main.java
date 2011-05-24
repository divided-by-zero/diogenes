package de.hsrm.diogenes.connection;

import java.io.IOException;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.logic.Movement;

public class Main {

	public static void main(String[] args) throws WRPException, IOException {
//		Connection c = new Connection("10.18.72.254", 33333);
		Connection c = new Connection("localhost", 33333);

//		Map map = new Map(c);
//	 	ConvertMap2Image cv = new ConvertMap2Image(map.getMap(), map.getMap().getMapWidth());
	 		
		Movement move = new Movement(c);
		move.moveTo(3000, -1500);
//		move.moveForward(1000);
//		move.wander(new Point(3367, -1747), new Point(6274, 1620), new Point(-334, 1570));

		c.getDiogenes().disconnect();
	}	
	
}
