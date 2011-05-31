package de.hsrm.diogenes.socket;

import java.net.URL;

import javax.swing.ImageIcon;

public class Trinity {

		public ImageIcon icon;
		public String text;
		public double coordX;
		public double coordY;
	
		public Trinity(ImageIcon icon, String text, double coordX, double coordY){
				this.icon   = icon;
				this.text   = text;
				this.coordX = coordX;
				this.coordY = coordY;
			
		}
		
		public ImageIcon loadPic(String pic){
			URL picUrl = ImageIcon.class.getResource( pic );
			ImageIcon icon = new ImageIcon( picUrl );
			return icon;
		}
		
		
		
}
