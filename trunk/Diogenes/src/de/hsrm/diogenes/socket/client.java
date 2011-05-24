package de.hsrm.diogenes.socket;

import java.net.*;
import java.io.*;


public class client {

/*
 * Create constant Variables for Socket Connection
 * 
 */
	String host;
	int    port;
	Socket server;
	
	public client(String host, int port){
		host = this.host;
		port = this.port;
		
	}
	
	client() throws IOException{
		/*Tell client to what server he shall connect*/
		Socket server = new Socket(host, port);
		ObjectInputStream obIn = new ObjectInputStream(server.getInputStream());
		OutputStream obOut = new ObjectOutputStream(server.getOutputStream());
		
		
	
	}
	
	/*Close the Connection to the Server*/
	public void closeCon(){
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
	
		client cl = new client("localhost", 55555);
		
		
	}
		
		
		
	
	
	
}
