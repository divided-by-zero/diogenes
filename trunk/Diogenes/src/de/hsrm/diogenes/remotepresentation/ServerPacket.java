package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class ServerPacket {
	
	private ImageIcon image;
	
	private String text;
	
	private Rectangle triggerBox;

	public ServerPacket(ImageIcon image, String text, Rectangle triggerBox) {
		this.image 		= image;
		this.text		= text;
		this.triggerBox = triggerBox;
	}

	public ImageIcon getImage() {
		return image;
	}
	
	public String getText() {
		return text;
	}
	
	public Rectangle getTriggerBox() {
		return triggerBox;
	}
	
}
