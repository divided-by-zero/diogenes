package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import de.hsrm.diogenes.connection.Location;

public interface Presentable {

	// TODO abstract
	
	public byte[] imageToByteArray();
	public byte[] textToByteArray();
	public int imageToByteArrayLength();
	public int textToByteArrayLength();
	
	public ImageIcon getImage();
	public String getText();
	public Rectangle getRectangle();	
	public boolean surrounds(Point p);
	public boolean surrounds(Location l);
	
}
