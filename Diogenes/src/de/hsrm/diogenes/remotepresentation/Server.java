package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 * A Server-Object will be connected to a Client-Object via a network.
 * It's use is to display information of Packets sent by the Client-Object.
 * This class will start connecting using the run-method, which
 * also runs this Object in an own Thread.
 */
public class Server extends Thread {
	
	/**
	 * A packet containing an image and a text to be displayed by a GUI 
	 * (use getPacket). Will be updated by a remote client.
	 */
	private ServerPacket serverpacket;
	
	/** The port to be used for the connection with Clients. */
	private int port;
	
	/**
	 * A shared Object of ServerGUI and Server, so that the Server 
	 * as a Thread is able to throw an Exception to the ExceptionListener 
	 * and the ServerGUI can read the Servers' Exception out of it. 
	 * Also used as a lock for synchronizing ServerGUI and Server-Thread
	 */
	private ExceptionListener exceptionlistener;
	
	/** Delay. */
	private final int RECEIVEDELAY = 500;
	
	/**
	 * Instantiates the Server at the port.
	 * The ExceptionListener should be owned by a class
	 * holding a Server-Object and given to the Server.
	 * This way the class holding the Server (e.g. the GUI)
	 * can get Exceptions thrown by the Server. Casual
	 * java-Exceptions won't work as this Server runs in an
	 * own Thread. The ExceptionListener will be used as
	 * a lock for synchronization as well. 
	 * @param port The port to be opened
	 * @param el An ExceptionListener of the class 
	 * 			holding this Object (e.g. a GUI)
	 */
	public Server(int port, ExceptionListener el) {
		this.port = port;
		this.exceptionlistener = el;
		// first Packet will be a "welcome-packet"
		ImageIcon image = new ImageIcon(Server.class.getClassLoader().getResource("example.jpg"));
//		ImageIcon image = new ImageIcon(getClass().getResource("../img/scrat.jpg"));
		serverpacket = new ServerPacket(
				image,
				"<html><B>No information received so far...</B></html>", 
				new Rectangle(0, 0, 0, 0));
	}

	/**
	 * Runs the Server in an own Thread and starts accepting
	 * Clients (infinite).
	 * Notifies any Exceptions to the ExceptionListener after doing so
	 * and opens the lock with doing that.
	 */
	public void run() {
		ServerSocket server_sock;
		try {
			server_sock = new ServerSocket(port);
			// connection with port successful, notify
			synchronized (exceptionlistener) {
				exceptionlistener.notify();
			}
			// infinite loop for accepting clients, receiving data and disconnecting them
			while (true) {
				// accept client
				Socket current_client = server_sock.accept();
				InputStream client_input = current_client.getInputStream();
				ObjectInputStream client_objinput = new ObjectInputStream(client_input);
				DataInputStream client_datainput = new DataInputStream(client_input);
				byte[] imageByteArray;
				byte[] textByteArray;
				Rectangle triggerBox;
				while(true) {
					// read image
					int imagelength = (Integer) client_objinput.readObject();
					imageByteArray = new byte[imagelength];
					client_datainput.readFully(imageByteArray);
					// read text
					int textlength = (Integer) client_objinput.readObject();
					textByteArray = new byte[textlength];
					client_input.read(textByteArray);
					// read triggerBox
					int r_x = (Integer) client_objinput.readObject();
					int r_y = (Integer) client_objinput.readObject();
					int r_w = (Integer) client_objinput.readObject();
					int r_h = (Integer) client_objinput.readObject();
					triggerBox = new Rectangle(r_x, r_y, r_w, r_h);
					// assemble to presentable
					ServerPacket tmp_presentable = new ServerPacket(
							new ImageIcon(imageByteArray),
							new String(textByteArray), 
							triggerBox);
					if (serverpacket != null) {
						serverpacket = tmp_presentable;
					} else {
						break;
					}
					sleep(RECEIVEDELAY);
				}
				// close connection
				current_client.close();
				client_input.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
			exceptionlistener.notifyException(t);
		}
		// due to the whileloop this part of code is only reachable when 
		// an exception occurred, then notify
		synchronized (exceptionlistener) {
			exceptionlistener.notify();
		}
	}

	/**
	 * Returns a packet containing an image and a text to be displayed by a GUI. Will be updated by a remote Client.
	 * @return  The packet containing an image and a text for visualization
	 */
	public ServerPacket getPacket() {
		return serverpacket;
	}

}
