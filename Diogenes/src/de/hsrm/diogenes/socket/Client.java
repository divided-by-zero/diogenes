package de.hsrm.diogenes.socket;

import java.net.*;
import java.io.*;

public class Client {

	// members neccessary for client?
	
	public Client(String dest_addr, int port) {
		/* Tell client to what server he shall connect */
		Socket server;
		try {
			server = new Socket(dest_addr, port);
//			ObjectInputStream obIn = new ObjectInputStream(server.getInputStream());
//			OutputStream obOut = new ObjectOutputStream(server.getOutputStream());
			// simple tests with integers first, needs to be done for objects!
			InputStream input = server.getInputStream();
			OutputStream output = server.getOutputStream();
			output.write(5);
			output.write(10);
			output.flush();
			System.out.println("answer: " + input.read());
			server.close();
			input.close();
			output.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("connecting client");
		new Client("localhost", 55555);
		System.out.println("client closed");
	}

}
