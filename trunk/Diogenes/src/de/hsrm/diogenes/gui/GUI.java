package de.hsrm.diogenes.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class GUI {
	
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
	
	public static void makeGUI() {
		JFrame frame;
		JLabel label1;
		
		frame = new JFrame("Diogenes Projekt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        label1 = new JLabel("Statusbar");
        label1.setHorizontalAlignment(JLabel.RIGHT);
        label1.setVerticalAlignment(JLabel.BOTTOM);
        label1.setPreferredSize(new Dimension(1024, 680));
        
        frame.getContentPane().add(label1, BorderLayout.CENTER);

        
        GUI demo = new GUI();
        frame.setJMenuBar(demo.makeMenuBar());
        
        frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                makeGUI();
            }
        });

	}
}
