package de.hsrm.diogenes.remotepresentation;

import java.io.Serializable;

public class Packet implements Serializable {

	/**
	 * Serialization needed for sending objects via sockets
	 */
	private static final long serialVersionUID = 4444413373095067281L;

	private Content content;
	
	public Packet(Content c) {
		this.content = c;
	}
	
	public Content getContent() {
		return content;
	}
	
}
