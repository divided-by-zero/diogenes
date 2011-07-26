package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import java.io.File;

public class PacketContainerExample extends ClientPacketContainer {

	private static final long serialVersionUID = 1L;

	public PacketContainerExample() {
		// 1. rectangle at starting position of robbie
		// 2. rectangle in front of the middle-desk
		super(
				new ClientPacket(new File("test1.jpg"), "The answer is 42",
						new Rectangle(-1000, 1000, 1300, 1300)),
				new ClientPacket(
						new File("test3.jpg"),
						"<html><B>Kaffee</B> [ˈkafe, kaˈfeː] (türk. kahve aus arab. ‏قهوة‎ qahwa)<br>"
								+ "ist ein schwarzes, coffeinhaltiges Heißgetränk, das aus gerösteten<br>"
								+ "(weshalb man auch von Röstkaffee spricht) und gemahlenen Kaffeebohnen<br>"
								+ "hergestellt wird.</html>", new Rectangle(
								1000, -2000, 3500, 2000)));
	}
	
}
