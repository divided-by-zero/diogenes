package de.hsrm.diogenes.gui;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;

public class GuiStarter {
	
	private GuiModel gm;
	private GuiController gc;
	
	public static void main(String[] args) throws WRPException {
	
		GuiStarter gs = new GuiStarter();
		
		gs.gm = new GuiModel(new Connection("10.18.72.254", 33333));
		gs.gc = new GuiController(gs.gm);
	}

}
