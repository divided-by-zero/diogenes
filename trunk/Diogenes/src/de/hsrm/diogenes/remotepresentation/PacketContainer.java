package de.hsrm.diogenes.remotepresentation;

import java.util.ArrayList;

/**
 * The Class PacketContainer.
 */
public class PacketContainer extends ArrayList<Presentable> {

	/** The constant serialVersionUID. */
	private static final long serialVersionUID = -868542116216859181L;

	/**
	 * Instantiates a new packet container with the given presentables
	 */
	public PacketContainer(Presentable... presentables) {
		for (Presentable p : presentables) {
			this.add(p);
		}
	}
	
}
