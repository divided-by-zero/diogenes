package de.hsrm.diogenes.gui;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.diogenes.common.ModelListener;

public class GuiModel {
	
	private List<ModelListener> listener;
	
	public GuiModel(){
		this.listener = new ArrayList<ModelListener>();
	}
	
	public void addModelListener(ModelListener l){
		listener.add(l);
	}
	
	
	public void fireModelChanged(){
		for(ModelListener ml : listener){
			ml.modelChanged();
		}
	}
	

}
