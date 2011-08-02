package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import de.hsrm.diogenes.connection.Location;

/**
 * Container for a file (referring an image), text and a triggerBox
 * @author Philip Koch, Daniel Ernst
 */
public class ClientPacket {

	/** The file of the image. */
	private File file;
	
	/** Additional text to be displayed next to the image. */
	private String text;
	
	/** 
	 * The triggerBox describing the coordinates where the
	 * robot invokes the display of the image and the text 
	 */
	private Rectangle triggerBox;
	
	/**
	 * Instantiates a new ClientPacket.
	 * @param file The file to the image.
	 * @param text The text to be displayed next to the image.
	 * @param triggerBox The triggerBox describing the coordinates where 
	 * 			the robot invokes the display of the image and the text
	 */
	public ClientPacket(File file, String text, Rectangle triggerBox) {
			this.file 		= file;
			this.text		= text;
			this.triggerBox = triggerBox;
	}

	/**
	 * Returns the image as Bytearray
	 * @return The image as a Bytearray
	 * @throws FileNotFoundExceptionn If the file couldn't be found
	 * @throws IOException If the file couldn't be accessed
	 */
	public byte[] getImage() throws FileNotFoundException, IOException {
		return Conversion.fileToBaos(this.file);
	}

	/**
	 * Returns the text as Bytearray
	 * @return The text as a Bytearray
	 * @throws IOException If the file couldn't be accessed
	 */
	public byte[] getText() throws IOException {
		return Conversion.stringToBaos(this.text);		
	}
	
	/**
	 * Returns the triggerBox describing the coordinates
	 * where the robot invokes the display of the image 
	 * and the text.
	 * @return The triggerBox describing the coordinates
	 * 			where the robot invokes the display of the 
	 * 			image and the text.
	 */
	public Rectangle getTriggerBox() {
		return triggerBox;
	}

	/**
	 * Determines if the triggerBox of this Packet surrounds 
	 * a Location (~2D-Point) - or in other words:
	 * If the Location lies within the Packet's triggerBox
	 * @param l The Location to be checked for being surrounded
	 * 			by this Packet's triggerBox
	 * @return True if the Location lies within the Packet's 
	 * 			triggerBox or false if not
	 */
	public boolean surrounds(Location l) {
		return surrounds(new Point(l.getX(), l.getY()));
	}

	/**
	 * Determines if the triggerBox of this Packet surrounds 
	 * a Point - or in other words:
	 * If the Point lies within the Packet's triggerBox
	 * @param p The Point to be checked for being surrounded
	 * 			by this Packet's triggerBox
	 * @return True if the Point lies within the Packet's 
	 * 			triggerBox or false if not
	 */
	public boolean surrounds(Point p) {
		if (triggerBox.contains(p)) {
			return true;
		}
		return false;
	}
	
}
