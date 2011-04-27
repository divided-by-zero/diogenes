package de.hsrm.diogenes.camera;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;

public class CameraData extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private WRPVideoPacket packet;
	
	public CameraData(WRPVideoPacket packet) {
		this.packet = packet;
		setUpCamera();
	}
	
	
	public void setUpCamera() {
		System.out.println("handleVideoPacket(): Received image packet - "
				+ "data size is " + this.packet.getJpegData().length + " bytes.");
		
		// Just to get an idea of how to work with the image data...
		try {
			final BufferedImage image = ImageIO.read(new ByteArrayInputStream(packet.getJpegData()));
			final JLabel label = new JLabel(new ImageIcon(image));
			this.add(label);
		}
		catch (IOException e) {
			System.err.println("Could not create buffered image :(");
		}
	}
	
}
