package de.hsrm.diogenes.camera;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;

public class CameraData {
	
	private static final long serialVersionUID = 1L;
	private WRPVideoPacket packet;
	private JLabel cam;

	public CameraData(WRPVideoPacket packet) {
		this.packet = packet;
		setUpCamera();
	}
	
	
	public void setUpCamera() {
		System.out.println("handleVideoPacket(): Received image packet - "
				+ "data size is " + this.packet.getJpegData().length + " bytes.");
		
		try {
			final BufferedImage image = ImageIO.read(new ByteArrayInputStream(packet.getJpegData()));
			setCam(new JLabel(new ImageIcon(image)));
			}
		catch (IOException e) {
			System.err.println("Could not create buffered image :(");
		}
	}


	public void setCam(JLabel cam) {
		this.cam = cam;
	}


	public JLabel getCam() {
		return cam;
	}


}
