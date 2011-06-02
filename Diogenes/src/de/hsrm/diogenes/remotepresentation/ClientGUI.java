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

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Client client;
	private ThreadExceptionListener tel;
	boolean connected;
	private ContentContainer container;
	private JTextField hostfield;
	private JTextField portfield;
	private JTextField containerfield;
	private JLabel status;
	private JButton button;

	public ClientGUI() {
		// set up main frame
		this.setTitle("Presentation Window");
		this.setLayout(new GridLayout(4, 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// center on screen
		this.setLocationRelativeTo(null);
		// not connected at startup
		connected = false;
		tel = new ThreadExceptionListener();
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
					connected = false;
					button.setText("Connect");
					status.setText("disconnected");
				} else {
					connect();
					try {
						tel.getException();
					} catch (Throwable e1) {
						JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
					}
				}
			}
		});
		// assemble
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
		client = new Client(getHost(), getPort(), this.tel);
		client.start();
	}
	
	private void disconnect() {
		
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
