package de.hsrm.diogenes.socket;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
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
	private JMenuBar menu;
	
	public PresentationGUI(int port) throws IOException, ClassNotFoundException {
		// set up server for receiving packets
		/*server = new Server(port);
		packet = server.getPacket();*/
		packet = new PresentationPacket(new ImageIcon("photo"), "lolz");
		// set up main frame
		this.setTitle("Presentation Window");
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true); 
		// set up menubar
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
		// load picture and text initially
		picture_label = new JLabel(packet.getPicture());
		text_label = new JLabel(packet.getText());
//		this.add(picture_label, GridBagConstraintsFactory.create(0, 1));
//		this.add(text_label, GridBagConstraintsFactory.create(1, 1));
		this.pack();
		this.setVisible(true);
	}
	
	private void refreshContent() {
		picture_label.setIcon(packet.getPicture());
		text_label.setText(packet.getText());
	}
	
	public static void main(String[] args) {
		try {
			PresentationGUI gui = new PresentationGUI(55555);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
