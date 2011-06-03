package de.hsrm.diogenes.remotepresentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Client client;
	private ExceptionListener exceptionlistener;
	boolean connected;
	private ContentContainer container;
	private JTextField hostfield;
	private JTextField portfield;
	private JTextField containerfield;
	private JLabel status;
	private JButton button;

	public ClientGUI() {
		// internal stuff
		connected = false;
		exceptionlistener = new ExceptionListener();
		// set up main frame
		this.setTitle("Presentation Window");
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
		// assemble window
		this.add(hostlabel);
		this.add(hostfield);
		this.add(portlabel);
		this.add(portfield);
		this.add(containerlabel);
		this.add(containerfield);
		this.add(status);
		this.add(button);
		// finish
		this.pack();
		this.setVisible(true);
	}

	private void connect() {
		// TODO set up container
		container = new ContentContainer();
		// set up client for receiving packets
		client = new Client(getHost(), getPort(), this.exceptionlistener);
		client.start();
		// TODO: workaround - not finished yet. waiting for client to set exception
		synchronized (exceptionlistener) {
			try {
				exceptionlistener.wait();
			} catch (InterruptedException e) {
				System.out.println("whoops");
			}
		}
		// check for occurred exception in thread
		try {
			// throws exception if client got exception, else does nothing
			System.out.println("GUI: getting exceptions...");
			exceptionlistener.throwException();
			// if no exception do this
			connected = true;
			button.setText("Disconnect");
			status.setText("connected/running");
		} catch (Throwable e1) {
			// popup exception, don't change the button+label
			System.out.println("GUI: popping exceptions...");
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	private void disconnect() {
		// check for occurred exception in thread
		try {
			client.disconnect();
			// throws exception if client got exception, else does nothing
			exceptionlistener.throwException();
			// if no exception do this
			connected = false;
			button.setText("Connect");
			status.setText("disconnected");
		} catch (Throwable e1) {
			// popup exception, don't change the button+label
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
		}		
	}
	
	private String getHost() {
		return hostfield.getText();
	}
	
	private Integer getPort() {
		return Integer.valueOf(portfield.getText());
	}
	
	public static void main(String[] args) {
		new ClientGUI();
	}

}
