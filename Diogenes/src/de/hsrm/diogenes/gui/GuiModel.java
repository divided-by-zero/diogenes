package de.hsrm.diogenes.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import sun.awt.HorizBagLayout;

import de.hsrm.diogenes.common.ModelListener;

public class GuiModel {
	
	private List<ModelListener> listener;
	private String status;
	private JFrame frame;
	private JLabel statusLabel;
	private JButton b1;
	private JPanel mainPanel,sp_left,sp_right,splitPanePanel,statusBarPanel;
	private JSplitPane sp;
	
	public GuiModel(){
		this.listener = new ArrayList<ModelListener>();
		this.sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.makeGUI();
	}
	
	public void addModelListener(ModelListener l){
		this.listener.add(l);
	}
	
	public void changeStatus(String status){
		this.status = status;
		fireModelChanged();
	}
	
	public String getStatus(){
		return this.status;
	}
	
	
	public void fireModelChanged(){
		for(ModelListener ml : this.listener){
			ml.modelChanged();
		}
	}
		public JMenuBar makeMenuBar() {
			JMenuBar menuBar;
			JMenu menu_datei,menu_funktionen,menu_kamera,menu_ueber;
			JMenuItem menuItem;
			
			menuBar = new JMenuBar();
	        menu_datei = new JMenu("Datei");
	        menu_funktionen = new JMenu("Funktionen");
	        menu_kamera = new JMenu("Kamera");
	        menu_ueber = new JMenu("Über");
	        
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
		
		public void makeGUI() {
			
			this.mainPanel = new JPanel();
			this.splitPanePanel = new JPanel();
			this.statusBarPanel = new JPanel();
			this.sp_left = new JPanel();
	        this.sp_right = new JPanel();
			this.b1 = new JButton("test");
			this.mainPanel.setLayout(new GridLayout(1,1));
			this.statusLabel = new JLabel("Statusbar");
			this.setFrame(new JFrame("Diogenes Projekt"));
			
			getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	        this.statusLabel.setHorizontalAlignment(JLabel.RIGHT);
	        this.statusLabel.setVerticalAlignment(JLabel.BOTTOM);
	        this.statusLabel.setPreferredSize(new Dimension(1024, 680));
	        
	        //frame.getContentPane().add(label1, BorderLayout.CENTER);
	        
	        this.splitPanePanel.setSize(300, 200);
	        this.statusBarPanel.setSize(1024,10);

	        this.sp_right.add(this.b1);
	        
	        this.splitPane();
	        this.initPanel();
	        
	        this.frame.setJMenuBar(makeMenuBar());
	        this.frame.add(this.mainPanel);
	        this.frame.pack();
			this.frame.setVisible(true);
		}

		public void setFrame(JFrame frame) {
			this.frame = frame;
		}

		
		public JFrame getFrame() {
			return this.frame;
		}

		public void setStatusLabel(JLabel label1) {
			this.statusLabel = label1;
		}

		public JLabel getStatusLabel() {
			return this.statusLabel;
		}

		public JButton getB1() {
			return this.b1;
		}

		public void setB1(JButton b1) {
			this.b1 = b1;
		}
		
		public void splitPane() {
			this.sp.setLeftComponent(this.sp_left);
			this.sp.setRightComponent(this.sp_right);
		}
		
		public void initPanel() {
			this.splitPanePanel.add(this.sp);
			this.statusBarPanel.add(this.statusLabel);
			this.mainPanel.add(this.splitPanePanel);
			this.mainPanel.add(statusBarPanel);
		}
}
	


