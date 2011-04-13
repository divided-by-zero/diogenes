package de.hsrm.diogenes.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIModel1 extends JFrame {

	private JPanel mapPanel;
	private JPanel webcamPanel;
	private JPanel coordsPanel;
	private JPanel statusbarPanel;

	public GUIModel1() {
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());

		mapPanel = new JPanel();
		mapPanel.setBorder(BorderFactory.createTitledBorder("Hallo"));
		webcamPanel = new JPanel();
		webcamPanel.setBorder(BorderFactory.createTitledBorder("Picture"));
		coordsPanel = new JPanel();
		coordsPanel.setBorder(BorderFactory
				.createTitledBorder("coordination points of the Webrobi "));
		statusbarPanel = new JPanel();
		statusbarPanel.setBorder(BorderFactory.createTitledBorder("Statusbar"));

	add(mapPanel,GridBagConstraintsFactory.create(0, 0, 1, 2));
	add(webcamPanel,GridBagConstraintsFactory.create(1,0,1,0));
	add(coordsPanel,GridBagConstraintsFactory.create(1,1,1,1));
	add(statusbarPanel,GridBagConstraintsFactory.create(0, 2, 0, 1));
	
	
	}

}
