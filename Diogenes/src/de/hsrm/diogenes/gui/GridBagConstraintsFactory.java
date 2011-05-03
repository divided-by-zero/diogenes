package de.hsrm.diogenes.gui;

import java.awt.GridBagConstraints;

/**
 * A factory for creating GridBagConstraints objects.
 */
public class GridBagConstraintsFactory {

	/**
	 * Creates the grid
	 *
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @return the grid bag constraints
	 */
	public static GridBagConstraints create(int x, int y) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;

		return gbc;
	}

	/**
	 * Creates the grid
	 *
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @param gridWidth the width of the grid
	 * @param gridHeight the height of the grid
	 * @return the grid bag constraints
	 */
	public static GridBagConstraints create(int x, int y,int gridWidth, int gridHeight) {
		GridBagConstraints gbc = create(x, y);
		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;

		return gbc;
	}

	
}
