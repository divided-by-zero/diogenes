package de.hsrm.diogenes.gui;

import java.io.IOException;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.map.Map;

/**
 * The Class GuiStarter, starts us the GUI.
 * @author Dirk Stanke
 */
public class GuiStarter {
	
	/** The gm. @uml.property  name="gm" @uml.associationEnd */
	private GuiModel gm;
	
	/** The gc. @uml.property  name="gc" @uml.associationEnd */
	private GuiController gc;
	
	/**
	 * Sets the controller.
	 *
	 * @param gc the new controller
	 */
	public void setGc(GuiController gc) {
		this.gc = gc;
	}

	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public GuiController getGc() {
		return gc;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws WRPException the wRP exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws WRPException, IOException, InterruptedException {
	
		GuiStarter gs = new GuiStarter();
		Connection c = new Connection("127.0.0.1", 33333);
		//Connection c = new Connection("10.18.72.254", 33333);
		gs.gm = new GuiModel(c, new Map(c));
		gs.setGc(new GuiController(gs.gm));
	}

}
