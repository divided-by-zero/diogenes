package de.hsrm.diogenes.connection;

/**
 * The Class Location containing the current location of the robot.
 * @author Daniel Ernst
 */
public class Location {

	/** The x-coordinate of the robot */
	private int x;
	
	/** The y-coordinate of the robot */
	private int y;
	
	/** The angle of the robot (from -179,9 to 180) */
	private int angle;
	
	/**
	 * Instantiates a new location.
	 * @param x The x-coordinate of the robot
	 * @param y The x-coordinate of the robot
	 * @param angle The angle of the robot
	 */
	public Location(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	/**
	 * Prints out the current values.
	 */
	public String toString() {
		return "Location: X=" + x + ", Y=" + y + ", Angle=" + angle; 
	}
	
	/**
	 * Gets the x-coordinate of the robot.
	 * @return The x-coordinate of the robot.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y-coordinate of the robot.
	 * @return The y-coordinate of the robot.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the angle of the robot.
	 * @return The angle of the robot.
	 */
	public int getAngle() {
		return angle;
	}
	
	/**
	 * Sets the x-coordinate of the robot.
	 * @param x The new x-coordinate of the robot.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y-coordinate of the robot.
	 * @param y The new y-coordinate of the robot.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the angle of the robot.
	 * @param angle The new angle of the robot.
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	/**
	 * Sets x- and y-coordinate and the angle of the robot.
	 * @param x The x-coordinate of the robot.
	 * @param y The y-coordinate of the robot.
	 * @param angle The angle of the robot.
	 */
	public void setAll(int x, int y, int angle) {
		setX(x);
		setY(y);
		setAngle(angle);
	}
	
}
