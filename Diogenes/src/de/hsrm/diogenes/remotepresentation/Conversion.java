package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.imageio.ImageIO;


public class Conversion {

	
//	
//	public File file;
//	public long size;
//	//ACHTUNG NOCH VON LONG NACH INT GECASTET!!!NUR ZU TESTZWECKEN!!!
//	public int bufSize;
//	public static int len;
//	public static byte[] buffer;
//	
	
//	public Conversion(){
//		file = new File("");
//		size = new Long(file.length());
//		bufSize = (int)size;
//		buffer =  new byte[bufSize];
//	}
//	
//	public void loadImg(BufferedImage img) throws IOException{
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ImageIO.write(img, "jpeg", baos);
//		byte[] imgData = baos.toByteArray();
//		System.out.println(imgData.length);
//		System.out.println("loadImg Method success!");
//		
//	}
	

//convert file in ByteArrayOutputStream 
	public static byte[] fileToBaos(File file) throws FileNotFoundException, IOException{
		long size = new Long(file.length());
		int bufSize = (int)size;
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		copyStream(new FileInputStream(file), baos, bufSize);
		byte[] data = baos.toByteArray();
		System.out.println(data.length);
		System.out.println("loadFile Method success!");
		return data;
	}
	
//reads file, writes in buffer	
	public static void copyStream(InputStream in, OutputStream out, int bufSize) throws IOException{
		
		int len;
		byte[] buffer;
		
		buffer =  new byte[bufSize];
		System.out.println("copystream");
		while((len = in.read(buffer))> 0) {
			System.out.println("writing");
			out.write(buffer, 0, len);
		}
		System.out.println("img copied");
		in.close();
		System.out.println("source closed");
		out.close();
		System.out.println("destination closed");
		
	}
	
	
	
//string in byte[] stecken

	public static byte[] stringToBaos(String s) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		copyStream(new StringBufferInputStream(s), baos, s.length());
		byte[] stringData = baos.toByteArray();
		System.out.println(stringData.length);
		System.out.println("stringToBaos success!");
		return stringData;
	}
	
	
	
	
//	public static byte[] rectToBaos(Rectangle r) throws IOException{
//		int x = (int)r.getX();
//		int y = (int)r.getY();
//		int w = (int)r.width;
//		int h = (int)r.height;
//		byte[] = ;
//		
//		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		BufferedInputStream rectIn;
//		copyStream(rectIn(r), baos);
//		byte[] rectData = baos.toByteArray();
//		System.out.println(rectData.length);
//		System.out.println("rectToBaos success!");
//		return rectData;
//	}
	
	
	
	//sozusagen die main!
//	public byte[] action(){
//	
//	try {
//		loadFile(file);
//		BufferedImage bufImg = ImageIO.read(file);
//		loadImg(bufImg);
//	} catch (IOException e) {
//		System.out.println("Image not Found!!");
//		e.printStackTrace();
//	}
//	return buffer;
//	}
//	
	
}
