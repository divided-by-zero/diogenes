package de.hsrm.diogenes.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//TODO proper exception handling!!
public class Server {

	private ServerSocket server_sock;
	
	private Socket current_client;
	
	private ObjectInputStream client_input;
	
	/**
	 * Output from server to client not necessary or used here yet.
	 */
	private ObjectOutputStream client_output;
	
	private PresentationPacket packet;
	
	public Server(int port) throws IOException {
		System.out.println("server: initializing serversocket");
		server_sock = new ServerSocket(port);
	}

	public void waitForClient() throws IOException {
		System.out.println("server: accepting clients...");
		current_client = server_sock.accept();
		client_input = new ObjectInputStream(current_client.getInputStream());
		client_output = new ObjectOutputStream(current_client.getOutputStream());
		System.out.println("server: client accepted, holding connection");
	}
	
	public void closeCurrentClient() throws IOException {
		System.out.println("server: closing current client");
		client_input.close();
		client_output.close();
		current_client.close();
	}
	
	public void send() throws IOException {
		System.out.println("server: sending current presentationpacket back to client");
		client_output.writeObject(packet);
		client_output.flush();
	}
	
	public void send(PresentationPacket packet) throws IOException {
		System.out.println("server: sending new presentationpacket back to client");
		client_output.writeObject(packet);
		client_output.flush();
	}
	
	public void recieve() throws IOException, ClassNotFoundException {
		System.out.println("server: recieving presentationpacket");
		packet = (PresentationPacket) client_input.readObject();
	}
	
	public static void main(String[] args) throws IOException {
		Server s = new Server(55555);
		s.waitForClient();
	}

}
