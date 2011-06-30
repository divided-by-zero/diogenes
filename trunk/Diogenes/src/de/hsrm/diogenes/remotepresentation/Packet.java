package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.hsrm.diogenes.connection.Location;

public class Packet implements Presentable {

	private File file;
	private ImageIcon image;
	private String text;
	private Rectangle triggerBox;
	
	public Packet(File file, String text, Rectangle triggerBox) {
			this.file 		= file;
			this.text		= text;
			this.triggerBox = triggerBox;
	}

	public Packet(ImageIcon image, String text, Rectangle triggerBox) {
		this.image 		= image;
		this.text		= text;
		this.triggerBox = triggerBox;
	}

	public ImageIcon getImage() {
		return image;
	}
	
	@Override
	public String getText() {
		return text;
	}	
	
	@Override
	public boolean surrounds(Location l) {
		return surrounds(new Point(l.getX(), l.getY()));
	}
	
	@Override
	public boolean surrounds(Point p) {
		if (triggerBox.contains(p)) {
			return true;
		}
		return false;
	}

	@Override
	public Rectangle getRectangle() {
		return triggerBox;
	}

	@Override
	public byte[] imageToByteArray() throws FileNotFoundException, IOException {
		return Conversion.fileToBaos(this.file);
	}

	@Override
	public int imageToByteArrayLength() throws FileNotFoundException, IOException {
		return Conversion.fileToBaos(this.file).length;
	}

	@Override
	public byte[] textToByteArray() throws IOException {
		return Conversion.stringToBaos(this.text);		
	}

	@Override
	public int textToByteArrayLength() throws IOException {
		return Conversion.stringToBaos(this.text).length;
		
	}
	
}
