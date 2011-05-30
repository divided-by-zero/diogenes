package de.hsrm.diogenes.socket;

import java.io.Serializable;

public class PresentationPacket implements Serializable {

	/**
	 * Serialization needed for sending objects via sockets
	 */
	private static final long serialVersionUID = 4444413373095067281L;
	
	/**
	 * PLACEHOLDER
	 * This will be a picture in the future!
	 */
	private int picture;
	
	/**
	 * A text with additional information
	 */
	private String text;
	
	public PresentationPacket(int picture, String text) {
		this.picture = picture;
		this.text = text;
	}
	
	/**
	 * Returns a reference to the picture which will be updated
	 * by a remote client
	 * @return The picture for visualization 
	 */
	public int getPicture() {
		return picture;
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
