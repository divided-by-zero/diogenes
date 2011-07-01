package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.ImageIcon;

import de.hsrm.diogenes.connection.Location;

public class PaketFurz implements Serializable, Presentable {


	private String text1;
	
	private String text2;
	
	private static final long serialVersionUID = -7540106854783084L;

	private Rectangle triggerBox;
	
	public PaketFurz(String t1, String t2, Rectangle triggerBox) {
		text1 = t1;
		text2 = t2;
		this.triggerBox = triggerBox;
	}

	@Override
	public boolean surrounds(Point p) {
		if (triggerBox.contains(p)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean surrounds(Location l) {
		return surrounds(new Point(l.getX(), l.getY()));
	}

	@Override
	public byte[] imageToByteArray() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] textToByteArray() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int imageToByteArrayLength() throws FileNotFoundException,
			IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int textToByteArrayLength() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		return null;
	}

}
