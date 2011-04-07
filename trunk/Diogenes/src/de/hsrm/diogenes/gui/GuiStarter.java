package de.hsrm.diogenes.gui;

public class GuiStarter {
	
	private GuiModel gm;	
	
	public static void main(String[] args) {
	
		GuiStarter gs = new GuiStarter();
		gs.gm = new GuiModel();
		
		new GUI(gs.gm).makeGUI();

	}

}
