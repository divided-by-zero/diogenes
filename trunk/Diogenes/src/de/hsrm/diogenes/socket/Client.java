package de.hsrm.diogenes.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;

public class Client {

	/**
	 * Holding the socket-connection
	 */
	private Socket server;
	
	/**
	 * Holding the output-stream (for PresentationPacket-Objects)
	 */
	private ObjectOutputStream output;
	
	
	
	
	public static ImageIcon loadPic(String pic){
		URL picUrl = ImageIcon.class.getResource( pic );
		ImageIcon icon = new ImageIcon( picUrl );
		
		
		return icon;
	}
	
	
	
	
	/**
	 * Connects the client to a server with the address and port given.
	 * @param dest_addr Destination-Address of the running Server
	 * @param port Port of the running Server
	 * @throws UnknownHostException If the host couldn't be reached
	 * @throws IOException If streams within the connection couldn't be established 
	 */
	public void connect(String dest_addr, int port) throws UnknownHostException, IOException {
		System.out.println("client: connecting");
		server = new Socket(dest_addr, port);
		output = new ObjectOutputStream(server.getOutputStream());
		output.flush();
		System.out.println("client: connection established");
	}
	
	/**
	 * Sends a PresentationPacket to the Server who stores it locally.
	 * @param packet The packet to be sent to the Server
	 * @throws IOException If streams within the connection couldn't be established
	 */
	public void send(PresentationPacket packet) throws IOException {
		System.out.println("client: sending presentationpacket to server");
		output.writeObject(packet);
		output.flush();
	}

	/**
	 * Disconnects the client from the server and closes the streams.
	 * @throws IOException If closing the socket or the stream didn't work
	 */
	public void disconnect() throws IOException {
		System.out.println("client: disconnecting");
		server.close();
		output.close();
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Client c = new Client();
		
		try {
			String url = null;
			ImageIcon bild = loadPic(url);
			// this could be a button:
			c.connect("localhost", 55555);
			// this could be a button too:
			c.send(new PresentationPacket(bild, "the answer to everything"));
			// also this:
			c.disconnect();
		} catch (IOException e) {
			System.err.println("Streams couldn't be established");
			e.printStackTrace();
		}
	}
	
}
