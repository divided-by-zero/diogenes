package de.hsrm.diogenes.connection;

public class Location {

	/**
	 * @uml.property  name="x"
	 */
	private int x;
	/**
	 * @uml.property  name="y"
	 */
	private int y;
	/**
	 * @uml.property  name="angle"
	 */
	private int angle;
	
	public Location(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public String toString() {
		return "Location: X=" + x + ", Y=" + y + ", Angle=" + angle; 
	}
	
	/**
	 * @param x
	 * @uml.property  name="x"
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @param y
	 * @uml.property  name="y"
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public void setAll(int x, int y, int angle) {
		setX(x);
		setY(y);
		setAngle(angle);
	}
	
	/**
	 * @param angle
	 * @uml.property  name="angle"
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}
	/**
	 * @return
	 * @uml.property  name="x"
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return
	 * @uml.property  name="y"
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return
	 * @uml.property  name="angle"
	 */
	public int getAngle() {
		return angle;
	}
	
}
