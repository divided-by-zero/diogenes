package de.hsrm.diogenes.connection;

import java.io.IOException;
import de.fhwiesbaden.webrobbie.wrp.WRPException;

public class Main {

	public static void main(String[] args) throws WRPException, IOException {
//		Connection c = new Connection("10.18.72.254", 33333);
		new Connection("localhost", 33333);

	}	
	
}
