package de.hsrm.diogenes.socket;

public class PresentationPacket {

	// no idea of picture-classes, so an integer as example-class!
	private int picture;
	private String text;
	
	public PresentationPacket(int picture, String text) {
		this.picture = picture;
		this.text = text;
	}
	
	public int getPicture() {
		return picture;
	}
	
	public String getText() {
		return text;
	}
	
}
