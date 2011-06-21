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
	/**
	 * @uml.property  name="robbie"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private WebRobbiePainterImpl robbie;
	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Connection c;
	/**
	 * @uml.property  name="img"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
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
	
	/**
	 * @return
	 * @uml.property  name="img"
	 */
	public ImageIcon getImg() {
		return img;
	}

	/**
	 * @param img
	 * @uml.property  name="img"
	 */
	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
