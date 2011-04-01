package de.hsrm.diogenes.connection;

import de.fhwiesbaden.webrobbie.wrp.WRPException;

public class Main {

	public static void main(String[] args) throws WRPException {
		// settings for the simulator
		new Client().run("localhost", 33333);
	}	
	
}
