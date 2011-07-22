package de.hsrm.diogenes.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;
import de.hsrm.diogenes.faceClientExample.ClientExample;
import de.hsrm.diogenes.map.Map;
import de.hsrm.diogenes.remotepresentation.Client;
import de.hsrm.diogenes.remotepresentation.ExceptionListener;
import de.hsrm.diogenes.remotepresentation.PacketContainer;

/**
 * @author Dirk Stanke
 * 
 * The Class GuiModel, everything related to the GUI
 */
public class GuiModel extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The webcam panel. @uml.property name="webcamPanel" @uml.associationEnd
	 * multiplicity="(1 1)"
	 */
	private final JPanel webcamPanel;

	/**
	 * The statusbar panel. @uml.property name="statusbarPanel"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private JPanel statusbarPanel;

	/** The l1. @uml.property name="l1" @uml.associationEnd multiplicity="(1 1)" */
	private JLabel l1;

	/** /. @uml.property name="c" @uml.associationEnd multiplicity="(1 1)" */
	private Connection c;

	/** The cam enabled. @uml.property name="camEnabled" */
	private boolean camEnabled;

	/**
	 * The map. @uml.property name="map" @uml.associationEnd
	 * multiplicity="(1 1)"
	 */
	private Map map;

	/** The presentation client. Used for the presentation mode */
	private Client presentationClient;

	/** The presentation mode exception listener. */
	private ExceptionListener presentationExceptionListener;

	/** Tells us wether the presentation mode is running or not */
	private boolean isPresentationRunning;

	/** The presentation menu item. */
	private JMenuItem presentationMenuItem;

	/** The presentation mode dialog frame. */
	private JFrame presentationDialogFrame;

	/** The presentation mode dialog portfield. */
	private JTextField presentationDialogPortfield;

	/**
	 * The scroll object for the mapPanel. @uml.property name="scroller"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private JScrollPane scroller;

	/**
	 * Instantiates a new gui model.
	 * 
	 * @param c
	 *            the connection
	 * @param map
	 *            the map given by the robot
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public GuiModel(Connection c, Map map) throws IOException,
			InterruptedException {
		this.c = c;
		this.setMap(map);
		this.setTitle("Diogenes robot control");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(ImageIO.read(getClass()
				.getResource("../img/robi.jpg")));
		this.setResizable(false);
		this.setLayout(new GridBagLayout());
		this.setJMenuBar(makeMenuBar());
		this.setCamEnabled(true);
		this.presentationExceptionListener = new ExceptionListener();
		this.isPresentationRunning = false;
		this.l1 = new JLabel("Status");

		this.webcamPanel = new JPanel();
		this.webcamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.statusbarPanel = new JPanel();

		createPanels();

		this.scroller = new JScrollPane(new MapCanvas(new Map(this.c), this.c,
				70, 50, 400), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.scroller.setPreferredSize(new Dimension(350, 410));

		// Add the Panels to our JFrame
		this.add(scroller, GridBagConstraintsFactory.create(0, 0, 1, 2));
		this.add(webcamPanel, GridBagConstraintsFactory.create(1, 0, 1, 1));
		this.add(new ButtonPanel(this.c), GridBagConstraintsFactory.create(1,
				1, 1, 1));
		this.add(statusbarPanel, GridBagConstraintsFactory.create(0, 3, 2, 1));

		this.pack();
		this.setVisible(true);

	}

	/**
	 * Adds the needed components to the specific JPanel.
	 */
	public void createPanels() {

		this.statusbarPanel.add(l1);

		if (this.c.isCamData()) {
			webcamPanel.add(c.getCameraData().getCam());
			Timer t = new Timer(210, new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					webcamPanel.removeAll();
					webcamPanel.add(c.getCameraData().getCam());
					webcamPanel.validate();
				}
			});
			t.start();
		} else {
			this.webcamPanel.add(new JLabel(new ImageIcon(getClass()
					.getResource("../img/scrat.png"))));
		}
	}

	/**
	 * Constructs our JMenuBar.
	 * 
	 * @return the constructed JMenuBar
	 */
	public JMenuBar makeMenuBar() {

		JMenuBar menuBar;
		JMenu menu_datei, menu_funktionen, menu_kamera, menu_ueber;
		JMenuItem menuItem;

		menuBar = new JMenuBar();
		menu_datei = new JMenu("File");
		menu_funktionen = new JMenu("Functions");
		menu_kamera = new JMenu("Camera");
		menu_ueber = new JMenu("About");

		menuItem = new JMenuItem("Connect");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!c.isConnected()) {
						c.connect(); // "10.18.72.254", 33333
						l1.setText("connected");
						c.setConnected(true);
					}
				} catch (WRPException e2) {
					e2.printStackTrace();
				}
			}
		});
		menu_datei.add(menuItem);

		menuItem = new JMenuItem("Disconnect");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.getDiogenes().disconnect();
					l1.setText("disconnected");
					c.setConnected(false);
				} catch (WRPException e2) {
					e2.printStackTrace();
				}
			}
		});
		menu_datei.add(menuItem);

		menuItem = new JMenuItem("Simulator?");
		menu_datei.add(menuItem);

		menuItem = new JMenuItem("Wander the given points");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setStartWander(true);
			}
		});
		menu_funktionen.add(menuItem);

		menuItem = new JMenuItem("Visualize map");
		menu_funktionen.add(menuItem);

		presentationMenuItem = new JMenuItem("Start presentation mode");
		presentationMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if presentation is NOT yet running:
				if (!isPresentationRunning) {
					// ask the user via gui for a port to use
					presentationDialogFrame = new JFrame("Insert port-number");
					presentationDialogFrame.setLayout(new GridLayout(2, 2));
					presentationDialogFrame.setLocationRelativeTo(null);
					JLabel label = new JLabel("Port:");
					presentationDialogPortfield = new JTextField();
					presentationDialogPortfield.setText("55555");
					JButton b_cancel = new JButton("Cancel");
					b_cancel.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							presentationDialogFrame.dispose();
						}
					});
					JButton b_ok = new JButton("OK");
					b_ok.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int port = Integer
									.valueOf(presentationDialogPortfield
											.getText());
							presentationDialogFrame.dispose();
							// actually init the client here
							startPresentationClient(port);
						}
					});
					presentationDialogFrame.add(label);
					presentationDialogFrame.add(presentationDialogPortfield);
					presentationDialogFrame.add(b_ok);
					presentationDialogFrame.add(b_cancel);
					presentationDialogFrame.pack();
					presentationDialogFrame.setVisible(true);
				} else {
					// if presentation is already running:
					stopPresentationClient();
				}
			}
		});
		menu_funktionen.add(presentationMenuItem);

		menuItem = new JMenuItem("Turn camera left");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.getCameraData().adjustCameraLeft(2000);
				} catch (WRPException e2) {
					e2.printStackTrace();
				}
			}
		});
		menu_kamera.add(menuItem);

		menuItem = new JMenuItem("Take photo");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.getCameraData().takePhoto();
					l1.setText("Photo taken!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menu_kamera.add(menuItem);

		menuItem = new JMenuItem("Wander");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							l1.setText("Wander initiated");
							c.getMove().wander(new Point(3367, -1747),
									new Point(6274, 1620),
									new Point(-334, 1570));
						} catch (WRPException e2) {
							e2.printStackTrace();
						}
					}
				});
				t.start();
			}
		});
		menu_funktionen.add(menuItem);

		menuItem = new JMenuItem("Find Person");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							ClientExample.startDetection();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				});
				t.start();
			}
		});
		menu_kamera.add(menuItem);

		menuItem = new JMenuItem("Switch modi");
		menu_kamera.add(menuItem);

		menuItem = new JMenuItem("Info");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon ic = new ImageIcon(getClass().getResource(
						"../img/robi.jpg"));
				JOptionPane
						.showMessageDialog(
								new JFrame(),
								new String(
										"<html> <body><center><h2><b>Diogenes Robot control r124<b/></h2> "
												+ "<br> This client has been developed at Hochschule RheinMain, <br> for the modula software engineering project <br><br> <b>Developers are:</b><br><br> "
												+ "Oliver Kieven (oliver-kieven@gmx.de)<br> Philip Koch (pkoch88@googlemail.com)<br> "
												+ "Dirk Stanke (dirk.stanke@googlemail.com)<br> Marc Stanke (marc.stanke@googlemail.com)<br> Daniel Ernst (daniel.ernst01@googlemail.com)<br>"
												+ " Gayatri Patel (gayatrijapan@googlemail.com)<br>"
												+ "</center></body></html>"),
								"Info", JFrame.EXIT_ON_CLOSE, ic);

			}
		});
		menu_ueber.add(menuItem);

		menuItem = new JMenuItem("Help");
		menu_ueber.add(menuItem);

		menuBar.add(menu_datei);
		menuBar.add(menu_funktionen);
		menuBar.add(menu_kamera);
		menuBar.add(menu_ueber);
		return menuBar;
	}

	/**
	 * Gets the l1.
	 * 
	 * @return the l1
	 * @uml.property name="l1"
	 */
	public JLabel getL1() {
		return l1;
	}

	/**
	 * Sets the l1.
	 * 
	 * @param l1
	 *            the new l1
	 * @uml.property name="l1"
	 */
	public void setL1(JLabel l1) {
		this.l1 = l1;
	}

	/**
	 * Start presentation client.
	 * 
	 * @param port
	 *            the port
	 */
	private void startPresentationClient(int port) {
		// start clientstuff now
		presentationClient = new Client(c.getIP(), port,
				presentationExceptionListener, new PacketContainer(), c
						.getLocation());
		// own thread
		presentationClient.start();
		// wait for client to finish initialization
		synchronized (presentationExceptionListener) {
			try {
				presentationExceptionListener.wait();
			} catch (InterruptedException ie) {
				// the client usually should not interrupt,
				// but warn the user if it happens anyway
				JOptionPane.showMessageDialog(new JFrame(),
						"Clients' synchronization interrupted,\n"
								+ "This may cause bad Exception-Handling:\n"
								+ ie.getMessage(), "Warning",
						JOptionPane.WARNING_MESSAGE, null);
			}
		}
		// finished waiting for initialization
		// now check if exception occurred in the client, e.g. "no connection"
		try {
			// throws exception if client got exception, else does nothing
			presentationExceptionListener.throwException();
			// will only be set if NO exception occurred:
			isPresentationRunning = true;
			presentationMenuItem.setText("Stop presentation mode");
			l1.setText("Presentation mode started");
		} catch (Throwable e1) {
			// popup exception, don't set labeltext
			JOptionPane.showMessageDialog(new JFrame(),
					"An Error occurred during the initialization of the Client:\n"
							+ e1.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE, null);
		}
		// now finished with:
		// - client-connection started in own thread
		// - waited for client's connection to finish
		// - read any exceptions if there were any
		// and reacted with error-popups
	}

	/**
	 * Stop presentation client.
	 */
	private void stopPresentationClient() {
		try {
			presentationClient.disconnect();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Couldn't stop Presentation-Mode:\n" + e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE, null);
		}
		// no matter what happened, this stuff will be set
		isPresentationRunning = false;
		presentationMenuItem.setText("Start presentation mode");
		l1.setText("Presentation mode stopped");
	}

	public void setCamEnabled(boolean camEnabled) {
		this.camEnabled = camEnabled;
	}

	public boolean isCamEnabled() {
		return camEnabled;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					// Connection c = new Connection("10.18.72.254", 33333);
					Connection c = new Connection("localhost", 33333);
					new GuiModel(c, new Map(c));
				} catch (WRPException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
