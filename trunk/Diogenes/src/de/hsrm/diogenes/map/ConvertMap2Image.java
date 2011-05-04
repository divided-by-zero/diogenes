package de.hsrm.diogenes.map;

import java.awt.Color;
import java.awt.Graphics;
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
		
		this.image = transform();
		writeMap();
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
		
		
		BufferedImage img = new BufferedImage(8000, 6000, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.BLUE);
		for(MapLine ml : lines){
			g.drawLine((int)ml.getP1().getX(), (int)ml.getP1().getY()*-1, (int)ml.getP2().getX(), (int)ml.getP2().getY()*-1);
		}
		g.setColor(Color.WHITE);
		for(MapPoint mp : points){
			g.drawLine((int)mp.getX(), (int)mp.getY()*-1, (int)mp.getX()+5, ((int)mp.getY()*-1));
		}
		
		return img;
	}
	
	/**
	 * Writes the map-image.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeMap() throws IOException{
		ImageIO.write(this.image, "png", new File("mapimg"));
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

}
