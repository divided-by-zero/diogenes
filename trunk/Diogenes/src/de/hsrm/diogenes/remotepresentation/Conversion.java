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
 * @author Philip Koch
 */
@SuppressWarnings("deprecation")
public class Conversion {

	/**
	 * File to baos.
	 * @param file
	 * @return
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
	 * String to baos.
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static byte[] stringToBaos(String s) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		copyStream(new StringBufferInputStream(s), baos, s.length());
		byte[] stringData = baos.toByteArray();
		return stringData;
	}

	/**
	 * Copies the stream.
	 * @param in
	 * @param out
	 * @param bufSize
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
