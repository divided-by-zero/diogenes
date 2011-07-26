package de.hsrm.diogenes.faceClientExample;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.mhendred.face4j.DefaultFaceClient;
import com.github.mhendred.face4j.FaceClient;
import com.github.mhendred.face4j.exception.FaceClientException;
import com.github.mhendred.face4j.exception.FaceServerException;
import com.github.mhendred.face4j.model.Face;
import com.github.mhendred.face4j.model.Photo;

/**
 * Example for the face detection
 */
public class ClientExample 
{
	
	/** The Constant API_KEY. */
	private static final String API_KEY = "0a4fdfea7a4d4bba057edb0614c0b016";
	
	/** The Constant API_SEC. */
	private static final String API_SEC = "c6103059b1be11631fca3c6eda64460d";

//	private static final String URL_WITH_FACES = "/home/olli/Desktop/Bilder/DSCF0493.jpg";

	/** The Constant NAMESPACE. */
private static final String NAMESPACE = "Diogenes";

	/** The Constant USER. */
	private static final String USER = "Oliver_Kieven";
	
	/** The Constant USER_ID. */
	private static final String USER_ID = USER + "@" + NAMESPACE;
	
	/**
	 * Start detection.
	 *
	 * @throws FaceClientException the face client exception
	 * @throws FaceServerException the face server exception
	 */
	public static void startDetection() throws FaceClientException, FaceServerException{
		
		FaceClient faceClient = new DefaultFaceClient(API_KEY, API_SEC);
    	
    	/**
    	 * First we detect some faces in a url. This URL has a single face, So we get back one
    	 * Photo object with one Face object in it.
    	 * 
    	 * You can pass more than one URL (comma delimited String) or you can pass an image file    
    	 * 
    	 * @see http://developers.face.com/docs/api/faces-detect/
    	 * @see http://developers.face.com/docs/api/faces-recognize/
    	 */
    	File file = new File("src/B_000002.jpg");
    	Photo photo = faceClient.detect(file);
//    	Photo photo = faceClient.detect(file).get(0);
    
    	/*
    	 * Now we pull out the temporary tag and call save with the desired username and label.
    	 */
    	Face f = photo.getFace();
    	faceClient.saveTags(f.getTID(), USER_ID, "a label");
    	
    	/*
    	 * Let get the training status for this user now. We should see training in progress TRUE
    	 * because we havent called train yet.
    	 */
    	faceClient.status(USER_ID);
    	
    
    	/*
    	 * IMPORTANT: Now we call train on our untrained user. This will commit our saved tag for this user to
    	 * the database so we can recognize them later with 'recognize' calls   
    	 */
//    	faceClient.train(USER_ID);
    	
    	/**
    	 * Now we can call recognize. Look for any user in our index (we only have one now)
    	 * We should see a guess now
    	 */
//    	photo = faceClient.recognize(URL_WITH_FACES, "all@" + NAMESPACE).get(0);
    	photo = faceClient.recognize(file, "all@" + NAMESPACE);
    	
    	for (Face face : photo.getFaces()) {
    		JOptionPane.showMessageDialog(new JFrame(),
    				"The person on the foto is : " + face.getGender() + "\n" +
    				"Is the person wearing some glasses? " + (face.isWearingGlasses() ? "Yes" : "No") + "\n" +
    				"He/She is smiling? " + (face.isSmiling() ? "Yes" : "No") + "\n\n" +
    				face.getGuess(), "Face Detection", JOptionPane.INFORMATION_MESSAGE		
    		);
    	}
	}
}