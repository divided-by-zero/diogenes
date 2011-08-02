package de.hsrm.diogenes.remotepresentation;

import java.util.ArrayList;

/**
 * A Container holding ClientPackets.
 * @author Daniel Ernst
 */
public class ClientPacketContainer extends ArrayList<ClientPacket> {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ClientPacketContainer with the given Packets
	 */
	public ClientPacketContainer(ClientPacket... presentables) {
		for (ClientPacket p : presentables) {
			this.add(p);
		}
	}

}
