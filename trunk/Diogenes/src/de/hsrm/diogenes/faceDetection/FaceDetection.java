package de.hsrm.diogenes.faceDetection;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.github.mhendred.face4j.DefaultFaceClient;
import com.github.mhendred.face4j.FaceClient;
import com.github.mhendred.face4j.exception.FaceClientException;
import com.github.mhendred.face4j.exception.FaceServerException;
import com.github.mhendred.face4j.model.Face;
import com.github.mhendred.face4j.model.Photo;

/**
 * Example for the face detection
 */
public class FaceDetection {

	/** The Constant API_KEY. */
	private static final String API_KEY = "0a4fdfea7a4d4bba057edb0614c0b016";

	/** The Constant API_SEC. */
	private static final String API_SEC = "c6103059b1be11631fca3c6eda64460d";

	/** Path of the photo (can be an URL too). */
	private static final String PHOTOPATH = "/home/dirk/workspace/Diogenes/photo2";

	/** The Constant NAMESPACE. */
	private static final String NAMESPACE = "Diogenes";

	/**
	 * Train Person.
	 * 
	 * 
	 * @throws FaceClientException
	 *             the face client exception
	 * @throws FaceServerException
	 *             the face server exception
	 */
	public static void trainPerson() throws FaceClientException,
			FaceServerException {
		final JFrame frame = new JFrame("Name input");
		final JTextField tf = new JTextField();
		JButton button = new JButton("Submit");
		frame.add(tf);
		frame.add(button);
		frame.setLayout(new GridLayout());
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							FaceClient faceClient = new DefaultFaceClient(
									API_KEY, API_SEC);
							final String userID = tf.getText() + "@"
									+ NAMESPACE;
							File file = new File(PHOTOPATH);
							Photo photo = faceClient.detect(file);
							/*
							 * Now we pull out the temporary tag and call save
							 * with the desired username and label.
							 */
							Face f = photo.getFace();
							faceClient.saveTags(f.getTID(), userID, "label");
							/*
							 * Let get the training status for this user now. We
							 * should see training in progress TRUE because we
							 * havent called train yet.
							 */
							faceClient.status(userID);
							/*
							 * IMPORTANT: Now we call train on our untrained
							 * user. This will commit our saved tag for this
							 * user to the database so we can recognize them
							 * later with 'recognize' calls
							 */
							faceClient.train(userID);
							frame.dispose();
						} catch (FaceClientException e1) {
							e1.printStackTrace();
						} catch (FaceServerException e1) {
							e1.printStackTrace();
						}
					}
				});
				t.start();
			}
		});
	}

	/**
	 * Start detection.
	 * 
	 * @throws FaceClientException
	 *             the face client exception
	 * @throws FaceServerException
	 *             the face server exception
	 */
	public static void startDetection() throws FaceClientException,
			FaceServerException {

		FaceClient faceClient = new DefaultFaceClient(API_KEY, API_SEC);

		/**
		 * First we detect some faces in a url. This URL has a single face, So
		 * we get back one Photo object with one Face object in it.
		 * 
		 * You can pass more than one URL (comma delimited String) or you can
		 * pass an image file
		 * 
		 * @see http://developers.face.com/docs/api/faces-detect/
		 * @see http://developers.face.com/docs/api/faces-recognize/
		 */
		File file = new File(PHOTOPATH);
		Photo photo = faceClient.detect(file);
		/**
		 * Now we can call recognize. Look for any user in our index (we only
		 * have one now) We should see a guess now
		 */
		photo = faceClient.recognize(file, "all@" + NAMESPACE);

		for (Face face : photo.getFaces()) {
			JOptionPane.showMessageDialog(new JFrame(),
							"Is the person wearing some glasses? "
							+ (face.isWearingGlasses() ? "Yes" : "No") + "\n"
							+ "He/She is smiling? "
							+ (face.isSmiling() ? "Yes" : "No") + "\n\n"
							+ face.getGuess(), "Face Detection",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}