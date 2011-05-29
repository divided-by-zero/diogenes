package de.hsrm.diogenes.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket server;
	
	public Server(int port) throws IOException {
		server = new ServerSocket(port);
		listen();
	}

	private void listen() {
		while (true) {
			Socket client;
			try {
				client = server.accept();
				InputStream input = client.getInputStream();
				OutputStream output = client.getOutputStream();
				// simple tests with integers first, needs to be done for objects!
				int z1 = input.read();
				int z2 = input.read();
				output.write(z1 + z2);
				output.flush();
				input.close();
				output.close();
			} catch (IOException e) {
				System.err.println("Error handling client");
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("starting server");
			new Server(55555);
			System.out.println("server closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
