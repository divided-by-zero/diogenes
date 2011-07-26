package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import de.hsrm.diogenes.connection.Location;

/**
 * Contains an image(file), text and a triggerBox
 */
public class ClientPacket {

	/** The file of the image. */
	private File file;
	
	/** The text. */
	private String text;
	
	/** The triggerBox describing the coordinates where the
	 * robot invokes the display of the image and the text */
	private Rectangle triggerBox;
	
	/**
	 * Instantiates a new ClientPacket.
	 *
	 * @param file The file to the image.
	 * @param text The text.
	 * @param triggerBox The triggerBox describing the coordinates where 
	 * 			the robot invokes the discplay of the image and the text
	 */
	public ClientPacket(File file, String text, Rectangle triggerBox) {
			this.file 		= file;
			this.text		= text;
			this.triggerBox = triggerBox;
	}

	public byte[] getImage() throws FileNotFoundException, IOException {
		return Conversion.fileToBaos(this.file);
	}

	public byte[] getText() throws IOException {
		return Conversion.stringToBaos(this.text);		
	}
	
	public Rectangle getTriggerBox() {
		return triggerBox;
	}

	public boolean surrounds(Location l) {
		return surrounds(new Point(l.getX(), l.getY()));
	}
	
	public boolean surrounds(Point p) {
		if (triggerBox.contains(p)) {
			return true;
		}
		return false;
	}
	
}
