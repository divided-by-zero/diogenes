package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.ImageIcon;

import de.hsrm.diogenes.connection.Location;

public class Packet implements Serializable, Presentable {

	private static final long serialVersionUID = -6026570658167708225L;
	/**
	 * @uml.property  name="image"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ImageIcon image;
	/**
	 * @uml.property  name="text"
	 */
	private String text;
	/**
	 * @uml.property  name="triggerBox"
	 */
	private Rectangle triggerBox;
	
	public Packet(ImageIcon icon, String text, Rectangle triggerBox) {
			this.image 		= icon;
			this.text		= text;
			this.triggerBox = triggerBox;
	}
	
	/**
	 * @return
	 * @uml.property  name="image"
	 */
	@Override
	public ImageIcon getImage() {
		return image;
	}
	
	@Override
	public String getDescriptionText() {
		return text;
	}
	
	@Override
	public String getAdditionalText() {
		return "Invoked between (" 
			+ (int)triggerBox.getMinX() + "," + (int)triggerBox.getMinY() 
			+ ") and (" 
			+ (int)triggerBox.getMaxX() + "," + (int)triggerBox.getMaxY() + ")";
	}
	
	@Override
	public boolean surrounds(Location l) {
		return surrounds(new Point(l.getX(), l.getY()));
	}
	
	@Override
	public boolean surrounds(Point p) {
		if (triggerBox.contains(p)) {
//			System.out.println("Packet: triggerBox " + triggerBox.toString() + " surrounds " + p.toString());
			return true;
		}
//		System.out.println("Packet: triggerBox " + triggerBox.toString() + " does not surround " + p.toString());
		return false;
	}
	
}
