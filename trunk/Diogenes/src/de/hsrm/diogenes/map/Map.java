package de.hsrm.diogenes.map;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import de.fhwiesbaden.webrobbie.clientutil.awt.Map2ImageTransformerImpl;
import de.fhwiesbaden.webrobbie.clientutil.map.Map2ImageTransformer;
import de.fhwiesbaden.webrobbie.clientutil.map.MapFile;
import de.fhwiesbaden.webrobbie.clientutil.map.MapLine;
import de.fhwiesbaden.webrobbie.clientutil.map.MapPoint;
import de.fhwiesbaden.webrobbie.clientutil.map.RobotMapParser;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPMapPacket;
import de.hsrm.diogenes.connection.Connection;

/**
 * 
 * @author dirk
 * Class gets the map data from the robot, writes it into a file,
 * then parses the file into the program and saves it as a MapFile object.
 */
public class Map {

	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Connection c;
	/**
	 * @uml.property  name="mp"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private WRPMapPacket mp;
	/**
	 * @uml.property  name="data" multiplicity="(0 -1)" dimension="1"
	 */
	private byte[] data;
	/**
	 * @uml.property  name="w"
	 */
	private Writer w;
	/**
	 * @uml.property  name="roboParse"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private RobotMapParser roboParse;
	/**
	 * @uml.property  name="map"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private MapFile map;
	
	/**
	 * @uml.property  name="lines"
	 */
	private List<MapLine> lines;
	
	/**
	 * @uml.property  name="points"
	 */
	private List<MapPoint> points;
	
	/**
	 * @uml.property  name="img"
	 */
	private Image img;
	
	/**
	 * 
	 * @param c Object of the connection class
	 * @throws IOException
	 */
	public Map(Connection c) throws IOException{
		this.c = c;
		this.w = new FileWriter("map.map");
		this.lines = new ArrayList<MapLine>();
		this.points = new ArrayList<MapPoint>();
		parseMap();
		this.lines = this.map.getLineList();
		this.points = this.map.getPointList();
		
		
	}
	


	/**
	 * Creates the MapFile object, containing the map data of the robot
	 * @throws IOException
	 */
	public void parseMap() throws IOException{
	
		//Get the MapPacket
		this.mp = this.c.getDiogenes().getRobotMap();	
		this.data = mp.getByteData();	
		
		//Write the map data into a file
		this.w.write(new String(data));
		this.w.close();
	
		//Parse the map file and create the MapFile object
		this.roboParse = new RobotMapParser(new BufferedInputStream(new FileInputStream("map.map")));
		this.map = this.roboParse.parse();
		
		/*Map2ImageTransformerImpl transformer = new Map2ImageTransformerImpl(this.map, 300);
		this.img = transformer.transform();
		ImageIO.write((RenderedImage) img, "png", new File("mapimg"));
		System.out.println("Image written");*/
		
		new ConvertMap2Image(this.getMap(),400);
		this.img = ImageIO.read(new File("mapimg.png"));
		
	}
	
	
	public List<MapLine> getLines() {
		return lines;
	}

	public void setLines(List<MapLine> lines) {
		this.lines = lines;
	}

	public List<MapPoint> getPoints() {
		return points;
	}

	public void setPoints(List<MapPoint> points) {
		this.points = points;
	}



	/**
	 * @return
	 * @uml.property  name="map"
	 */
	public MapFile getMap() {
		return map;
	}



	/**
	 * @param map
	 * @uml.property  name="map"
	 */
	public void setMap(MapFile map) {
		this.map = map;
	}



	/**
	 * @param img
	 * @uml.property  name="img"
	 */
	public void setImg(Image img) {
		this.img = img;
	}



	/**
	 * @return
	 * @uml.property  name="img"
	 */
	public Image getImg() {
		return img;
	}
	
}
