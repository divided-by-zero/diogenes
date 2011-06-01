package de.hsrm.diogenes.remotepresentation;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import de.hsrm.diogenes.gui.GridBagConstraintsFactory;

public class ServerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Server server;
	private Integer port;
	private JLabel picture_label;
	private JLabel text_label;
	private JLabel status_label;
	private JMenuBar menu;
	
	public ServerGUI() {
		final JFrame frame = new JFrame("Insert port-number");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 2));
		JLabel label = new JLabel("Port:");
		final JTextField portfield = new JTextField("55555");
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				port = Integer.valueOf(portfield.getText());
				startGUI();
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
	}
	
	private void startGUI() {
		// set up server for receiving packets
		server = new Server(port);
		server.start();
		// set up main frame
		this.setTitle("Presentation Window");
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true); 
		// set up menubar
		setUpMenuBar();
		// load picture and text initially
		picture_label = new JLabel(server.getPacket().getContent().getImage());
		text_label = new JLabel(server.getPacket().getContent().getDescriptionText());
		status_label = new JLabel("Awaiting connection on port " + port + ", " + 
								server.getPacket().getContent().getAdditionalText());
		this.add(picture_label, GridBagConstraintsFactory.create(1, 1));
		this.add(text_label, GridBagConstraintsFactory.create(2, 1));
		this.add(status_label, GridBagConstraintsFactory.create(1, 2));
		// finish
		this.pack();
		this.setVisible(true);		
		// refresh loop
		while(true) {
			// TODO wait-method
			picture_label.setIcon(server.getPacket().getContent().getImage());
			text_label.setText(server.getPacket().getContent().getDescriptionText());
			status_label.setText(server.getPacket().getContent().getAdditionalText());
		}
	}
	
	private void setUpMenuBar() {
		menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(exit);
		menu.add(file);
		this.setJMenuBar(menu);
	}

	// test
	public static void main(String[] args) {
		new ServerGUI();
	}
}

