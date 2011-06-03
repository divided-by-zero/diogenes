package de.hsrm.diogenes.remotepresentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This GUI is a comfortable way of using a Client-Object
 * for a remote presentation on a Server-Object.
 * @see Client
 * @author Daniel Ernst
 */
public class ClientGUI extends JFrame {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Holds a Client-Object who connects to a Server-Object
	 */
	private Client client;
	
	/**
	 * A shared Object of ClientGUI and Client, so that the Client
	 * as a Thread is able to throw a connection to the ExceptionListener
	 * and the ClientGUI can read the Clients' Exception out of it.
	 * Also used as a lock for synchronizing ClientGUI and Client-Thread
	 */
	private ExceptionListener exceptionlistener;
	
	/**
	 * Represents if the connection between this Client and a Server
	 * is/has been established
	 */
	boolean connected;
	
	/**
	 * A container holding Contents to be transferred via network in a
	 * Packet to the Server and visualized there, if a certain value in
	 * the Content is reached.
	 */
	private ContentContainer container;
	
	/**
	 * The input-field for the hostaddress (Server)
	 */
	private JTextField hostfield;
	
	/**
	 * The input-field for the hostaddress (Server)
	 */
	private JTextField portfield;
	
	/**
	 * The input-field for the container-file
	 */
	private JTextField containerfield;
	
	/**
	 * Shows the current state of the program (connected and running
	 * or disconnected)
	 */
	private JLabel status;
	
	/**
	 * The only button, initally a "Connect" button, but on an established
	 * connection it turns to the "Disconnect" button
	 */
	private JButton button;

	/**
	 * Instantiates the GUI values and starts the main window
	 */
	public ClientGUI() {
		connected = false;
		exceptionlistener = new ExceptionListener();
		startMainWindow();
	}

	/**
	 * Builds the GUI's main window with initial values in the fields
	 * and sets the button's ActionListener which starts the
	 * connect() or disconnect()-methods
	 */
	private void startMainWindow() {
		// set up main frame
		this.setTitle("Presentation Window (Client)");
		this.setLayout(new GridLayout(4, 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// center on screen
		this.setLocationRelativeTo(null);
		// initial GUI items
		JLabel hostlabel = new JLabel("Host:");
		JLabel portlabel = new JLabel("Port:");
		JLabel containerlabel = new JLabel("Container:");
		hostfield = new JTextField("localhost");
		portfield = new JTextField(String.valueOf(55555));
		containerfield = new JTextField("/path/to/xml/");
		status = new JLabel("disconnected");
		button = new JButton("Connect");
		// buttonaction	
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (connected) {
					disconnect();
				} else {
					connect();
				}
			}
		});
		// assemble window and show
		this.add(hostlabel);
		this.add(hostfield);
		this.add(portlabel);
		this.add(portfield);
		this.add(containerlabel);
		this.add(containerfield);
		this.add(status);
		this.add(button);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Instantiates the ContentContainer.
	 * Instantiates the Client with the values of the inputfields and
	 * shares the ExceptionListener with him. Starts the Client in
	 * an own Thread, but will wait for the Client to finish the
	 * initialization using the ExceptionListener as a lock.
	 * Will show errormessages to the user if the Client-Thread
	 * notified an Exception to the ExceptionListener.
	 * Changes the button whether to a "Connect"- or "Disconnect"-
	 * button and the statuslabel as well.
	 */
	private void connect() {
		// TODO set up container
		container = new ContentContainer();
		// set up client for receiving packets
		client = new Client(getHost(), getPort(), this.exceptionlistener);
		// start in own thread
		client.start();
		// waiting for client-thread to finish initialization (exceptions possible)
		synchronized (exceptionlistener) {
			try {
				exceptionlistener.wait();
			} catch (InterruptedException e) {
				// ignore interruptions as the client does not do it
				// but warn the user
				JOptionPane.showMessageDialog(
						new JFrame(), 
						e.getMessage() + "\nClients' synchronization interrupted:\n" +
								"This may cause bad Exception-Handling", 
						"Warning", 
						JOptionPane.WARNING_MESSAGE, null);
			}
		}
		// waiting done, check if exception occurred in the client
		try {
			// throws exception if client got exception, else does nothing
			exceptionlistener.throwException();
			// if no exception, it's connected, 
			// so set the buttons and labels correctly
			connected = true;
			button.setText("Disconnect");
			status.setText("connected/running");
		} catch (Throwable e1) {
			// popup exception, don't change the button and label
			System.out.println("GUI: popping exceptions...");
			JOptionPane.showMessageDialog(
									new JFrame(), 
									e1.getMessage(), 
									"Error", 
									JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	/**
	 * Disconnects the Client from the Server, shows error-
	 * messages if Exception in the Client occurred (does
	 * not use the ExceptionListener for notification).
	 * Sets the button appropriate to whether "Connected"
	 * or "Disconnected".
	 */
	private void disconnect() {
		// check if exception occurred in the client
		try {
			// disconnect may throw an exception
			client.disconnect();
			// no synchronized procedure to check for exceptions via
			// exceptionlistener is needed here as the method disconnect
			// is able to throw directly.
			// if no exception, it's disconnected, 
			// so set the buttons and labels correctly
			connected = false;
			button.setText("Connect");
			status.setText("disconnected");
		} catch (Throwable e1) {
			// popup exception, don't change the button+label
			JOptionPane.showMessageDialog(
									new JFrame(), 
									e1.getMessage(), 
									"Error", 
									JOptionPane.ERROR_MESSAGE, 
									null);
		}
	}
	
	/**
	 * Returns the current hostaddress of the hostfield
	 * @return The current input of the hostfield
	 */
	private String getHost() {
		return hostfield.getText();
	}

	/**
	 * Returns the current port of the portfield
	 * (converts the String to Integer)
	 * @return The current input of the portfield
	 */
	private Integer getPort() {
		return Integer.valueOf(portfield.getText());
	}
	
	public static void main(String[] args) {
		new ClientGUI();
	}

}
