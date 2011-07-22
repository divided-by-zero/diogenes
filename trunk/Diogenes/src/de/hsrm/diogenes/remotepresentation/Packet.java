package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;



import de.hsrm.diogenes.connection.Location;

/**
 * The Class Packet.
 */
public class Packet implements Presentable {

	/** The file. */
	private File file;
	
	/** The image. */
	private ImageIcon image;
	
	/** The text. */
	private String text;
	
	/** The trigger box. */
	private Rectangle triggerBox;
	
	/**
	 * Instantiates a new packet.
	 *
	 * @param file the file
	 * @param text the text
	 * @param triggerBox the trigger box
	 */
	public Packet(File file, String text, Rectangle triggerBox) {
			this.file 		= file;
			this.text		= text;
			this.triggerBox = triggerBox;
	}

	/**
	 * Instantiates a new packet.
	 *
	 * @param image the image
	 * @param text the text
	 * @param triggerBox the trigger box
	 */
	public Packet(ImageIcon image, String text, Rectangle triggerBox) {
		this.image 		= image;
		this.text		= text;
		this.triggerBox = triggerBox;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#getImage()
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#getText()
	 */
	@Override
	public String getText() {
		return text;
	}	
	
	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#surrounds(de.hsrm.diogenes.connection.Location)
	 */
	@Override
	public boolean surrounds(Location l) {
		return surrounds(new Point(l.getX(), l.getY()));
	}
	
	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#surrounds(java.awt.Point)
	 */
	@Override
	public boolean surrounds(Point p) {
		if (triggerBox.contains(p)) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#getRectangle()
	 */
	@Override
	public Rectangle getRectangle() {
		return triggerBox;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#imageToByteArray()
	 */
	@Override
	public byte[] imageToByteArray() throws FileNotFoundException, IOException {
		return Conversion.fileToBaos(this.file);
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#imageToByteArrayLength()
	 */
	@Override
	public int imageToByteArrayLength() throws FileNotFoundException, IOException {
		return Conversion.fileToBaos(this.file).length;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#textToByteArray()
	 */
	@Override
	public byte[] textToByteArray() throws IOException {
		return Conversion.stringToBaos(this.text);		
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#textToByteArrayLength()
	 */
	@Override
	public int textToByteArrayLength() throws IOException {
		return Conversion.stringToBaos(this.text).length;
		
	}
	
}
