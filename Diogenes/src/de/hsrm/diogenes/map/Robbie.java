package de.hsrm.diogenes.map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import de.fhwiesbaden.webrobbie.clientutil.awt.WebRobbiePainterImpl;
import de.hsrm.diogenes.connection.Connection;

public class Robbie{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WebRobbiePainterImpl robbie;
	private Connection c;
	private ImageIcon img;
	
	public Robbie(Connection c) throws IOException{
		this.c = c;
		this.robbie = new WebRobbiePainterImpl(new ConvertMap2Image(new Map(c).getMap(), 340));
		
		
			this.img = new ImageIcon(this.getClass().getResource("../img/robi.jpg"));
			
		
		
		doSomething();
	}
	
	public void doSomething() throws IOException{
		
		
			robbie.setPosition(this.c.getLocation().getX(), this.c.getLocation().getY(), this.c.getLocation().getAngle());
		
	}
	
	public void paintMeto(Graphics g){
		
		this.img.paintIcon(null, g, 0, 0);
	}
	
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
