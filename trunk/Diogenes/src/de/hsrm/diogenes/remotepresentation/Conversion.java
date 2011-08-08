package de.hsrm.diogenes.remotepresentation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

/**
 * The Class Conversion.
 */
@SuppressWarnings("deprecation")
public class Conversion {

	/**
	 * Converts a file to a Byte Array Output Stream.
	 * @param file the file to be converted 
	 * @return byte array which consists of the file-data
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] fileToBaos(File file) throws FileNotFoundException, IOException {
		long size = new Long(file.length());
		int bufSize = (int) size;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		copyStream(new FileInputStream(file), baos, bufSize);
		byte[] data = baos.toByteArray();
		return data;
	}

	/**
	 * Converts a String to a Byte Array Output Stream
	 * @param s the String to be converted
	 * @return byte array which consists of the String
	 * @throws IOException
	 */
	public static byte[] stringToBaos(String s) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		copyStream(new StringBufferInputStream(s), baos, s.length());
		byte[] stringData = baos.toByteArray();
		return stringData;
	}

	/**
	 * Copies the bytestream.
	 * @param in Input Stream
	 * @param out Output Stream
	 * @param bufSize Size of the Buffer
	 * @throws IOException
	 */
	private static void copyStream(InputStream in, OutputStream out, int bufSize) throws IOException {
		int len;
		byte[] buffer;
		buffer = new byte[bufSize];
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

}
