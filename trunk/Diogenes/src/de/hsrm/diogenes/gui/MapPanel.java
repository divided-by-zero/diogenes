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



public class MapPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Image mapImg; 
	private Image robiImg;
	private Map map;
	private Connection c;
	private int width;
	private int height;
	
	public MapPanel(Map map, Connection c) throws InterruptedException{
		this.setMap(map);
		this.c = c;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.mapImg = map.getImg();
		this.robiImg = Toolkit.getDefaultToolkit().createImage(getClass().getResource("../img/robi.jpg"));
		checkSize();
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
	
	public void setMap(Map map) {
		this.map = map;
	}

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
