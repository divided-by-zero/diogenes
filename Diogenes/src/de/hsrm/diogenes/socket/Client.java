package de.hsrm.diogenes.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//TODO proper exception handling!!
public class Client {

	private Socket server;
	
	/**
	 * Input from server to client not necessary or used here yet
	 */
	private ObjectInputStream input;
	
	private ObjectOutputStream output;
	
	public Client() {}

	public void connect(String dest_addr, int port) throws IOException {
		System.out.println("client: connecting");
		server = new Socket(dest_addr, port);
		input = new ObjectInputStream(server.getInputStream());
		output = new ObjectOutputStream(server.getOutputStream());
	}
	
	public void disconnect() throws IOException {
		System.out.println("client: disconnecting");
		server.close();
		input.close();
		output.close();
	}
	
	public void send(PresentationPacket packet) throws IOException {
		System.out.println("client: sending presentationpacket to server");
		output.writeObject(packet);
		output.flush();
	}

	public void recieve() throws IOException, ClassNotFoundException {
		System.out.println("client: recieving presentationpacket from server");
		PresentationPacket spp = (PresentationPacket) input.readObject();
		System.out.println("answer: picture=" + spp.getPicture() + " text=" + spp.getText());
	}
	
	public static void main(String[] args) throws IOException {
		Client c = new Client();
		c.connect("localhost", 55555);
	}
	
}
