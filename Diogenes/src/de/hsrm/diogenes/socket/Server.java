package de.hsrm.diogenes.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Server {

	/**
	 * A packet containing a picture and a text to be displayed by a
	 * GUI (use getPacket). Will be updated by a remote client using sockets.
	 */
	private PresentationPacket packet;
	
	/**
	 * Instantiating a ServerSocket using the given portnumber. Will automagically
	 * accept incoming client-queries in an infinite loop, receive their PresentationPacket
	 * and storing it locally in the member-variable. Disconnects the client afterwards
	 * and accepts new incoming client-queries again.
	 * @param port The portnumber to be used for remote access
	 * @throws IOException If streams within the connection couldn't be established
	 * @throws ClassNotFoundException If the Object sent by the client couldn't be read 
	 */
	public Server(int port) throws IOException, ClassNotFoundException {
		System.out.println("server: initializing serversocket");
		ServerSocket server_sock = new ServerSocket(port);
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
	}

	/**
	 * Returns a packet containing a picture and a text to be displayed by a
	 * GUI. Will be updated by a remote client using sockets. 
	 * @return The packet containing a picture and a text for visualization
	 */
	public PresentationPacket getPacket() {
		return packet;
	}
	
	
	
	public static void main(String[] args) {
			
		
		
		try {
				new Server(55555);
			} catch (IOException e) {
				System.err.println("Error handling server-input/output");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("Error recieving PresentationPacket-Object");
				e.printStackTrace();
			}
	}

}
