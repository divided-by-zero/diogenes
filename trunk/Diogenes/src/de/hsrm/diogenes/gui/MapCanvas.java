package de.hsrm.diogenes.gui;

import java.awt.Color;
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

/**
 * The Class MapCanvas.
 */
public class MapCanvas extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The map. */
	private Map map;
	
	/** The connection. */
	private Connection connection;
	
	/** The scale. */
	private int scale;
	
	/** The l. */
	private Location l;
	
	/** The robbie_x. */
	private int robbie_x;
	
	/** The robbie_y. */
	private int robbie_y ;
	
	/**
	 * Instantiates a new map canvas.
	 *
	 * @param m the map
	 * @param c the connection
	 * @param s the scale
	 */
	public MapCanvas(Map m, Connection c, int s) {
		this.map = m;
		this.connection = c;
		this.scale = s;
		this.setPreferredSize(this.getSize());
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(350, 480);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
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
		this.l = connection.getLocation();
		this.robbie_x = (l.getX()/scale)+50;
		this.robbie_y = (-l.getY()/scale)+400;
		
		g.setColor(Color.RED);
		g.drawOval(robbie_x, robbie_y, 5, 5);
		g.setColor(Color.BLACK);
		g.drawString("robbie", robbie_x, robbie_y);
		//g.setColor(Color.YELLOW);
		//g.drawLine(robbie_x, robbie_y, robbie_x+10, robbie_y+10);
		
		
	 	Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//if(connection.getMove().isRobiMoving()){
					repaint();
				//}
			}
		});
		t.start(); 
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws WRPException the wRP exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
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
