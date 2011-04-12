package de.hsrm.diogenes.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiController {

	private GuiModel m;
	
	public GuiController(GuiModel m){
		this.m = m;
		addListener();
		
	}
	
	
	public void changeStatusValue(final String status){
		m.getB1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m.changeStatus(status);
			}
		});
		
	}
	
	public void addListener(){
		m.getB1().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m.changeStatus("bluub");
			}
		});
		
	}
	
}
