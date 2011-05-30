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
	
	// used mainly for testing, maybe not neccessary in final class
	public void setPicture(int picture) {
		this.picture = picture;
	}
	
	public String getText() {
		return text;
	}
	
	// used mainly for testing, maybe not neccessary in final class
	public void setText(String text) {
		this.text = text;
	}
	
}
