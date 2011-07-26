package de.hsrm.diogenes.map;


import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import de.fhwiesbaden.webrobbie.clientutil.map.MapFile;
import de.fhwiesbaden.webrobbie.clientutil.map.MapLine;
import de.fhwiesbaden.webrobbie.clientutil.map.MapPoint;
import de.fhwiesbaden.webrobbie.clientutil.map.RobotMapParser;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPMapPacket;
import de.hsrm.diogenes.connection.Connection;

/**
 * Class gets the map data from the robot, writes it into a file,
 * then parses the file into the program and saves it as a MapFile object.
 * 
 * @author Dirk Stanke
 */
public class Map {

	/** The connection object. @uml.property  name="c" @uml.associationEnd  multiplicity="(1 1)" */
	private Connection c;
	
	/** The mapPacket given by the robot. @uml.property  name="mp" @uml.associationEnd  multiplicity="(1 1)" */
	private WRPMapPacket mp;
	
	/** A byte array for getting the mapdata from the robot. @uml.property  name="data" multiplicity="(0 -1)" dimension="1" */
	private byte[] data;
	
	/** A writer object for writing the map information into a file. @uml.property  name="w" */
	private Writer w;
	
	/** A parser to parse the mapfile into our program. @uml.property  name="roboParse" @uml.associationEnd  multiplicity="(1 1)" */
	private RobotMapParser roboParse;
	
	/** The parsed mapFile. @uml.property  name="map" @uml.associationEnd  multiplicity="(1 1)" */
	private MapFile map;
	
	/** A list for the lines of the mapFile. @uml.property  name="lines" */
	private List<MapLine> lines;
	
	/** A list for the points of the mapFile. @uml.property  name="points" */
	private List<MapPoint> points;
	
	/** The image for converting the mapfile into an image. @uml.property  name="img" */
	private Image img;
	
	/**
	 * Instantiates a new map.
	 *
	 * @param c Object of the connection class
	 * @throws IOException Signals that an I/O exception has occurred.
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
	 * Creates the MapFile object, containing the map data of the robot.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
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
		
	}
	
	
	/**
	 * Converts the mapFile to an image.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeIMG() throws IOException{
		
		new ConvertMap2Image(this.getMap(),400);
		this.img = ImageIO.read(new File("mapimg.png"));
	}
	
	
	/**
	 * Gets the lines.
	 *
	 * @return the lines
	 */
	public List<MapLine> getLines() {
		return lines;
	}

	/**
	 * Sets the lines.
	 *
	 * @param lines the new lines
	 */
	public void setLines(List<MapLine> lines) {
		this.lines = lines;
	}

	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public List<MapPoint> getPoints() {
		return points;
	}

	/**
	 * Sets the points.
	 *
	 * @param points the new points
	 */
	public void setPoints(List<MapPoint> points) {
		this.points = points;
	}



	/**
	 * Gets the map.
	 *
	 * @return the map
	 * @uml.property  name="map"
	 */
	public MapFile getMap() {
		return map;
	}



	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 * @uml.property  name="map"
	 */
	public void setMap(MapFile map) {
		this.map = map;
	}



	/**
	 * Sets the img.
	 *
	 * @param img the new img
	 * @uml.property  name="img"
	 */
	public void setImg(Image img) {
		this.img = img;
	}



	/**
	 * Gets the img.
	 *
	 * @return the img
	 * @uml.property  name="img"
	 */
	public Image getImg() {
		return img;
	}
	
}
