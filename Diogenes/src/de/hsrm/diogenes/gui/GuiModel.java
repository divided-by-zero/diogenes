package de.hsrm.diogenes.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.hsrm.diogenes.common.ModelListener;

public class GuiModel {
	
	private List<ModelListener> listener;
	private String status;
	private JFrame frame;
	private JLabel label1;
	private JButton b1;
	private JPanel panel;
	
	public GuiModel(){
		this.listener = new ArrayList<ModelListener>();
	}
	
	public void addModelListener(ModelListener l){
		listener.add(l);
	}
	
	public void changeStatus(String status){
		this.status = status;
		fireModelChanged();
	}
	
	public String getStatus(){
		return this.status;
	}
	
	
	public void fireModelChanged(){
		for(ModelListener ml : listener){
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
			
			panel = new JPanel();
			b1 = new JButton("test");
			setFrame(new JFrame("Diogenes Projekt"));
			getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	        label1 = new JLabel("Statusbar");
	        label1.setHorizontalAlignment(JLabel.RIGHT);
	        label1.setVerticalAlignment(JLabel.BOTTOM);
	        label1.setPreferredSize(new Dimension(1024, 680));
	        
	        //frame.getContentPane().add(label1, BorderLayout.CENTER);

	        
	        
	        frame.setJMenuBar(makeMenuBar());
	        panel.add(b1);
	        panel.add(label1);
	        
	        frame.add(panel);
	        
	        frame.pack();
			frame.setVisible(true);
		}

		public void setFrame(JFrame frame) {
			this.frame = frame;
		}

		public JFrame getFrame() {
			return frame;
		}

		public void setLabel1(JLabel label1) {
			this.label1 = label1;
		}

		public JLabel getLabel1() {
			return label1;
		}

		public JButton getB1() {
			return b1;
		}

		public void setB1(JButton b1) {
			this.b1 = b1;
		}
		
}
	


