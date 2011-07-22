package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;

import de.hsrm.diogenes.connection.Location;

/**
 * The Class PaketFurz.
 */
public class PaketFurz implements Serializable, Presentable {


	/** The text1. */
	private String text1;
	
	/** The text2. */
	private String text2;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7540106854783084L;

	/** The trigger box. */
	private Rectangle triggerBox;
	
	/**
	 * Instantiates a new paket furz.
	 *
	 * @param t1 the t1
	 * @param t2 the t2
	 * @param triggerBox the trigger box
	 */
	public PaketFurz(String t1, String t2, Rectangle triggerBox) {
		setText1(t1);
		setText2(t2);
		this.triggerBox = triggerBox;
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
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#surrounds(de.hsrm.diogenes.connection.Location)
	 */
	@Override
	public boolean surrounds(Location l) {
		return surrounds(new Point(l.getX(), l.getY()));
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#imageToByteArray()
	 */
	@Override
	public byte[] imageToByteArray() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#textToByteArray()
	 */
	@Override
	public byte[] textToByteArray() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#imageToByteArrayLength()
	 */
	@Override
	public int imageToByteArrayLength() throws FileNotFoundException,
			IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#textToByteArrayLength()
	 */
	@Override
	public int textToByteArrayLength() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#getImage()
	 */
	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#getText()
	 */
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.hsrm.diogenes.remotepresentation.Presentable#getRectangle()
	 */
	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Sets the text1.
	 *
	 * @param text1 the new text1
	 */
	public void setText1(String text1) {
		this.text1 = text1;
	}

	/**
	 * Gets the text1.
	 *
	 * @return the text1
	 */
	public String getText1() {
		return text1;
	}

	/**
	 * Sets the text2.
	 *
	 * @param text2 the new text2
	 */
	public void setText2(String text2) {
		this.text2 = text2;
	}

	/**
	 * Gets the text2.
	 *
	 * @return the text2
	 */
	public String getText2() {
		return text2;
	}

}
