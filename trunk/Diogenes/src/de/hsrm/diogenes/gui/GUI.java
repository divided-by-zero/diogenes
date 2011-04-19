package de.hsrm.diogenes.gui;

import de.hsrm.diogenes.common.ModelListener;


public class GUI implements ModelListener {
	
	private GuiModel m;
	
	public GUI(GuiModel m){
		
		this.m = m;
	//	this.m.addModelListener(this);
	}
	
	
	@Override
	public void modelChanged() {
	//	this.m.getStatusLabel().setText(this.m.getStatus());	
	//	this.m.getFrame().repaint();
	}
	
	
}
