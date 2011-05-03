package de.hsrm.diogenes.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The Class GuiController.
 */
public class GuiController {

	/**
	 * A GuiModel
	 * @uml.property  name="m"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private GuiModel m;
	
	/**
	 * Instantiates a new gui controller.
	 *
	 * @param m A guiModel
	 */
	public GuiController(GuiModel m){
		this.m = m;
		addListener();
	}
	
	
	/**
	 * Change status value.
	 *
	 * @param status the status of the Value
	 */
	public void changeStatusValue(final String status){
		/*this.m.getB1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m.changeStatus(status);
			}
		});*/
	}
	
	/**
	 * Adds the listener.
	 */
	public void addListener(){
		/*this.m.getB1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m.changeStatus("bluub");
			}
		});*/
	}
	
}
