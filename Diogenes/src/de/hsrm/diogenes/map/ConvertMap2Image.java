package de.hsrm.diogenes.map;

import java.awt.Color;
import java.awt.Graphics;
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
 */
public class ConvertMap2Image extends Map2ImageTransformer<BufferedImage> {

	
	/** The map. */
	private MapFile map;
	
	/** The lines. */
	private List<MapLine> lines;
	
	/** The points. */
	private List<MapPoint> points;
	
	/** The image. */
	private BufferedImage image;
	
	private static int scaleFactor;
	private static int zoomFactor;
	private static int width;
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
	
	public static void zoomIn() {
		ConvertMap2Image.scaleFactor /= ConvertMap2Image.zoomFactor;
		ConvertMap2Image.width *= ConvertMap2Image.zoomFactor;
		ConvertMap2Image.height *= ConvertMap2Image.zoomFactor;
	}
	
	/* (non-Javadoc)
	 * @see de.fhwiesbaden.webrobbie.clientutil.map.Map2ImageTransformer#transform()
	 */
	
	/**
	 * Creates an image with the given lines and points from the mapfile
	 */
	@Override
	public BufferedImage transform() {
		//BufferedImage img = new BufferedImage(this.mapFile.getMapWidth(), this.mapFile.getMapHeight(), BufferedImage.TYPE_INT_RGB);
		
		BufferedImage img = new BufferedImage(ConvertMap2Image.width*zoomFactor, ConvertMap2Image.height*zoomFactor, BufferedImage.TYPE_INT_ARGB);//350, 410
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
	 *
	 * @param map the new map
	 */
	public void setMap(MapFile map) {
		this.map = map;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public MapFile getMap() {
		return map;
	}
	
//	public void setZoomFactor(int zoomFactor) {
//		ConvertMap2Image.zoomFactor = zoomFactor;
//	}
//	
//	public int getZoomFactor() {
//		return ConvertMap2Image.zoomFactor;
//	}
//	
//	public void setWidth(int width) {
//		ConvertMap2Image.width*scaleFactor = width;
//	}
//	
//	public int getWidth() {
//		return ConvertMap2Image.width;
//	}
//	
//	public void setHeight(int height) {
//		ConvertMap2Image.height = height;
//	}
//	
//	public int getHeight() {
//		return ConvertMap2Image.height;
//	}
}
