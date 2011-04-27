package de.hsrm.diogenes.map;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import de.fhwiesbaden.webrobbie.clientutil.map.MapFile;
import de.fhwiesbaden.webrobbie.clientutil.map.RobotMapParser;
import de.fhwiesbaden.webrobbie.wrp.packet.WRPMapPacket;
import de.hsrm.diogenes.connection.Client;

public class Map {

	private Client c;
	private WRPMapPacket mp;
	private byte[] data;
	private Writer w;
	private RobotMapParser roboParse;
	private MapFile map;
	
	public Map(Client c) throws IOException{
		this.c = c;
		w = new FileWriter("map.map");
		
	}
	
	public void readPacket() throws IOException{
	
		mp = this.c.getDiogenes().getRobotMap();
		data = mp.getByteData();
		String s = new String(data);
		
		w.write(s);
		w.close();
		roboParse = new RobotMapParser(new BufferedInputStream(new FileInputStream("map.map")));
		map = roboParse.parse();
		System.out.println(map.getNumLines());
		System.out.println(map.getNumPoints());
		
		
	}
	
	
}
