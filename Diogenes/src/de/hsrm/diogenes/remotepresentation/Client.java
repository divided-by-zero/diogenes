package de.hsrm.diogenes.remotepresentation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
	 * @uml.property  name="dest_addr"
	 */
	private String dest_addr;
	
	/**
	 * Holds the port
	 * @uml.property  name="port"
	 */
	private int port;
	
	/**
	 * A shared Object of the Client and its GUI, so that the Client as a Thread is able to throw a connection to the ExceptionListener and the GUI can read the Clients' Exception out of it. Also used as a lock for synchronizing GUI and Client-Thread
	 * @uml.property  name="exceptionlistener"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ExceptionListener exceptionlistener;
	
	/**
	 * Holding the socket-connection
	 * @uml.property  name="server"
	 */
	private Socket server;
	
	/**
	 * Holding the output-stream (for Packet-Objects)
	 * @uml.property  name="output"
	 */
	private ObjectOutputStream output;
	
	/**
	 * @uml.property  name="locationlistener"
	 */
	private Thread locationlistener;
	
	/**
	 * @uml.property  name="container"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private PacketContainer container;
	
	/**
	 * @uml.property  name="location"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Location location;
	
	/**
	 * Holds the last presentable which has been sent
	 * to the Server. Used to prevent the Client to send
	 * the same presentable over and over as long as the sending 
	 * itself is triggered 
	 */
	private Presentable lastPresentable;
	
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
		initLocationListener();
	}

	private void initLocationListener() {
		this.locationlistener = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					// check all content-objects if within the current position
					for (Presentable p : container) {
						// sends if triggered AND 
						// if this presentable hasn't just been sent in the 
						// last round (less traffic) 
						System.out.println("Robbielocation: " + location.toString());
						if (p.surrounds(location) && !p.equals(lastPresentable)) {
							System.out.println("Client: Triggerbox-hit");
							try {
								System.out.println("Client: Try to send a packet...");
								send(p);
								System.out.println("Client: Packet sent: " + p.getAdditionalText());
								lastPresentable = p;
							} catch (IOException e) {
								System.out.println("Client: Unkown exception during sending package");
							}
						}
					}
					try {
						sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
				}

			}
		});
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
		synchronized (exceptionlistener) {
			exceptionlistener.notify();
		}
	}
	
	public void startListening() {
		locationlistener.start();
	}

	public void stopListening() {
		//TODO locationlistener.stop() ?
	}
	
	/**
	 * Sends a Packet to the Server who stores it locally.
	 * @param p The packet to be sent to the Server
	 * @throws IOException If streams within the connection couldn't be established
	 */
	public void send(Presentable p) throws IOException {
		System.out.println("Client: Write Obj to output...");
		output.writeObject(p);
		System.out.println("Client: Flushing output...");
		output.flush();
	}
	
}
