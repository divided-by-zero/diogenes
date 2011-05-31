package de.hsrm.diogenes.socket;

import java.net.URL;

import javax.swing.ImageIcon;


//klasse um objekte zu erzeugen die ein bild, text und koordinaten enthalten, was wir dann alles unserem tollen server rüberballern damit der das anzeigen tut
public class PresObj {

		public ImageIcon icon;
		public String text;
		public double fromCoordX;
		public double toCoordX;
		public double fromCoordY;
		public double toCoordY;
		
		//konstruktor
		public PresObj(ImageIcon icon, String text, double fromCoordX, double toCoordX, double fromCoordY, double toCoordY){
				this.icon   = icon;
				this.text   = text;
				this.fromCoordX = fromCoordX;
				this.toCoordX = toCoordX;
				this.fromCoordY = fromCoordY;
				this.toCoordY = toCoordY;
		}
		//bild laden
		public ImageIcon loadPic(String pic){
			URL picUrl = ImageIcon.class.getResource( pic );
			ImageIcon icon = new ImageIcon( picUrl );
			return icon;
		}
		
		//testaufruf
		//Trinity bla = new Trinity(loadPic("sack.jpg"), "Hier steht irgendein text", 123.2, 132.4, 123.2, 132.4);
		
		
}
