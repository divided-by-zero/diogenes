package de.hsrm.diogenes.gui;

import java.awt.GridBagConstraints;

public class GridBagConstraintsFactory {

	public static GridBagConstraints create(int x, int y) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;

		return gbc;
	}

	public static GridBagConstraints create(int x, int y,int gridWidth, int gridHeight) {
		GridBagConstraints gbc = create(x, y);
		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;

		return gbc;
	}

	
}
