package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

/**
 * The Class PacketContainer.
 */
public class PacketContainer extends ArrayList<Presentable> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -868542116216859181L;

	/**
	 * Instantiates a new packet container.
	 */
	public PacketContainer() {
		// TODO add items by user
		// testitems:
		// 1. rectangle at starting position of robbie
		this.add(new Packet(
				new File("test1.jpg"), 
				"The answer is 42", 
				new Rectangle(-1000, 1000, 1300, 1300)));
		//2. rectangle in front of the middle-desk
		this.add(new Packet(
				new File("test3.jpg"), 
				"<html><B>Kaffee</B> [ˈkafe, kaˈfeː] (türk. kahve aus arab. ‏قهوة‎ qahwa) " +
				"ist ein schwarzes, coffeinhaltiges Heißgetränk, das aus gerösteten " +
				"(weshalb man auch von Röstkaffee spricht) und gemahlenen Kaffeebohnen " +
				"hergestellt wird.</html>",
				new Rectangle(1000, -2000, 3500, 2000)));
//		this.add(new PaketFurz( 
//		"The answer is 42",
//		"zweiter",
//		new Rectangle(-1000, 1000, 1300, 1300)));
//		this.add(new PaketFurz( 
//		"<html><B>Kaffee</B> [ˈkafe, kaˈfeː] (türk. kahve aus arab. ‏قهوة‎ qahwa) " +
//		"ist ein schwarzes, coffeinhaltiges Heißgetränk, das aus gerösteten " +
//		"(weshalb man auch von Röstkaffee spricht) und gemahlenen Kaffeebohnen " +
//		"hergestellt wird.</html>",
//		"zweiter",
//		new Rectangle(1000, -2000, 3500, 2000)));
	}
	
}
