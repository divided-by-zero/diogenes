package de.hsrm.diogenes.gui;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Client;

public class GuiStarter {
	
	private GuiModel gm;
	private GuiController gc;
	
	public static void main(String[] args) throws WRPException {
	
		GuiStarter gs = new GuiStarter();
		
		gs.gm = new GuiModel(new Client());
		gs.gc = new GuiController(gs.gm);
	}

}
