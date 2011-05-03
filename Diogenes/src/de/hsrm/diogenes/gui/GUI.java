package de.hsrm.diogenes.gui;

import de.hsrm.diogenes.common.ModelListener;

/**
 * The Class GUI.
 */
public class GUI implements ModelListener {
	
	/**
	 * A gui model variable
	 * @uml.property  name="m"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private GuiModel m;
	
	/**
	 * Instantiates a new gUI.
	 *
	 * @param m  A GuiModel
	 */
	public GUI(GuiModel m){
		
		this.m = m;
	//	this.m.addModelListener(this);
	}
	
	
	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.common.ModelListener#modelChanged()
	 */
	@Override
	public void modelChanged() {
	//	this.m.getStatusLabel().setText(this.m.getStatus());	
	//	this.m.getFrame().repaint();
	}
	
	
}
