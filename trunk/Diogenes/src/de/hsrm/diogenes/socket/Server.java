package de.hsrm.diogenes.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;

public class Server extends Thread {

	/**
	 * A packet containing a picture and a text to be displayed by a GUI (use
	 * getPacket). Will be updated by a remote client using sockets.
	 */
	private PresentationPacket packet;

	private int port;

	public Server(int port) {
		this.port = port;
		// first "welcome"-paket
		packet = new PresentationPacket(new ImageIcon("photo"), "<html><B>No Information gathered so far...</B>");
	}

	public void run() {
		System.out.println("server: initializing serversocket");
		ServerSocket server_sock;
		try {
			server_sock = new ServerSocket(port);
			// infinite loop for accepting clients, receiving data and disconnecting them
			while (true) {
				// accept client
				System.out.println("server: accepting clients...");
				Socket current_client = server_sock.accept();
				ObjectInputStream client_input = new ObjectInputStream(current_client.getInputStream());
				System.out.println("server: client accepted, holding connection");
				// receive data
				System.out.println("server: receiving presentationpacket");
				packet = (PresentationPacket) client_input.readObject();
				// show what you got
				System.out.println("server: stored: " + packet.getPicture() + ", " + packet.getText());
				// close connection
				current_client.close();
				client_input.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns a packet containing a picture and a text to be displayed by a
	 * GUI. Will be updated by a remote client using sockets.
	 * 
	 * @return The packet containing a picture and a text for visualization
	 */
	public PresentationPacket getPacket() {
		return packet;
	}

	public static void main(String[] args) {
		Server s = new Server(55555);
		s.start();
	}

}
