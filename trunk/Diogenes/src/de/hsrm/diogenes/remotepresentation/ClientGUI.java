package de.hsrm.diogenes.remotepresentation;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.logic.Movement;

/**
 * TEST GUI! (deprecated as we will implement it in the mainGUI)
 */
public class ClientGUI extends JFrame {

	Connection connection;
	Movement move;
	private static final long serialVersionUID = 1L;
	private Client client;
	private ExceptionListener exceptionlistener;
	boolean connected;
	private JTextField hostfield;
	private JTextField portfield;
	private JTextField containerfield;
	private JLabel status;
	private JButton button;

	public ClientGUI() throws WRPException {
		this.connected = false;
		this.exceptionlistener = new ExceptionListener();
		// run gui
		startMainWindow();
	}

	private void startMainWindow() {
		this.setTitle("Presentation Window (Client)");
		this.setLayout(new GridLayout(4, 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JLabel hostlabel = new JLabel("Host:");
		JLabel portlabel = new JLabel("Port:");
		JLabel containerlabel = new JLabel("Container:");
		hostfield = new JTextField("localhost");
		portfield = new JTextField(String.valueOf(55555));
		containerfield = new JTextField("/path/to/xml/");
		status = new JLabel("disconnected");
		button = new JButton("Connect");
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

	private void connect() {
		// example connection+movement
		try {
			this.connection = new Connection("localhost", 33333);
			this.move = new Movement(connection);
		} catch (WRPException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// start clientthread
		client = new Client(getHost(), getPort(), this.exceptionlistener,
				new PacketContainer(), connection.getLocation());
		client.start();
		// waiting for client-thread to finish initialization (exceptions
		// possible)
		synchronized (exceptionlistener) {
			try {
				exceptionlistener.wait();
			} catch (InterruptedException e) {
				// ignore interruptions as the client does not do it
				// but warn the user
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage()
						+ "\nClients' synchronization interrupted:\n"
						+ "This may cause bad Exception-Handling", "Warning",
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
			// popup exception, don't change the button and labels
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE, null);
		}
		// connection + mutual exclusion + exceptionlistening done
		doSomething();
	}

	private void doSomething() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// move to desk and back to initial pose
					move.wander(new Point(3400, -1500), new Point(-268, 1585));
				} catch (WRPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	private void disconnect() {
		// check if exception occurred in the client
		try {
			// disconnecting client may throw an exception
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
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE, null);
		}
	}

	private String getHost() {
		return hostfield.getText();
	}

	private Integer getPort() {
		return Integer.valueOf(portfield.getText());
	}

	public static void main(String[] args) throws WRPException {
		new ClientGUI();
	}

}
