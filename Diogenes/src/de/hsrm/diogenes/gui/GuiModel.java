package de.hsrm.diogenes.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.camera.CameraData;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.connection.Location;
import de.hsrm.diogenes.map.Map;
import de.hsrm.diogenes.map.Robbie;
import de.hsrm.diogenes.remotepresentation.Client;
import de.hsrm.diogenes.remotepresentation.ExceptionListener;
import de.hsrm.diogenes.remotepresentation.PacketContainer;


public class GuiModel extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="mapPanel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JPanel mapPanel;
	/**
	 * @uml.property  name="webcamPanel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final JPanel webcamPanel;
	/**
	 * @uml.property  name="coordsPanel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JPanel coordsPanel;
	/**
	 * @uml.property  name="statusbarPanel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JPanel statusbarPanel;
	/**
	 * @uml.property  name="l1"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel l1;
	/**
	 * @uml.property  name="l2"
	 * @uml.associationEnd  readOnly="true"
	 */
	private JLabel l2;
	/**
	 * @uml.property  name="l3"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel l3;
	/**
	 * @uml.property  name="l4"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel l4;
	/**
	 * @uml.property  name="screen"
	 * @uml.associationEnd  readOnly="true"
	 */
	private ImageIcon screen;
	/**

	/**
	 * @uml.property  name="c"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Connection c;
	/**
	 * @uml.property  name="camEnabled"
	 */
	private boolean camEnabled;
	
	private Map map;
	private ImageIcon mapImg;
	
	private Client praesi_client;
	
	private Robbie robi;
	private JScrollPane scroller;
	
	public GuiModel(Connection c, Map map) throws IOException, InterruptedException {
		this.c = c;
		this.map = map;
		this.setTitle("Diogenes robot control");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setJMenuBar(makeMenuBar());
		this.camEnabled = true;

		this.l1 = new JLabel("Status");
		
		//this.l2 = new JLabel(new ImageIcon(map.getImg()));
		
		//this.mapPanel = new JPanel();
		
		this.webcamPanel = new JPanel();
		this.webcamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.coordsPanel = new JPanel();
		this.coordsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.statusbarPanel = new JPanel();
		this.robi = new Robbie(c);
		
		
		createPanels();
		
		
		
		/**
		 * Adds the different panels onto our JFrame with the given GridBagConstraints
		 * create(x, y, gridwidth, gridheight) 
		 */
		
		this.scroller = new JScrollPane(new MapCanvas(new Map(this.c), this.c, 70, 50, 400), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//this.scroller = new JScrollPane(new MapPanel(this.map, this.c), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scroller.setPreferredSize(new Dimension(350, 410));
		this.add(scroller, GridBagConstraintsFactory.create(0, 0, 1, 2));
		this.add(webcamPanel, GridBagConstraintsFactory.create(1,0,1,1));
		//this.add(coordsPanel,GridBagConstraintsFactory.create(1,1,1,1));
		this.add(new ButtonPanel(this.c), GridBagConstraintsFactory.create(1,1,1,1));
		this.add(statusbarPanel, GridBagConstraintsFactory.create(0, 3, 2, 1));
	
		this.pack();
		this.setVisible(true);
		
		//refreshCamPanel(this.webcamPanel);
		
		
	}
	
	/*@Override
	public Dimension getPreferredSize() {
		return new Dimension(1024, 768);
	}*/
	
	/**
	 * Adds the needed components to the specific JPanel
	 */
	public void createPanels(){
		
		//this.mapPanel.add(l3);
		this.statusbarPanel.add(l1);
		//this.mapPanel.add(this.l2);
		//this.mapPanel.add(new JLabel(this.robi.getImg()));
		
		if(this.c.isCamData()){
			webcamPanel.add(c.getCameraData().getCam());
			Timer t = new Timer(210, new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					webcamPanel.removeAll();
					webcamPanel.add(c.getCameraData().getCam());
					webcamPanel.validate();				
				}
	
			});
			t.start();
				
		}else{
			this.webcamPanel.add(new JLabel(new ImageIcon(getClass().getResource("../img/scrat.png"))));
		}
	}
	
	
	/**
	 * Constructs our JMenuBar
	 * @return the constructed JMenuBar
	 */
	public JMenuBar makeMenuBar() {
		
		JMenuBar menuBar;
		JMenu menu_datei,menu_funktionen,menu_kamera,menu_ueber;
		JMenuItem menuItem;
		
		menuBar = new JMenuBar();
        menu_datei = new JMenu("File");
        menu_funktionen = new JMenu("Functions");
        menu_kamera = new JMenu("Camera");
        menu_ueber = new JMenu("About");
        
        menuItem = new JMenuItem("Connect");
        menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!c.isConnected()){
						c.run("10.18.72.254", 33333);
						l1.setText("connected");
						c.setConnected(true);
					}
				} catch (WRPException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
        menu_datei.add(menuItem);
        menuItem = new JMenuItem("Disconnect");
        menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.getDiogenes().disconnect();
					l1.setText("disconnected");
					c.setConnected(false);
				} catch (WRPException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
        menu_datei.add(menuItem);
        
        menuItem = new JMenuItem("Simulator?");
        menu_datei.add(menuItem);
        
        menuItem = new JMenuItem("Wander the given points");
        menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setStartWander(true);
				
			}
		});
        menu_funktionen.add(menuItem);
        menuItem = new JMenuItem("Visualize map");
        menu_funktionen.add(menuItem);
        
        menuItem = new JMenuItem("Start presentation mode");
        menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				praesi_client = new Client("localhost", 55555, new ExceptionListener(), new PacketContainer(), new Location(1, 2, 3));
				//praesi_client.run();
				l1.setText("Presentation mode started");
				
			}
		});
        menu_funktionen.add(menuItem);
        
        menuItem = new JMenuItem("Stop presentation mode");
        menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					praesi_client.disconnect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				l1.setText("Presentation mode stopped");
				
			}
		});
        menu_funktionen.add(menuItem);
        
        
        menuItem = new JMenuItem("Turn camera left");
        menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.getCameraData().adjustCameraLeft(2000);
				} 
				 catch (WRPException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
        menu_kamera.add(menuItem);
        
        menuItem = new JMenuItem("Take photo");
          
        
        menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.getCameraData().takePhoto();
					l1.setText("Photo taken!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
        menu_kamera.add(menuItem);
        
        menuItem = new JMenuItem("Wander");
        menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							l1.setText("Wander initiated");
							c.getMove().wander(new Point(3367, -1747), new Point(6274, 1620), new Point(-334, 1570));
						} catch (WRPException e2) {
							e2.printStackTrace();
						}
						
					}
				});
				t.start();
			}
		});
        menu_funktionen.add(menuItem);
        menuItem = new JMenuItem("Find person");
        menu_kamera.add(menuItem);
        menuItem = new JMenuItem("Switch modi");
        menu_kamera.add(menuItem);
        
        
        menuItem = new JMenuItem("Info");
        menu_ueber.add(menuItem);
        menuItem = new JMenuItem("Help");
        menu_ueber.add(menuItem);
        
        menuBar.add(menu_datei);
		menuBar.add(menu_funktionen);
		menuBar.add(menu_kamera);
		menuBar.add(menu_ueber);
		return menuBar;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		
		 SwingUtilities.invokeLater(new Runnable() {

		      @Override
		      public void run() {
		     
		    		try {
		    			//Connection c = new Connection("10.18.72.254", 33333);
		    			Connection c = new Connection("localhost", 33333);
		    			new GuiModel(c, new Map(c));
		    		} catch (WRPException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      }
		    });
		  }

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel l1) {
		this.l1 = l1;
	}

	public void setPraesi_client(Client praesi_client) {
		this.praesi_client = praesi_client;
	}

	public Client getPraesi_client() {
		return praesi_client;//this.add(this.robi, GridBagConstraintsFactory.create(0, 0, 1, 1));
		//this.add(this.cam, GridBagConstraintsFactory.create(2, 0, 1, 1));
	}

}
