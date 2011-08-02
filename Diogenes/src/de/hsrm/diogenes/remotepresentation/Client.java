package de.hsrm.diogenes.remotepresentation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.hsrm.diogenes.connection.Location;

/**
 * A Client-Object connects to a Server-Object via a network.
 * The usage is to send packages as bytearrays to the Server,
 * who reassambles it and presents it on a display (e.g.).
 * This class will start connecting using the run-method, which
 * will run this Object in an own Thread.
 * @author Philip Koch, Daniel Ernst
 */
public class Client extends Thread {

	/** Holds the destination address (Server). */
	private String dest_addr;
	
	/** Holds the port. */
	private int port;
 
	/**
	 * A shared Object of the Client and its GUI, so that the Client as a Thread 
	 * is able to throw an Exception to the ExceptionListener and the GUI can read 
	 * the Clients' Exception out of it. 
	 * Also used as a lock for synchronizing GUI and Client-Thread.
	 */
	private ExceptionListener exceptionlistener;
	
	/** Holding the socket-connection. */
	private Socket server;
	
	/** Holding the output-stream. */
	private OutputStream output;

	/** An ObjectOutputStream using the OutputStream mainly for sending Integers */
	private ObjectOutputStream objoutput;
	
	/** The "current" Location of the robot */
	private Location location;
	
	/** 
	 * A Thread repetitively reading the current location of the robot
	 * and checking if a Packet of the Container is triggered 
	 */
	private Thread locationlistener;
	
	/** A Container holding the Packets which will probably be sent to the Server */
	private ClientPacketContainer container;
	
	/**
	 * Holds the last Packet which has been sent
	 * to the Server. Used to prevent the Client to send
	 * the same Packet over and over as long as the robot
	 * triggers this Packets' triggerBox
	 */
	private ClientPacket lastPacket;

	/** Time to wait before sending data again in ms, to improve performance */
	private final int SENDDELAY = 500;
	
	/**
	 * Instantiates the Client with the address.
	 * The ExceptionListener should be owned by a class
	 * holding a Client-Object and given to the Client.
	 * This way the class holding the client (e.g. the GUI)
	 * can get Exceptions thrown by the Client. Casual
	 * java-Exceptions won't work as this Client runs in an
	 * own Thread. The ExceptionListener will be used as
	 * a lock for synchronization as well.
	 * Not using a shared ExcpetionListenerObject will
	 * cause the Exceptions of this Thread to not be
	 * notified elsewhere outside this Object and
	 * furthermore there will be no synchronization between
	 * the Threads. Despite this the Client may still work anyway.
	 * @param dest_addr The address to the host (Server)
	 * @param port The port of the host (Server)
	 * @param el The ExceptionListener-Object of the Object
	 * 			holding this Client (e.g. a GUI) for synchronization
	 * @param container The Container holding the Packets 
	 * 			which are supposed to be eventually sent 
	 * 			to the Server
	 * @param location A reference to the Object holding 
	 * 			the current Location of the robot
	 */
	public Client(String dest_addr, int port, ExceptionListener el, ClientPacketContainer container, Location location) {
		this.dest_addr = dest_addr;
		this.port = port;
		this.exceptionlistener = el;
		this.container = container;
		this.location = location;
		initLocationListener();
	}
	
	/**
	 * Runs the Client in an own Thread and starts connecting
	 * to the address of the Server.
	 * Notifies any Exceptions to the ExceptionListener after doing so
	 * and releases the lock with doing that.
	 */
	public void run() {
		try {
			server = new Socket(dest_addr, port);
			output = server.getOutputStream();
			objoutput = new ObjectOutputStream(output);
			output.flush();
			startListening();
		} catch (Throwable t) {
			exceptionlistener.notifyException(t);
		}
		// release lock
		synchronized (exceptionlistener) {
			exceptionlistener.notify();
		}
	}

	/**
	 * Start reading repetitively the current location of the robot
	 * and checks if a Packet of the Container is triggered
	 */
	public void startListening() {
		locationlistener.start();
	}
	
	/**
	 * Initializes the location listener.
	 */
	private void initLocationListener() {
		this.locationlistener = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					// check all content-objects if within the current position
					for (ClientPacket cp : container) {
						// sends if triggered AND 
						// if this Packet hasn't just been sent in the 
						// last round (avoids unnecessary traffic) 
						if (cp.surrounds(location) && !cp.equals(lastPacket)) {
							try {
								send(cp);
								lastPacket = cp;
							} catch (IOException e) {
								// popup exception
								JOptionPane.showMessageDialog(
														new JFrame(), 
														"Couldn't send packet:\n"
														+ e.getMessage(),  
														"Error", 
														JOptionPane.ERROR_MESSAGE, null);
							}
						}
					}
					try {
						sleep(SENDDELAY);
					} catch (InterruptedException e) {
						// interrupting the sleep will cause a performance-issue as
						// the for-each above will be reached earlier - depending on
						// the size of the SENDDELAY as well as the size of the container.
						// So this Exception can be catched without doing anything else. 
					}
				}

			}
		});
	}

	/**
	 * Sends data to the Server who is supposed to store it locally as a ServerPacket.
	 * This method avoids sending complex Objects as a whole as it may cause serialization
	 * problems between different JVM-versions. This is why it just sends whether simple 
	 * Objects (only Integers) or Bytearrays for more complex Objects (like Images but also
	 * Strings).
	 * In detail it will send the data in this order:
	 * 1st: The image of the packet as byteArray (first the length (int), then the array itself)
	 * 2nd: The text of the packet as byteArray (first the length (int), then the array itself)
	 * 3rd: The triggerBox of the packet broke down in four Integers (x,y,width,height)
	 * @param cp The packet holding the data to be sent to the Server
	 * @throws IOException If streams within the connection couldn't be established
	 */
	public void send(ClientPacket cp) throws IOException {
		// sending image (int and bytearray)
		int imagelength = cp.getImage().length;
		objoutput.writeObject(imagelength);
		objoutput.flush();
		output.write(cp.getImage(), 0, imagelength);
		output.flush();
		// sending text (int and bytearray)
		objoutput.writeObject(cp.getText().length);
		objoutput.flush();
		output.write(cp.getText());
		output.flush();
		// sending rectangle (four times int)
		objoutput.writeObject((int)cp.getTriggerBox().getX());
		objoutput.flush();
		objoutput.writeObject((int)cp.getTriggerBox().getY());
		objoutput.flush();
		objoutput.writeObject((int)cp.getTriggerBox().getWidth());
		objoutput.flush();
		objoutput.writeObject((int)cp.getTriggerBox().getHeight());
		objoutput.flush();
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
		// release lock
		synchronized (exceptionlistener) {
			exceptionlistener.notify();
		}
	}
	
}
