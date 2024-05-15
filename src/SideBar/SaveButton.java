package SideBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class SaveButton extends JButton {
    private final JTextArea textArea;
    private final SideBar sideBar;

    public SaveButton(SideBar sideBarDisplay, JTextArea textArea) {
        this.textArea = textArea;
        this.sideBar = sideBarDisplay;

        this.addActionListener(new SaveButton.NewActionListener());
    }

    class NewActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<File> projectFiles = sideBar.getFiles();
            File currentFile = SideBar.getCurrentFile();
            String textContent = textArea.getText();

            if (currentFile == null) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileChooser.getSelectedFile();
                    sideBar.addFile(currentFile);
                } else {
                    System.out.println("Save command cancelled by user.");
                    return;
                }
            }

            try {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                    writer.write(textContent);
                    System.out.println("File saved successfully: " + currentFile.getAbsolutePath());
                }
            } catch (IOException ex) {
                System.out.println("Error saving file: " + ex.getMessage());
            }
        }
    }
}
