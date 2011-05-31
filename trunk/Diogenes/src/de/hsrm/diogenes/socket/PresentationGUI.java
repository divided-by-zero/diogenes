package de.hsrm.diogenes.socket;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import de.hsrm.diogenes.gui.GridBagConstraintsFactory;

public class PresentationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Server server;
	private PresentationPacket packet;
	private JLabel picture_label;
	private JLabel text_label;
	private JLabel status_label;
	private JMenuBar menu;
	
	public PresentationGUI(int port) throws IOException, ClassNotFoundException {
		// set up server for receiving packets
		server = new Server(port);
		server.start();
		packet = server.getPacket();

		// set up main frame
		this.setTitle("Presentation Window");
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true); 
		
		// set up menubar
		setUpMenuBar();
		
		// load picture and text initially
		picture_label = new JLabel(packet.getPicture());
		text_label = new JLabel(packet.getText());
		status_label = new JLabel("Todo: status-info");
		
		this.add(picture_label, GridBagConstraintsFactory.create(1, 1));
		this.add(text_label, GridBagConstraintsFactory.create(2, 1));
		this.add(status_label, GridBagConstraintsFactory.create(1, 2));
		
		this.pack();
		this.setVisible(true);
		
		// refresh loop
		while(true) {
			// TODO wait-method
			picture_label.setIcon(packet.getPicture());
			text_label.setText(packet.getText());
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
	
	public static void main(String[] args) {
		try {
			new PresentationGUI(55555);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

