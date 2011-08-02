package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Container for an image, text and a triggerBox
 * @author Philip Koch, Daniel Ernst
 */
public class ServerPacket {
	
	/** The image. */
	private ImageIcon image;
	
	/** Additional text to be displayed next to the image. */
	private String text;
	
	/** 
	 * The triggerBox describing the coordinates where the
	 * robot invokes the display of the image and the text 
	 */
	private Rectangle triggerBox;

	/**
	 * Instantiates a new ServerPacket.
	 * @param image The image.
	 * @param text The text to be displayed next to the image.
	 * @param triggerBox The triggerBox describing the coordinates where 
	 * 			the robot invokes the display of the image and the text
	 */
	public ServerPacket(ImageIcon image, String text, Rectangle triggerBox) {
		this.image 		= image;
		this.text		= text;
		this.triggerBox = triggerBox;
	}

	/**
	 * Returns the image.
	 * @return The image.
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	/**
	 * Returns the additional text to the image.
	 * @return The additional text to the image.
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Returns the triggerBox describing the coordinates
	 * where the robot invokes the display of the image 
	 * and the text.
	 * @return The triggerBox describing the coordinates
	 * 			where the robot invokes the display of the 
	 * 			image and the text.
	 */
	public Rectangle getTriggerBox() {
		return triggerBox;
	}
	
}
