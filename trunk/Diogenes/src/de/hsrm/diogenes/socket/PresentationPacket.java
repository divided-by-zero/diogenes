package de.hsrm.diogenes.socket;


import java.io.Serializable;
import javax.swing.*;

public class PresentationPacket implements Serializable {

	/**
	 * Serialization needed for sending objects via sockets
	 */
	private static final long serialVersionUID = 4444413373095067281L;
	
	public ImageIcon icon;
	
	
	/**
	 * PLACEHOLDER
	 * This will be a picture in the future!
	 */
	
	
	/**
	 * A text with additional information
	 */
	private String text;
	
	public PresentationPacket(PresObj pObj) {
		pObj.icon = icon;
		pObj.text = text;
	}
	
	/**
	 * Returns a reference to the picture which will be updated
	 * by a remote client
	 * @return The picture for visualization 
	 */
	public ImageIcon getPicture() {
		return icon;
	}

	/**
	 * Returns a reference to the additional text which will be 
	 * updated by a remote client
	 * @return The text for visualization 
	 */
	public String getText() {
		return text;
	}
	
}
