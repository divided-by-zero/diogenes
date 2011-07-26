package de.hsrm.diogenes.connection;

/**
 * The Class Location, gives us the current location of the robot.
 */
public class Location {

	/** The x. @uml.property  name="x" */
	private int x;
	
	/** The y. @uml.property  name="y" */
	private int y;
	
	/** The angle. @uml.property  name="angle" */
	private int angle;
	
	/**
	 * Instantiates a new location.
	 *
	 * @param x the x
	 * @param y the y
	 * @param angle the angle
	 */
	public Location(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Location: X=" + x + ", Y=" + y + ", Angle=" + angle; 
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 * @uml.property  name="x"
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 * @uml.property  name="y"
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the all.
	 *
	 * @param x the x
	 * @param y the y
	 * @param angle the angle
	 */
	public void setAll(int x, int y, int angle) {
		setX(x);
		setY(y);
		setAngle(angle);
	}
	
	/**
	 * Sets the angle.
	 *
	 * @param angle the new angle
	 * @uml.property  name="angle"
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 * @uml.property  name="x"
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 * @uml.property  name="y"
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the angle.
	 *
	 * @return the angle
	 * @uml.property  name="angle"
	 */
	public int getAngle() {
		return angle;
	}
	
}
