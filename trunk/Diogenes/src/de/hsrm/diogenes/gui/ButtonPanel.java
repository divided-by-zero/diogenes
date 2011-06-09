
package de.hsrm.diogenes.gui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.fhwiesbaden.webrobbie.wrp.WRPException;
import de.hsrm.diogenes.connection.Connection;


// TODO: Auto-generated Javadoc
/**
 * The Class ButtonPanel, a Panel to control the movement
 * of the robot and the camera.
 */
public class ButtonPanel extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The robi. */
	private JButton robi;
	
	/** The cam. */
	private JButton cam;
	
	/** The left. */
	private JButton left;
	
	/** The right. */
	private JButton right;
	
	/** The up. */
	private JButton up;
	
	/** The down. */
	private JButton down;
	
	/** The param. */
	private JTextField param;
	
	/** The c. */
	private Connection c;
	
	/** The robo enabled. */
	private boolean roboEnabled;
	
	/**
	 * Instantiates a new button panel.
	 *
	 * @param c the connection
	 */
	public ButtonPanel(Connection c){
		this.roboEnabled = false;
		this.c = c;
		this.robi = new JButton("Robot");
		this.cam = new JButton("Camera");
		this.left = new JButton(new ImageIcon(getClass().getResource("../img/pfeilLi.JPG")));
		this.right = new JButton(new ImageIcon(getClass().getResource("../img/pfeilRe.JPG")));
		this.up = new JButton(new ImageIcon(getClass().getResource("../img/pfeilHo.JPG")));
		this.down = new JButton(new ImageIcon(getClass().getResource("../img/pfeilRu.JPG")));
		this.param = new JTextField("4");
		
		
		doListener();
		
		this.setLayout(new GridBagLayout());
		this.add(this.robi, GridBagConstraintsFactory.create(0, 0, 1, 1));
		this.add(this.cam, GridBagConstraintsFactory.create(2, 0, 1, 1));
		this.add(this.up, GridBagConstraintsFactory.create(1, 1, 1, 1));
		this.add(this.left, GridBagConstraintsFactory.create(0, 2, 1, 1));
		this.add(this.param, GridBagConstraintsFactory.create(1, 2, 1, 1));
		this.add(this.right, GridBagConstraintsFactory.create(2, 2, 1, 1));
		this.add(this.down, GridBagConstraintsFactory.create(1, 3, 1, 1));
		
		this.robi.setToolTipText("Enable robot control mode");
		this.cam.setToolTipText("Enable camera control mode");
		this.up.setToolTipText("Move robot/camera forward/up");
		this.left.setToolTipText("Move robot/camera left");
		this.right.setToolTipText("Move robot/camera right");
		this.down.setToolTipText("Move robot/camera backwards/down");
		this.param.setToolTipText("The moving factor");
		
	}
	
	/**
	 * Adds the needed logic to the buttons
	 */
	public void doListener(){
					
		robi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roboEnabled = true;
			}
		});

		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (roboEnabled){
					try {
						c.getMove().turnLeft(Integer.parseInt(param.getText())*10);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (WRPException e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(roboEnabled){
					try {
						c.getMove().turnRight(Integer.parseInt(param.getText())*10);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (WRPException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(roboEnabled){
					try {
						c.getMove().moveBackward(Integer.parseInt(param.getText())*10);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (WRPException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(roboEnabled){
					try {
						c.getMove().moveForward(Integer.parseInt(param.getText())*10);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (WRPException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
	}
		
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws WRPException the wRP exception
	 */
	public static void main(String[] args) throws WRPException {
		JFrame f = new JFrame();
		//f.setLayout(new GridBagLayout());
		f.add(new ButtonPanel(new Connection("localhost", 55555)));
		f.pack();
		f.setVisible(true);
	}
	
}
