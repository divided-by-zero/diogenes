package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import de.hsrm.diogenes.connection.Location;

/**
 * The Interface Presentable.
 */
public interface Presentable {
	
	/**
	 * Image to byte array.
	 *
	 * @return the byte[]
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] imageToByteArray() throws FileNotFoundException, IOException;
	
	/**
	 * Text to byte array.
	 *
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] textToByteArray() throws IOException;
	
	/**
	 * Image to byte array length.
	 *
	 * @return the int
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int imageToByteArrayLength() throws FileNotFoundException, IOException;
	
	/**
	 * Text to byte array length.
	 *
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int textToByteArrayLength() throws IOException;
	
	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public ImageIcon getImage();
	
	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText();
	
	/**
	 * Gets the rectangle.
	 *
	 * @return the rectangle
	 */
	public Rectangle getRectangle();	
	
	/**
	 * Surrounds.
	 *
	 * @param p the p
	 * @return true, if successful
	 */
	public boolean surrounds(Point p);
	
	/**
	 * Surrounds.
	 *
	 * @param l the l
	 * @return true, if successful
	 */
	public boolean surrounds(Location l);
	
}
