package de.hsrm.diogenes.connection;

import de.fhwiesbaden.webrobbie.wrp.WRPException;

public class Main {

	public static void main(String[] args) throws WRPException {
		Client c = new Client();
		// settings for the simulator
		c.run("localhost", 33333);
		// settings for the robbie
//		c.run("10.18.72.254", 33333);
//		c.moveTo(112, -42);
		//roboterbewegung?
		c.turnLeft(23);
	}	
	
}
