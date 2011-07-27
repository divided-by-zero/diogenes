package de.hsrm.diogenes.camera;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.fhwiesbaden.webrobbie.wrp.WRPCmd;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPCommand;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPVideoPacket;
import de.hsrm.diogenes.connection.Connection;

/**
 * The Class CameraData handles everything related to the camera.
 * @author Dirk Stanke
 * 
 */
public class CameraData {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The packet with the cam-data. @uml.property  name="packet" @uml.associationEnd  multiplicity="(1 1)"
	 * @uml.property  name="packet"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private WRPVideoPacket packet;
	
	/**
	 * A label to display the camera images. @uml.property  name="cam" @uml.associationEnd
	 * @uml.property  name="cam"
	 * @uml.associationEnd  
	 */
	private JLabel cam;

	/**
	 * The image.
	 * @uml.property  name="image"
	 */
	private BufferedImage image; 
	
	/**
	 * The connection.
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="cameraData:de.hsrm.diogenes.connection.Connection"
	 */
	private Connection c;
	
	/**
	 * Counts the amount of taken photos
	 */
	private int photoCount;
	
	
	/**
	 * An ArrayList of photos
	 */
	private List<BufferedImage> pList;
	
	/**
	 * Instantiates a new camera data object.
	 *
	 * @param packet the packet
	 * @param c the connection
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CameraData(WRPVideoPacket packet, Connection c) throws IOException {
		this.packet = packet;
		this.c = c;
		this.pList = new ArrayList<BufferedImage>();
		this.photoCount = 0;
		setUpCamera();
		//takePhoto();
	}
	
	/**
	 * Sets up the camera.
	 */
	public void setUpCamera() {
		try {
			image = ImageIO.read(new ByteArrayInputStream(packet.getJpegData()));
			setCam(new JLabel(new ImageIcon(image)));
			}
		catch (IOException e) {
			System.err.println("Could not create buffered image :(");
		}
	}
	
	/**
	 * Adjust camera left.
	 *
	 * @param angle the angle
	 * @throws WRPException the wRP exception
	 */
	public void adjustCameraLeft(int angle) throws WRPException{
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GET_CAMERA_DATA));
		this.c.getDiogenes().waitFor(WRPCmd.GET_CAMERA_DATA);
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.CAMERA_PAN, +angle));
		System.out.println("CamPan sent");
		this.c.getDiogenes().waitFor(WRPCmd.CAMERA_PAN);
		
	}
	
	/**
	 * Adjust camera right.
	 *
	 * @param angle the angle
	 * @throws WRPException the wRP exception
	 */
	public void adjustCameraRight(int angle) throws WRPException{
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GET_CAMERA_DATA));
		this.c.getDiogenes().waitFor(WRPCmd.GET_CAMERA_DATA);
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.CAMERA_PAN, -angle));
		System.out.println("CamPan sent");
		this.c.getDiogenes().waitFor(WRPCmd.CAMERA_PAN);
		
	}
	
	/**
	 * Adjust camera up.
	 *
	 * @param angle the angle
	 * @throws WRPException the wRP exception
	 */
	public void adjustCameraUp(int angle) throws WRPException{
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GET_CAMERA_DATA));
		this.c.getDiogenes().waitFor(WRPCmd.GET_CAMERA_DATA);
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.CAMERA_TILT, -angle));
		System.out.println("CamPan sent");
		this.c.getDiogenes().waitFor(WRPCmd.CAMERA_TILT);
		
	}
	
	/**
	 * Adjust camera down.
	 *
	 * @param angle the angle
	 * @throws WRPException the wRP exception
	 */
	public void adjustCameraDown(int angle) throws WRPException{
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GET_CAMERA_DATA));
		this.c.getDiogenes().waitFor(WRPCmd.GET_CAMERA_DATA);
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.CAMERA_TILT, +angle));
		System.out.println("CamPan sent");
		this.c.getDiogenes().waitFor(WRPCmd.CAMERA_TILT);
		
	}
	
	public void zoomIn(int factor) throws WRPException{
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GET_CAMERA_DATA));
		this.c.getDiogenes().waitFor(WRPCmd.GET_CAMERA_DATA);
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.CAMERA_ZOOM, factor));
		System.out.println("Zooming in");
		this.c.getDiogenes().waitFor(WRPCmd.CAMERA_ZOOM);
	}
	
	public void zoomOut(int factor) throws WRPException{
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.GET_CAMERA_DATA));
		this.c.getDiogenes().waitFor(WRPCmd.GET_CAMERA_DATA);
		this.c.getDiogenes().sendCommand(new WRPCommand(WRPCmd.CAMERA_ZOOM, -factor));
		System.out.println("Zooming in");
		this.c.getDiogenes().waitFor(WRPCmd.CAMERA_ZOOM);
	}
	
	
	/**
	 * Takes a photo.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void takePhoto() throws IOException{
		
		/*this.pList.add(this.image);
		this.image.flush();*/
		
		ImageIcon ic = new ImageIcon(this.image);
		//this.image.flush();
		photoCount++;
		ImageIO.write(this.image, "jpg", new File("photo"+(photoCount)));
			
		
		JFrame f = new JFrame("Picture preview");
		JLabel j = new JLabel(ic);
		f.add(j);
		f.pack();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		f.repaint();

	}
	
	
	/**
	 * Writes the saved photos to hdd
	 * @throws IOException
	 */
	public void writePhotos() throws IOException{
		for( BufferedImage bI : this.pList){
			photoCount++;
			ImageIO.write(bI, "jpg", new File("photo"+this.photoCount));
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

	public List<BufferedImage> getpList() {
		return pList;
	}

	public void setpList(List<BufferedImage> pList) {
		this.pList = pList;
	}

}
