package de.hsrm.diogenes.remotepresentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * This GUI is a comfortable way of using a Server-Object
 * for gaining Packets by a Client for a remote presentation.
 * @see Server
 */
public class ServerGUI extends JFrame {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Holds a Server-Object who can be connected by a Client-Object. @uml.property  name="server" @uml.associationEnd */
	private Server server;
	
	/** The port to be used for incoming Packets. @uml.property  name="port" */
	private Integer port;
	
	/**
	 * A shared Object of ServerGUI and Server, so that the Server as a Thread is able to throw an Exception to the ExceptionListener and the ServerGUI can read the Servers' Exception out of it. Also used as a lock for synchronizing ServerGUI and Server-Thread
	 * @uml.property  name="exceptionlistener"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ExceptionListener exceptionlistener;
	
	/** A label for the image. @uml.property  name="image_label" @uml.associationEnd */
	private JLabel image_label;
	
	/** A label for the description-text. @uml.property  name="text_label" @uml.associationEnd */	
	private JLabel text_label;
	
	/** A label for the status-text. @uml.property  name="status_label" @uml.associationEnd */
	private JLabel status_label;
	
	/**
	 * Instantiates the GUI and starts a DialogWindow
	 * at startup, which asks the user for the port
	 * to be used. Then starts the actual MainWindow
	 */
	public ServerGUI() {
		exceptionlistener = new ExceptionListener();
		startDialogWindow();
		// if ok in the DialogWindow is clicked, 
		// the main gui will show up
	}

	/**
	 * Starts a DialogWindow which asks the user for the port
	 * to be used.
	 * On hitting the OK-button the MainWindow will show up.
	 */
	private void startDialogWindow() {
		// starts a dialog to get port from user
		final JFrame frame = new JFrame("Insert port-number");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 2));
		// center on screen
		frame.setLocationRelativeTo(null);
		JLabel label = new JLabel("Port:");
		final JTextField portfield = new JTextField("55555");
		// buttons and actionlisteners - ok button
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				port = Integer.valueOf(portfield.getText());
				// here the main gui actually starts (on OK-click)
				startMainWindow();
				frame.dispose();				
			}
		});
		// buttons and actionlisteners - cancel button
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.add(label);
		frame.add(portfield);
		frame.add(ok);
		frame.add(cancel);
		frame.pack();
		frame.setVisible(true);
		// if ok in the DialogWindow is clicked, 
		// the main gui will show up
	}
	
	/**
	 * Starts the MainWindow with the information of the Packets
	 * (e.g. images, text...) displayed. The display will updated by
	 * a connected Client-Object sending a new Packet.
	 * This method also starts the Server-Object in an own Thread
	 * an waits for the Server-Object to finish initialization and
	 * notifying that it has done so. If an Exception occurred
	 * this will be shown in an errormessage, aborts showing the
	 * MainWindow and starts the DialogWindow again.
	 * As the MainWindow runs, it's displayed information will be
	 * updated every 500ms.
	 */
	private void startMainWindow() {
		// set up the Server and start it in an own Thread,
		// mind and handle Exceptions
		initServer();
		// set up main frame
		this.setTitle("Presentation Window (Server)");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// center on screen
		this.setLocationRelativeTo(null);
		// create labels
		Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		image_label = new JLabel();
		image_label.setHorizontalAlignment(JLabel.CENTER);
		image_label.setBorder(etchedBorder);
		text_label = new JLabel();
		text_label.setBorder(etchedBorder);
		status_label = new JLabel();
		status_label.setBorder(etchedBorder);
		// fill labels with the data the server initially holds
		refreshGUI();
		// add image + textlabel to a panel
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(image_label, BorderLayout.CENTER);
		p.add(text_label, BorderLayout.EAST);
		// add the panel (image+text) and the statuslabel to the frame
		this.add(p, BorderLayout.CENTER);
		this.add(status_label, BorderLayout.PAGE_END);
		// finish
		this.pack();
		this.setVisible(true);
		// refresh loop (every half second)
		Timer refreshtimer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshGUI();
			}
		});
		refreshtimer.start();
	}

	/**
	 * Sets up the Server-Object for receiving data.
	 * This server will be initialized and started in an own
	 * Thread. The exceptionlistener will be used as a lock
	 * between this GUI and the Server-Object.
	 * In detail this GUI will wait for the Server-Thread to
	 * release the lock again and handle Exceptions (using the
	 * ExceptionListener)
	 */
	private void initServer() {
		// set up server for receiving packets (own thread)
		server = new Server(port, exceptionlistener);
		server.start();
		// waiting for server to finish initialization (exceptions possible)
		synchronized (exceptionlistener) {
			try {
				exceptionlistener.wait();
			} catch (InterruptedException e) {
				// ignore interruptions as the server does not do it
				// but warn the user
				JOptionPane.showMessageDialog(
						new JFrame(), 
						"Servers' synchronization interrupted\n"
						+ "This may cause bad Exception-Handling:\n"
						+ e.getMessage(), 
						"Warning", 
						JOptionPane.WARNING_MESSAGE, null);
			}
		}
		try {
			exceptionlistener.throwException();
		} catch (Throwable e1) {
			// popup exception
			JOptionPane.showMessageDialog(
									new JFrame(), 
									e1.getMessage(), 
									"Error", 
									JOptionPane.ERROR_MESSAGE, null);
			// start all over again with the DialogWindow and
			// quit building the MainWindow
			startDialogWindow();
			return;
		}
	}
	
	private void refreshGUI() {
		image_label.setIcon(server.getPacket().getImage());
		text_label.setText(server.getPacket().getText());
		Rectangle r = server.getPacket().getRectangle();
		String label = "Invoked between " + 
					"(" + (int)r.getMinX() + "," + (int)r.getMinY() + ") and " +
					"(" + (int)r.getMaxX() + "," + (int)r.getMaxY() + ")";
		status_label.setText(label);		
	}
	
	/**
	 * The main method for starting the GUI
	 *
	 * @param args The arguments (not used)
	 */
	public static void main(String[] args) {
		new ServerGUI();
	}
}

