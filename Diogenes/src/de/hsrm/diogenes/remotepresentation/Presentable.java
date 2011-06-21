package de.hsrm.diogenes.remotepresentation;

import java.awt.Point;
import javax.swing.ImageIcon;
import de.hsrm.diogenes.connection.Location;

/**
 * @author  phil
 */
public interface Presentable {

	/**
	 * @uml.property  name="image"
	 * @uml.associationEnd  
	 */
	public ImageIcon getImage();
	/**
	 * @uml.property  name="descriptionText"
	 */
	public String getDescriptionText();
	/**
	 * @uml.property  name="additionalText"
	 */
	public String getAdditionalText();
	public boolean surrounds(Point p);
	public boolean surrounds(Location l);
	
}
