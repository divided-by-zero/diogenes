package de.hsrm.diogenes.gui;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton robi;
	private JButton cam;
	private JButton left;
	private JButton right;
	private JButton up;
	private JButton down;
	private JTextField param;
	
	public ButtonPanel(){
		this.robi = new JButton("Robot");
		this.cam = new JButton("Camera");
		this.left = new JButton(new ImageIcon(getClass().getResource("../img/pfeilLi.JPG")));
		this.right = new JButton(new ImageIcon(getClass().getResource("../img/pfeilRe.JPG")));
		this.up = new JButton(new ImageIcon(getClass().getResource("../img/pfeilHo.JPG")));
		this.down = new JButton(new ImageIcon(getClass().getResource("../img/pfeilRu.JPG")));
		this.param = new JTextField("Factor");
		
		this.setLayout(new GridBagLayout());
		this.add(this.robi, GridBagConstraintsFactory.create(0, 0, 1, 1));
		this.add(this.cam, GridBagConstraintsFactory.create(2, 0, 1, 1));
		this.add(this.up, GridBagConstraintsFactory.create(1, 1, 1, 1));
		this.add(this.left, GridBagConstraintsFactory.create(0, 2, 1, 1));
		this.add(this.param, GridBagConstraintsFactory.create(1, 2, 1, 1));
		this.add(this.right, GridBagConstraintsFactory.create(2, 2, 1, 1));
		this.add(this.down, GridBagConstraintsFactory.create(1, 3, 1, 1));
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		//f.setLayout(new GridBagLayout());
		f.add(new ButtonPanel());
		f.pack();
		f.setVisible(true);
	}
	
}
