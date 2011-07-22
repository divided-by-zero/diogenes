package de.hsrm.diogenes.fileChooser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFileChooser;

public class FileOpen {

    public static void main(String[] args) {
        FileOpen fo = new FileOpen();
        fo.oeffnen();
    }

    private void oeffnen() {
        final JFileChooser chooser = new JFileChooser("Bitte Verzeichnis auswaehlen");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        final File file = new File("/home/Pictures");

        chooser.setCurrentDirectory(file);

        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
                        || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                    @SuppressWarnings("unused")
					final File foo = (File) e.getNewValue();
                }
            }
        });

        chooser.setVisible(true);
        final int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File inputVerzFile = chooser.getSelectedFile();
            String inputVerzStr = inputVerzFile.getPath();
            System.out.println("Eingabepfad:" + inputVerzStr);
        }
        
        System.out.println("Abbruch");
        chooser.setVisible(false);
    }
} 