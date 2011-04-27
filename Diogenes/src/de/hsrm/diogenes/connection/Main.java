package de.hsrm.diogenes.connection;

import java.io.IOException;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.map.Map;

public class Main {

	public static void main(String[] args) throws WRPException, IOException {
		Client c = new Client();
		// settings for the simulator
//		c.run("localhost", 33333);
		// settings for the robbie
		c.run("10.18.72.254", 33333);
//		Map map = new Map(c);
//		map.readPacket();
		
//		c.moveTo(15, 10);
		//roboterbewegung?
		//c.turnLeft(23);
	}	
	
}
