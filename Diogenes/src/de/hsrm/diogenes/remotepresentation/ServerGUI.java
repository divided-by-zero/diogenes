package de.hsrm.diogenes.remotepresentation;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import de.hsrm.diogenes.gui.GridBagConstraintsFactory;

/**
 * This GUI is a comfortable way of using a Server-Object
 * for gaining Packets by a Client for a remote presentation.
 * @see Server
 */
public class ServerGUI extends JFrame {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Holds a Server-Object who can be connected by a Client-Object
	 * @uml.property  name="server"
	 * @uml.associationEnd  
	 */
	private Server server;
	
	/**
	 * The port to be used for incoming Packets
	 * @uml.property  name="port"
	 */
	private Integer port;
	
	/**
	 * A shared Object of ServerGUI and Server, so that the Server as a Thread is able to throw an Exception to the ExceptionListener and the ServerGUI can read the Servers' Exception out of it. Also used as a lock for synchronizing ServerGUI and Server-Thread
	 * @uml.property  name="exceptionlistener"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ExceptionListener exceptionlistener;
	
	/**
	 * A label for the image
	 * @uml.property  name="image_label"
	 * @uml.associationEnd  
	 */
	private JLabel image_label;
	
	/**
	 * A label for the description-text
	 * @uml.property  name="text_label"
	 * @uml.associationEnd  
	 */	
	private JLabel text_label;
	
	/**
	 * A label for the status-text
	 * @uml.property  name="status_label"
	 * @uml.associationEnd  
	 */
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
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				port = Integer.valueOf(portfield.getText());
				// here the main gui actually starts (on OK-click)
				startMainWindow();
				frame.dispose();
			}
		});
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
		// set up main frame
		this.setTitle("Presentation Window (Server)");
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// load picture and text initially
		image_label = new JLabel(server.getPacket().getImage());
//		image_label = new JLabel("here be pics");
		text_label = new JLabel(server.getPacket().getText());
		status_label = new JLabel("Awaiting connection on port " + port);
		this.add(image_label, GridBagConstraintsFactory.create(1, 1));
		this.add(text_label, GridBagConstraintsFactory.create(2, 1));
		this.add(status_label, GridBagConstraintsFactory.create(1, 2));
		// finish
		this.pack();
		this.setVisible(true);
		// refresh loop (every half second)
		Timer refreshtimer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				image_label.setIcon(server.getPacket().getImage());
				text_label.setText(server.getPacket().getText());
				status_label.setText(server.getPacket().getRectangle().toString());
			}
		});
		refreshtimer.start();
	}

	public static void main(String[] args) {
		new ServerGUI();
	}
}

