package de.hsrm.diogenes.socket;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PresentationGUI {

	PresentationPacket packet;
	JFrame frame;
	JLabel picture_label;
	JLabel text_label;
	
	public PresentationGUI() {
		frame = new JFrame("title");
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		picture_label = new JLabel();
		text_label = new JLabel("Nothing to display so far...");
		frame.add(picture_label);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void refreshContent() {
		picture_label.setIcon(packet.getPicture());
		text_label.set
	}
}
