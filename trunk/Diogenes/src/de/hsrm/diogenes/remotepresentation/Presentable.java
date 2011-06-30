package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import de.hsrm.diogenes.connection.Location;

public interface Presentable {

	// TODO abstract
	
	public byte[] imageToByteArray() throws FileNotFoundException, IOException;
	public byte[] textToByteArray() throws IOException;
	public int imageToByteArrayLength() throws FileNotFoundException, IOException;
	public int textToByteArrayLength() throws IOException;
	
	public ImageIcon getImage();
	public String getText();
	public Rectangle getRectangle();	
	public boolean surrounds(Point p);
	public boolean surrounds(Location l);
	
}
