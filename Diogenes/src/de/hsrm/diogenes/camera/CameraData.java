package de.hsrm.diogenes.camera;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.fhwiesbaden.webrobbie.wrp.WRPCmd;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCommand;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;
import de.hsrm.diogenes.connection.Connection;

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

	private BufferedImage image; 
	private Connection c;
	/**
	 * Instantiates a new camera data.
	 * @param packet the packet
	 * @throws IOException 
	 */
	public CameraData(WRPVideoPacket packet, Connection c) throws IOException {
		this.packet = packet;
		this.c = c;
		setUpCamera();
		takePhoto();
	}
	
	/**
	 * Sets the up camera.
	 */
	public void setUpCamera() {
		System.out.println("handleVideoPacket(): Received image packet - "
				+ "data size is " + this.packet.getJpegData().length + " bytes.");
		
		try {
			image = ImageIO.read(new ByteArrayInputStream(packet.getJpegData()));
			setCam(new JLabel(new ImageIcon(image)));
			}
		catch (IOException e) {
			System.err.println("Could not create buffered image :(");
		}
	}
	
	public void adjustCameraLeft(int angle) throws WRPException{
		
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.CAMERA_MOVE, angle));
		this.c.getDiogenes().waitFor(WRPCmd.CAMERA_MOVE);
		
	}
	
	public void takePhoto() throws IOException{
		ImageIO.write(this.image, "jpg", new File("photo"));
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
