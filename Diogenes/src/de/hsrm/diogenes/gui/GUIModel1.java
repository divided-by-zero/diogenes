package de.hsrm.diogenes.gui;

import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GUIModel1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mapPanel;
	private JPanel webcamPanel;
	private JPanel coordsPanel;
	private JPanel statusbarPanel;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JLabel l1;
	
	public GUIModel1() {
		
		this.setTitle("Diogenes robot control");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setJMenuBar(makeMenuBar());
		
		this.b1 = new JButton("Click me");
		this.b2 = new JButton("Click me");
		this.b3 = new JButton("Click me");
		this.l1 = new JLabel("Status");
		this.mapPanel = new JPanel();
		this.mapPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));
		this.webcamPanel = new JPanel();
		this.webcamPanel.setBorder(BorderFactory.createTitledBorder("Picture"));
		this.coordsPanel = new JPanel();
		this.coordsPanel.setBorder(BorderFactory
				.createTitledBorder("coordination points of the Webrobi "));
		this.statusbarPanel = new JPanel();
				
		/**
		 * Adds the needed components to the specific JPanel
		 */
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
	
	public void createPanels(){
		
		this.mapPanel.add(b1);
		this.webcamPanel.add(b2);
		this.coordsPanel.add(b3);
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
		new GUIModel1();
	}

}
