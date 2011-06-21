package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.ImageIcon;

import de.hsrm.diogenes.connection.Location;

public class Packet implements Serializable, Presentable {

	private static final long serialVersionUID = 1L;
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
	
	public static void main(String[] args) {
		Packet p = new Packet(
				new ImageIcon("test1.jpg"), 
				"The answer is 42", 
//				new Rectangle(-1000, 1000, 1300, 1300));
				new Rectangle(2400, -2500, 1300, 2500));
		System.out.println(p.triggerBox.getMinX() +", "+ p.triggerBox.getMinY() +", "+ p.triggerBox.getMaxX() +", "+ p.triggerBox.getMaxY());
		System.out.println(p.surrounds(new Point(3400, -1500)));
	}
	
}
