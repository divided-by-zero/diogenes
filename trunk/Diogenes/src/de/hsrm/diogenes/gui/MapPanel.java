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
import javax.swing.JScrollPane;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.map.Map;



public class MapPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="mapImg"
	 */
	private Image mapImg; 
	/**
	 * @uml.property  name="robiImg"
	 */
	private Image robiImg;
	/**
	 * @uml.property  name="map"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Map map;
	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Connection c;
	/**
	 * @uml.property  name="width"
	 */
	private int width;
	/**
	 * @uml.property  name="height"
	 */
	private int height;
	
	public MapPanel(Map map, Connection c) throws InterruptedException{
		this.setMap(map);
		this.c = c;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.mapImg = map.getImg();
		this.robiImg = Toolkit.getDefaultToolkit().createImage(getClass().getResource("../img/robi.jpg"));
		checkSize();
		
		
		
		//scroller.setPreferredSize(new Dimension(50, 50));
	}
	
	public void checkSize() throws InterruptedException{
		
		MediaTracker mTracker = new MediaTracker(this);
		mTracker.addImage(mapImg,1);
		mTracker.waitForID(1);
		this.width = mapImg.getWidth(null);
		this.height = mapImg.getHeight(null);
		System.out.println("The width of image: " + width);
		System.out.println("The height of image: " + height);
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.width, this.height);
	}
	
	
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
	 * @param map
	 * @uml.property  name="map"
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * @return
	 * @uml.property  name="map"
	 */
	public Map getMap() {
		return map;
	}

	public static void main(String[] args) throws WRPException, IOException, InterruptedException {
		JFrame f = new JFrame();
		Connection c = new Connection("localhost", 33333);
		f.add((new MapPanel(new Map(c), c)));
		f.pack();
		f.setVisible(true);

	}

}
