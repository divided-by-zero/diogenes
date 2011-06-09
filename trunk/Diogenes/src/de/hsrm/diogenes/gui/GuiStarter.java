package de.hsrm.diogenes.gui;

import java.io.IOException;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.map.Map;

public class GuiStarter {
	
	/**
	 * @uml.property  name="gm"
	 * @uml.associationEnd  
	 */
	private GuiModel gm;
	/**
	 * @uml.property  name="gc"
	 * @uml.associationEnd  
	 */
	private GuiController gc;
	
	public static void main(String[] args) throws WRPException, IOException, InterruptedException {
	
		GuiStarter gs = new GuiStarter();
		Connection c = new Connection("10.18.72.254", 33333);
		gs.gm = new GuiModel(c, new Map(c));
		gs.gc = new GuiController(gs.gm);
	}

}
