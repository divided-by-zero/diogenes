package de.hsrm.diogenes.gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JFrame;
import de.fhwiesbaden.webrobbie.clientutil.map.MapLine;
import de.fhwiesbaden.webrobbie.clientutil.map.MapPoint;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.map.Map;

public class MapCanvas extends Canvas {

	private static final long serialVersionUID = 1L;
	private Map map;
	private Connection connection;
	private int scale;
	
	public MapCanvas(Map m, Connection c, int s) {
		this.map = m;
		this.connection = c;
		this.scale = s;
	}
	
	@Override
	public void paint(final Graphics g) {
		super.paint(g);
		// print points
		for (MapPoint p : map.getPoints()) {
			int x = (int) (p.getX() / scale);
			int y = (int) (p.getY() / scale);
//			System.out.println("drawing Oval to " + x + "," + y);
			g.drawOval(x+50, -y+400, 1, 1); // TODO offset!
		}
		// print lines
		for (MapLine l : map.getLines()) {
//			System.out.println("drawing Line somewhere");
			g.drawLine((int)l.getP1().getX(), (int)l.getP1().getY(), (int)l.getP2().getX(), (int)l.getP2().getY());
		}
		// print robbie
//		Location l = connection.getLocation();
//		Image i;
//		try {
//			i = ImageIO.read(new File("test1.jpg"));
//			System.out.println("drawing RoboImg to " + l.getX() / scale + "," + l.getY() / scale);
//			g.drawImage(i, l.getX() / scale, l.getY() / scale, this);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) throws WRPException, IOException {
		JFrame f = new JFrame();
		Connection c = new Connection("localhost", 33333);
		MapCanvas mg = new MapCanvas(new Map(c), c, 70);
		f.add(mg);
		f.setSize(800, 600);
		f.pack();
		f.setVisible(true);
	}
	
}
