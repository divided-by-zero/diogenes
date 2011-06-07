package de.hsrm.diogenes.connection;

public class Location {

	private int x;
	private int y;
	private int angle;
	
	public Location(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getAngle() {
		return angle;
	}
	
}
