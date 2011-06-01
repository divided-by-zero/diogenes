package de.hsrm.diogenes.remotepresentation;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Client client;
	private ContentContainer container;
	private JMenuBar menu;

	public ClientGUI(int port) {
		// set up client for receiving packets
		client = new Client();

		// set up container
		container = new ContentContainer();
		
		// set up main frame
		this.setTitle("Presentation Window");
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set up menubar
		setUpMenuBar();

		this.pack();
		this.setVisible(true);
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

	public static void main(String[] args) {
		new ClientGUI(55555);
	}

}
