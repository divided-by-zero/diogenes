package de.hsrm.diogenes.camera;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;

/**
 * The Class CameraData.
 */
public class CameraData {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The packet with the cam-data
	 * @uml.property  name="packet"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private WRPVideoPacket packet;
	
	/**
	 * A label to display the camera images
	 * @uml.property  name="cam"
	 * @uml.associationEnd  
	 */
	private JLabel cam;

	/**
	 * Instantiates a new camera data.
	 * @param packet the packet
	 */
	public CameraData(WRPVideoPacket packet) {
		this.packet = packet;
		setUpCamera();
	}
	
	/**
	 * Sets the up camera.
	 */
	public void setUpCamera() {
		System.out.println("handleVideoPacket(): Received image packet - "
				+ "data size is " + this.packet.getJpegData().length + " bytes.");
		try {
			final BufferedImage image = ImageIO.read(new ByteArrayInputStream(packet.getJpegData()));
			setCam(new JLabel(new ImageIcon(image)));
		} catch (IOException e) {
			System.err.println("Could not create buffered image :(");
		}
	}

	/**
	 * Sets the Camera.
	 * @param cam  A new Camera
	 * @uml.property  name="cam"
	 */
	public void setCam(JLabel cam) {
		this.cam = cam;
	}

	/**
	 * Gets the Camera.
	 * @return the cam
	 * @uml.property  name="cam"
	 */
	public JLabel getCam() {
		return cam;
	}

}
