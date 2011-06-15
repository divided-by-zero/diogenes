package de.hsrm.diogenes.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import de.fhwiesbaden.webrobbie.clientutil.map.MapLine;
import de.fhwiesbaden.webrobbie.clientutil.map.MapPoint;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.connection.Location;
import de.hsrm.diogenes.map.Map;

public class MapCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private Map map;
	private Connection connection;
	private int scale;
	
	public MapCanvas(Map m, Connection c, int s) {
		this.map = m;
		this.connection = c;
		this.scale = s;
		this.setPreferredSize(this.getSize());
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(350, 480);
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
//			g.drawLine((int)l.getP1().getX(), (int)l.getP1().getY(), (int)l.getP2().getX(), (int)l.getP2().getY());
		}
		// print robbie
		Location l = connection.getLocation();
//		Image i;
//		try {
//			i = ImageIO.read(new File("test1.jpg"));
//			System.out.println("drawing RoboImg to " + l.getX() / scale + "," + l.getY() / scale);
//			g.drawImage(i, l.getX() / scale, l.getY() / scale, this);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int robbie_x = (l.getX()/scale)+50;
		int robbie_y = (-l.getY()/scale)+400;
		//System.out.println("drawing Robbie to " + robbie_x + "," + robbie_y);
		g.drawOval(robbie_x, robbie_y, 5, 5);
		g.drawString("robbie", robbie_x+5, robbie_y);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
	
				repaint();
				try {
					Thread.sleep(1000);
					System.out.println("height: "+getSize().height);
					System.out.println("width: "+getSize().width);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		t.start();
	}
	
	public static void main(String[] args) throws WRPException, IOException, InterruptedException {
		JFrame f = new JFrame();
		Connection c = new Connection("localhost", 33333);
		MapCanvas mg = new MapCanvas(new Map(c), c, 70);
		f.add(mg);
		f.setSize(800, 600);
		f.pack();
		f.setVisible(true);
		
		while(true) {
			System.out.println("repainting frame");
			f.repaint();
			Thread.sleep(1000);
			c.getMove().moveForward(1000);
		}
	}
	
}
