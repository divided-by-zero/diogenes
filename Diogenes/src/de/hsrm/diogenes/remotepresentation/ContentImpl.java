package de.hsrm.diogenes.remotepresentation;

import java.net.URL;

import javax.swing.ImageIcon;

//klasse um objekte zu erzeugen die ein bild, text und koordinaten enthalten, was wir dann alles unserem tollen server rüberballern damit der das anzeigen tut
public class ContentImpl implements Content {

		private ImageIcon image;
		private String text;
		private int fromCoordX;
		private int toCoordX;
		private int fromCoordY;
		private int toCoordY;
		
		//konstruktor
		public ContentImpl(ImageIcon icon, String text, int fromCoordX, int toCoordX, int fromCoordY, int toCoordY) {
				this.image 		= icon;
				this.text		= text;
				this.fromCoordX	= fromCoordX;
				this.toCoordX	= toCoordX;
				this.fromCoordX	= fromCoordY;
				this.toCoordY	= toCoordY;
		}
		
							
		//bild laden										/* TODO: not necessary, ImageIcon(String)! */
		public static ImageIcon loadPic(String pic) {
			URL picUrl = ImageIcon.class.getResource( pic );
			ImageIcon icon = new ImageIcon( picUrl );
			return icon;
		}
		
		@Override
		public ImageIcon getImage() {
			return image;
		}
		
		@Override
		public String getDescriptionText() {
			return text;
		}
		
		@Override
		public String getAdditionalText() {
			String result = "Invoked between (" + fromCoordX + "," + fromCoordY + ") " +
							"and (" + toCoordX + "," + toCoordY + ")";
			return result;
		}
		
}
