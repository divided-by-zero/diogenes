package de.hsrm.diogenes.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import de.fhwiesbaden.webrobbie.clientutil.map.Map2ImageTransformer;
import de.fhwiesbaden.webrobbie.clientutil.map.MapFile;
import de.fhwiesbaden.webrobbie.clientutil.map.MapLine;
import de.fhwiesbaden.webrobbie.clientutil.map.MapPoint;

/**
 * The Class ConvertMap2Image.
 * Converts a MapFile to an Image :)
 * @author Dirk Stanke
 */
public class ConvertMap2Image extends Map2ImageTransformer<BufferedImage> {

	
	/**
	 * The map given by the robot.
	 * @uml.property  name="map"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private MapFile map;
	
	/**
	 * The lines from the mapfile.
	 * @uml.property  name="lines"
	 */
	private List<MapLine> lines;
	
	/**
	 * The points form the mapfile.
	 * @uml.property  name="points"
	 */
	private List<MapPoint> points;
	
	/**
	 * The image.
	 * @uml.property  name="image"
	 */
	private BufferedImage image;
	
	/** The scale factor. */
	private static int scaleFactor;
	
	/** The zoom factor. */
	private static int zoomFactor;
	
	/** The width. */
	private static int width;
	
	/** The height. */
	private static int height;
	
	/**
	 * Instantiates a new convert map2 image.
	 *
	 * @param map the map
	 * @param scaleWidth the scale width
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ConvertMap2Image(MapFile map, int scaleWidth) throws IOException {
		super(map, scaleWidth);
		this.setMap(map);
		this.lines = new ArrayList<MapLine>();
		this.points = new ArrayList<MapPoint>();
		this.lines = map.getLineList();
		this.points = map.getPointList();
		ConvertMap2Image.scaleFactor = 100;
		ConvertMap2Image.zoomFactor = 2;
		ConvertMap2Image.width = 400;
		ConvertMap2Image.height = 460;
		this.image = transform();
		writeMap();
	}
	
	/**
	 * Zoom in.
	 */
	public static void zoomIn() {
		ConvertMap2Image.scaleFactor /= ConvertMap2Image.zoomFactor;
		ConvertMap2Image.width *= ConvertMap2Image.zoomFactor;
		ConvertMap2Image.height *= ConvertMap2Image.zoomFactor;
	}
	
	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.clientutil.map.Map2ImageTransformer#transform()
	 */
	
	/**
	 * Creates an image with the given lines and points from the mapfile.
	 *
	 * @return the buffered image
	 */
	@Override
	public BufferedImage transform() {
		
		
		BufferedImage img = new BufferedImage(ConvertMap2Image.width*zoomFactor, ConvertMap2Image.height*zoomFactor, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)img.getGraphics();
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, ConvertMap2Image.width*zoomFactor, ConvertMap2Image.height*zoomFactor);
		
		g.setColor(Color.BLACK);
		for(MapLine ml : lines){
			g.drawLine((int)ml.getP1().getX()/ConvertMap2Image.scaleFactor*zoomFactor+49, (int)ml.getP1().getY()*-1/ConvertMap2Image.scaleFactor*zoomFactor+ConvertMap2Image.width*zoomFactor,
						(int)ml.getP2().getX()/ConvertMap2Image.scaleFactor*zoomFactor+49, (int)ml.getP2().getY()*-1/ConvertMap2Image.scaleFactor*zoomFactor+ConvertMap2Image.width*zoomFactor);
		}
		g.setColor(Color.BLACK);
		for(MapPoint mp : points){
			g.drawLine((int)mp.getX()/ConvertMap2Image.scaleFactor*zoomFactor+49, (int)mp.getY()*-1/ConvertMap2Image.scaleFactor*zoomFactor+ConvertMap2Image.width*zoomFactor, 
						((int)mp.getX()+5)/ConvertMap2Image.scaleFactor*zoomFactor+49, ((int)mp.getY()*-1/ConvertMap2Image.scaleFactor*zoomFactor+ConvertMap2Image.width*zoomFactor));
		}
		return img;
	}
	
	/**
	 * Writes the map-image.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeMap() throws IOException{
		ImageIO.write(this.image, "png", new File("mapimg.png"));
		System.out.println("Image written");
	}

	/**
	 * Sets the map.
	 * @param map  the new map
	 * @uml.property  name="map"
	 */
	public void setMap(MapFile map) {
		this.map = map;
	}

	/**
	 * Gets the map.
	 * @return  the map
	 * @uml.property  name="map"
	 */
	public MapFile getMap() {
		return map;
	}
}
