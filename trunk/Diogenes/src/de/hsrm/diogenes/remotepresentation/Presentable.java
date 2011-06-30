package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import javax.swing.ImageIcon;
import de.hsrm.diogenes.connection.Location;

public interface Presentable {

	// TODO abstract
	public byte[] toByteArray();
	public ImageIcon getImage();
	public String getDescriptionText();
	public String getAdditionalText();
	public boolean surrounds(Point p);
	public boolean surrounds(Location l);
	
}
