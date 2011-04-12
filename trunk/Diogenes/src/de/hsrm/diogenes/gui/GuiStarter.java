package de.hsrm.diogenes.gui;

public class GuiStarter {
	
	private GuiModel gm;
	private GuiController gc;
	
	public static void main(String[] args) {
	
		GuiStarter gs = new GuiStarter();
		
		gs.gm = new GuiModel();
		gs.gc = new GuiController(gs.gm);
	}

}
