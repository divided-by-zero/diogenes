package de.hsrm.diogenes.gui;

import java.awt.Color;

import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;


public class GuiModel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mapPanel;
	private JPanel webcamPanel;
	private JPanel coordsPanel;
	private JPanel statusbarPanel;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private ImageIcon screen;
	private ImageIcon map;
	private Connection c;
	
	
	public GuiModel(Connection c) {
		this.c = c;
		this.setTitle("Diogenes robot control");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setJMenuBar(makeMenuBar());

		this.l1 = new JLabel("Status");
		
		this.screen = new ImageIcon(getClass().getResource("../img/scrat.jpg"));
		this.map = new ImageIcon(getClass().getResource("../img/map.jpg"));
		
		this.l2 = new JLabel(this.screen);
		this.l3 = new JLabel(this.map);
		this.l4 = new JLabel("X-Koordinate: 0 " +
							"Y-Koordinate: 0");
		
		this.mapPanel = new JPanel();
		this.mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.webcamPanel = new JPanel();
		this.webcamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.coordsPanel = new JPanel();
		this.coordsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.statusbarPanel = new JPanel();
				
		
		createPanels();
		
		/**
		 * Adds the different panels onto our JFrame with the given GridBagConstraints
		 * create(x, y, gridwidth, gridheight) 
		 */
		this.add(mapPanel,GridBagConstraintsFactory.create(0, 0, 1, 2));
		this.add(webcamPanel,GridBagConstraintsFactory.create(1,0,1,1));
		this.add(coordsPanel,GridBagConstraintsFactory.create(1,1,1,1));
		this.add(statusbarPanel,GridBagConstraintsFactory.create(0, 3, 1, 1));
		
		this.pack();
		this.setVisible(true);
		
	}
	
	/*@Override
	public Dimension getPreferredSize() {
		return new Dimension(1024, 768);
	}*/
	
	/**
	 * Adds the needed components to the specific JPanel
	 */
	public void createPanels(){
		
		this.mapPanel.add(l3);
		this.webcamPanel.add(c.getCameraData().getComponent(0));
		this.coordsPanel.add(l4);
		this.statusbarPanel.add(l1);
		
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
        menu_datei = new JMenu("Datei");
        menu_funktionen = new JMenu("Funktionen");
        menu_kamera = new JMenu("Kamera");
        menu_ueber = new JMenu("About");
        
        menuItem = new JMenuItem("Verbinden");
        menu_datei.add(menuItem);
        menuItem = new JMenuItem("Simulator?");
        menu_datei.add(menuItem);
        
        menuItem = new JMenuItem("Map");
        menu_funktionen.add(menuItem);
        menuItem = new JMenuItem("Map visualisieren");
        menu_funktionen.add(menuItem);
        
        menuItem = new JMenuItem("Bild machen");
        menu_kamera.add(menuItem);
        menuItem = new JMenuItem("Personen finden");
        menu_kamera.add(menuItem);
        menuItem = new JMenuItem("Modus umschalten");
        menu_kamera.add(menuItem);
        
        menuItem = new JMenuItem("about");
        menu_ueber.add(menuItem);
        menuItem = new JMenuItem("Info");
        menu_ueber.add(menuItem);
        menuItem = new JMenuItem("Hilfe");
        menu_ueber.add(menuItem);
        
        menuBar.add(menu_datei);
		menuBar.add(menu_funktionen);
		menuBar.add(menu_kamera);
		menuBar.add(menu_ueber);
		return menuBar;
	}
	
	public static void main(String[] args) {
		try {
			new GuiModel(new Connection());
		} catch (WRPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
