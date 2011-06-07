package de.hsrm.diogenes.remotepresentation;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import de.hsrm.diogenes.connection.Location;

/**
 * A Client-Object connects to a Server-Object via a network.
 * It's use is to send packages with information to the Server,
 * who presents it on a display for example.
 * This class will start connecting using the run-method, which
 * also runs this Object in an own Thread.
 * @author Daniel Ernst
 */
public class Client extends Thread {

	/**
	 * Holds the destination address (Server)
	 */
	private String dest_addr;
	
	/**
	 * Holds the port
	 */
	private int port;
	
	/**
	 * A shared Object of the Client and its GUI, so that the Client
	 * as a Thread is able to throw a connection to the ExceptionListener
	 * and the GUI can read the Clients' Exception out of it.
	 * Also used as a lock for synchronizing GUI and Client-Thread
	 */
	private ExceptionListener exceptionlistener;
	
	/**
	 * Holding the socket-connection
	 */
	private Socket server;
	
	/**
	 * Holding the output-stream (for Packet-Objects)
	 */
	private ObjectOutputStream output;
	
	private Timer refreshtimer;
	
	private PacketContainer container;
	
	private Location location;
	
	/**
	 * Instantiates the Client with the address.
	 * The ExceptionListener should be owned by a class
	 * holding a Client-Object and given to the Client.
	 * This way the class holding the client (e.g. the GUI)
	 * can get Exceptions thrown by the Client. Casual
	 * java-Exceptions won't work as this Client runs in an
	 * own Thread. The ExceptionListener will be used as
	 * a lock for synchronization as well. 
	 * @param dest_addr The address to the host (Server)
	 * @param port The port of the host (Server)
	 * @param el An ExceptionListener of the class 
	 * 			holding this Object (e.g. a GUI)
	 */
	public Client(String dest_addr, int port, ExceptionListener el, PacketContainer container, Location location) {
		this.dest_addr = dest_addr;
		this.port = port;
		this.exceptionlistener = el;
		this.container = container;
		this.location = location;
		this.run();
	}

	/**
	 * Runs the Client in an own Thread and starts connecting
	 * to the address of the Server.
	 * Notifies any Exceptions to the ExceptionListener after doing so
	 * and opens the lock with doing that.
	 */
	public void run() {
		try {
			server = new Socket(dest_addr, port);
			output = new ObjectOutputStream(server.getOutputStream());
			output.flush();
			startListening();
		} catch (Throwable t) {
			exceptionlistener.notifyException(t);
			t.printStackTrace();
		}
		synchronized (exceptionlistener) {
			exceptionlistener.notify();
		}
	}

	/**
	 * Disconnects the client from the server and closes the streams.
	 * Notifies any Exceptions to the ExceptionListener after doing so
	 * and opens the lock with doing that.
	 * @throws IOException If closing the socket or the stream didn't work
	 */
	public void disconnect() throws IOException {
		server.close();
		output.close();
		refreshtimer.stop();
		synchronized (exceptionlistener) {
			exceptionlistener.notify();
		}
	}
	
	public void startListening() {
		System.out.println("Client: start listening!");
		refreshtimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Client: TICK:");
				System.out.println("Client: comparing location with contents, robolocation = " + location.toString());
				// check all content-objects if within the current position
				for (Presentable p : container) {
					if (p.surrounds(location)) {
						System.out.println("Client: hit in triggerbox, sending content in packet...");
						try {
							send(p);
							System.out.println("Client: ...package sent!");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		refreshtimer.start();
	}

	public void stopListening() {
		refreshtimer.stop();
	}
	
	/**
	 * Sends a Packet to the Server who stores it locally.
	 * @param p The packet to be sent to the Server
	 * @throws IOException If streams within the connection couldn't be established
	 */
	public void send(Presentable p) throws IOException {
		System.out.println("Client: sending packet");
		output.writeObject(p);
		output.flush();
		System.out.println("Client: packet sent");
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("starting client test");
		Client c = new Client("localhost", 55555, new ExceptionListener(), new PacketContainer(), new Location(1, 2, 3));
		c.send(new Packet(
				new ImageIcon("test1.jpg"), 
				"The answer is 42", 
				new Rectangle(-1000, 1000, 1300, 1300)));
		System.out.println("end client test");
	}
	
}
