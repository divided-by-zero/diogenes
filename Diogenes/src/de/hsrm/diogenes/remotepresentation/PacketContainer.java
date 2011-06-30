package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PacketContainer extends ArrayList<Presentable> {

	private static final long serialVersionUID = -868542116216859181L;

	public PacketContainer() {
		// TODO add items by user
		// testitems:
		// 1. rectangle at starting position of robbie
		this.add(new Packet(
				new ImageIcon("test1.jpg"), 
				"The answer is 42", 
				new Rectangle(-1000, 1000, 1300, 1300)));
		//2. rectangle in front of the middle-desk
		this.add(new Packet(
				new ImageIcon("test3.jpg"), 
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
