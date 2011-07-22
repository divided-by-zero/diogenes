package de.hsrm.diogenes.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.map.Map;


/**
 * The Class MapPanel, shows the map of the robot
 * as an image --> replaced by MapCanvas.
 */
public class MapPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The map img. @uml.property  name="mapImg" */
	private Image mapImg; 
	
	/** The robi img. @uml.property  name="robiImg" */
	private Image robiImg;
	
	/** The map. @uml.property  name="map" @uml.associationEnd  multiplicity="(1 1)" */
	private Map map;
	
	/** The c. @uml.property  name="c" @uml.associationEnd  multiplicity="(1 1)" */
	private Connection c;
	
	/** The width. @uml.property  name="width" */
	private int width;
	
	/** The height. @uml.property  name="height" */
	private int height;
	
	/**
	 * Instantiates a new map panel.
	 *
	 * @param map the map
	 * @param c the connection
	 * @throws InterruptedException the interrupted exception
	 */
	public MapPanel(Map map, Connection c) throws InterruptedException{
		this.setMap(map);
		this.setC(c);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.mapImg = map.getImg();
		this.robiImg = Toolkit.getDefaultToolkit().createImage(getClass().getResource("../img/robi.jpg"));
		checkSize();
		
		
		
		//scroller.setPreferredSize(new Dimension(50, 50));
	}
	
	/**
	 * Checks the size of the panel
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	public void checkSize() throws InterruptedException{
		
		MediaTracker mTracker = new MediaTracker(this);
		mTracker.addImage(mapImg,1);
		mTracker.waitForID(1);
		this.width = mapImg.getWidth(null);
		this.height = mapImg.getHeight(null);
		System.out.println("The width of image: " + width);
		System.out.println("The height of image: " + height);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.width, this.height);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(mapImg != null){
			g.drawImage(this.mapImg, 0, 0, this);
		}
		
		if(robiImg != null){
			g.drawImage(this.robiImg, 0, 0, this);
		}
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 * @uml.property  name="map"
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 * @uml.property  name="map"
	 */
	public Map getMap() {
		return map;
	}

	public void setC(Connection c) {
		this.c = c;
	}

	public Connection getC() {
		return c;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws WRPException the wRP exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws WRPException, IOException, InterruptedException {
		JFrame f = new JFrame();
		Connection c = new Connection("localhost", 33333);
		f.add((new MapPanel(new Map(c), c)));
		f.pack();
		f.setVisible(true);

	}

}
