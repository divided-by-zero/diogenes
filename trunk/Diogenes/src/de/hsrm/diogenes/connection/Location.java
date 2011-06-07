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
	
	public String toString() {
		return "Location: X=" + x + ", Y=" + y + ", Angle=" + angle; 
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setAll(int x, int y, int angle) {
		setX(x);
		setY(y);
		setAngle(angle);
	}
	
	public void setAngle(int angle) {
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
