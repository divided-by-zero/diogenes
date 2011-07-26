package de.hsrm.diogenes.remotepresentation;

import java.util.ArrayList;

/**
 * The Class PacketContainer.
 */
public class ClientPacketContainer extends ArrayList<ClientPacket> {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new PacketContainer with the given Packets
	 */
	public ClientPacketContainer(ClientPacket... presentables) {
		for (ClientPacket p : presentables) {
			this.add(p);
		}
	}

}
