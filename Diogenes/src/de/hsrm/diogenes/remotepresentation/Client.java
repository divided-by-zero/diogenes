package de.hsrm.diogenes.remotepresentation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

	private String dest_addr;
	
	private int port;
	
	private ThreadExceptionListener tel;
	
	/**
	 * Holding the socket-connection
	 */
	private Socket server;
	
	/**
	 * Holding the output-stream (for PresentationPacket-Objects)
	 */
	private ObjectOutputStream output;
	
	public Client(String dest_addr, int port, ThreadExceptionListener tel) {
		this.dest_addr = dest_addr;
		this.port = port;
		this.tel = tel;
	}
	
	public void run() {
		System.out.println("client: connecting");
		try {
			server = new Socket(dest_addr, port);
			output = new ObjectOutputStream(server.getOutputStream());
			output.flush();
			System.out.println("client: connection established");
		} catch (UnknownHostException e) {
			tel.notifyException(e);
		} catch (IOException e) {
			tel.notifyException(e);
		}
	}
	
	/**
	 * Sends a PresentationPacket to the Server who stores it locally.
	 * @param packet The packet to be sent to the Server
	 * @throws IOException If streams within the connection couldn't be established
	 */
	public void send(Packet packet) throws IOException {
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
	
//	public static void main(String[] args) throws ClassNotFoundException {
//		Client c = new Client("localhost", 55555);
//		try {
//			c.start();
//			c.send(new Packet(new ContentImpl(new ImageIcon("example.jpg"), "that's new!", 17, 4, 0, 42)));
//			c.disconnect();
//		} catch (IOException e) {
//			System.err.println("Streams couldn't be established");
//			e.printStackTrace();
//		}
//	}
	
}
