package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import java.io.File;

/**
 * A Container holding ClientPackets with an example-content for a demonstration 
 * in the C-building of the University of Applied Science, Wiesbaden.
 * @author Daniel Ernst
 */
public class ClientPacketContainerExample extends ClientPacketContainer {

	private static final long serialVersionUID = 1L;

	public ClientPacketContainerExample() {
		
		// 1st rectangle at starting position of robbie with content "42 robot"
		// 2nd rectangle in front of the middle-desk with content "prof. dr. panitz"
		super(
				new ClientPacket(
						
						new File("pres_42.jpg"), 
						"The answer is 42",
						new Rectangle(-1000, 1000, 1300, 1300)),
				new ClientPacket(
						new File("pres_panitz.jpg"),
						"<html><B>Prof. Dr. Sven Eric Panitz</B><br>" +
						"Hochschule Rhein Main<br>" + 
						"University of Applied Science<br>" +
						"Fachbereich Design Informatik Medien<br>" +
						"Studienbereich Informatik<br>" +
						"Kurt Schumacher Ring. 18<br>" +
						"D-65197 Wiesbaden<br>" +
						"Germany</html>",
						new Rectangle(1000, -2000, 3500, 2000)));
	}
	
}
