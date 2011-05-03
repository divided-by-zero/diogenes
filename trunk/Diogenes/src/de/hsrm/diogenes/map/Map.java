package de.hsrm.diogenes.map;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import de.fhwiesbaden.webrobbie.clientutil.map.MapFile;
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
	 * 
	 * @param c Object of the connection class
	 * @throws IOException
	 */
	public Map(Connection c) throws IOException{
		this.c = c;
		this.w = new FileWriter("map.map");
		parseMap();
		
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
		
	}
	
}
