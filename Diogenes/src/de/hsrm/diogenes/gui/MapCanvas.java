package de.hsrm.diogenes.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class MapCanvas extends JPanel implements MouseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The map. */
	private Map map;
	
	/** The connection. */
	private Connection connection;
	
	/** The scale. */
	private int scale;
	
	/** The location of the robi */
	private Location l;
	
	/** The robbie_x. */
	private int robbie_x;
	
	/** The robbie_y. */
	private int robbie_y ;
	
	/** The clicked. */
	private boolean clicked;
	
	/** The clicked list. */
	private List<Point> clickedList;
	
	/** The converted list. */
	private List<Point> convertedList;
	
	/** The move xfactor. */
	private int moveXfactor;
	
	/** The move yfactor. */
	private int moveYfactor;
	
	
	/**
	 * Instantiates a new map canvas.
	 *
	 * @param m the map
	 * @param c the connection
	 * @param s the scale
	 * @param moveXfactor the move xfactor
	 * @param moveYfactor the move yfactor
	 */
	public MapCanvas(Map m, Connection c, int s, int moveXfactor, int moveYfactor) {
		this.map = m;
		this.connection = c;
		this.scale = s;
		this.moveXfactor = moveXfactor;
		this.moveYfactor = moveYfactor;
		this.clicked = false;
		this.clickedList = new ArrayList<Point>();
		this.convertedList = new ArrayList<Point>();
		this.addMouseListener(this);
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
			g.drawOval(x+this.moveXfactor, -y+this.moveYfactor, 1, 1); // TODO offset!
		}
		// print lines
		for (MapLine l : map.getLines()) {
//			System.out.println("drawing Line somewhere");
			g.drawLine((int)l.getP1().getX(), (int)l.getP1().getY(), (int)l.getP2().getX(), (int)l.getP2().getY());
		}
		
		// print robbie
		this.l = connection.getLocation();
		this.robbie_x = (l.getX()/scale)+this.moveXfactor;
		this.robbie_y = (-l.getY()/scale)+this.moveYfactor;
		
		g.setColor(Color.RED);
		g.drawOval(robbie_x, robbie_y, 5, 5);
		g.setColor(Color.BLACK);
		g.drawString("robbie", robbie_x, robbie_y);
		//g.setColor(Color.YELLOW);
		//g.drawLine(robbie_x, robbie_y, robbie_x+10, robbie_y+10);
		
		if(this.clicked){
			g.setColor(Color.BLUE);
			if(!connection.isWanderFinished()){
				for(Point p : this.clickedList){
					g.drawOval(p.x, p.y, 5, 5);
				}
				
			}else{
				this.clickedList.clear();
				this.convertedList.clear();
				this.connection.setWanderFinished(false);
			}
			
			if(connection.isStartWander()){
				connection.setStartWander(false);
				
				for(Point p : this.clickedList){
					this.convertedList.add(new Point(calculateRobiPoint(p)));
				}
				
				Thread t = new Thread(new Runnable() {
								
					@Override
					public void run() {
								
						try {
							connection.getMove().wander(convertedList);
						}
						catch (WRPException e) {
							e.printStackTrace();
						}
									
					}
				});
				t.start();
			}
			
		}
		
		
	 	Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//if(connection.getMove().isRobiMoving()){
					repaint();
				//}
			}
		});
		thread.start(); 
	}
	
	/**
	 * Sets the points.
	 *
	 * @param ev the new points
	 */
	public void setPoints(MouseEvent ev){
		
		Point p = new Point(ev.getX(), ev.getY());
		System.out.println(p);
		this.clickedList.add(p);
		this.clicked = true;
		
	}
	
	/**
	 * Calculates the exact point for the robot coordinate system.
	 *
	 * @param p the point from the panel coordinate system
	 * @return the point converted to robot coordinate system
	 */
	public Point calculateRobiPoint(Point p){
		
		int x = ((int)p.getX() * this.scale);
		int y =  (((int)p.getY() * this.scale) * -1);
		
		if(checkSign(x) == -1){
			x = x + (this.moveXfactor * this.scale);
		}
		else{
			x = x - (this.moveXfactor * this.scale);
		}
		
		if(checkSign(y) == -1){
			y = y + (this.moveYfactor * this.scale);
		}
		else{
			y = y - (this.moveYfactor * this.scale);
		}
		
		return new Point(x, y);
	}
	
	/**
	 * Checks whether an integer is signed or not.
	 *
	 * @param i the integer
	 * @return -1 if signed, 0 if 0, +1 if unsigned
	 */
	int checkSign(int i) {
	    if (i == 0) return 0;
	    if (i >> 31 != 0) return -1;
	    return +1;
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
		MapCanvas mg = new MapCanvas(new Map(c), c, 70, 50 , 400);
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


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent ev) {
		setPoints(ev);
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
