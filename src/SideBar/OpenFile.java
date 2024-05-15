package SideBar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OpenFile extends JButton {

    private final SideBar sideBar;
    private final JTextArea textArea;

    public OpenFile(SideBar sideBarPanel, JTextArea textArea) {
        this.sideBar = sideBarPanel;
        this.textArea = textArea;

        this.addActionListener(new NewActionListener());
    }

    class NewActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                System.out.println("File opened " + selectedFile);

                if(selectedFile != null) {
                    sideBar.addFile(selectedFile);
                    try {
                        String content = new String(Files.readAllBytes(selectedFile.toPath()));
                        textArea.setText(content);
                    } catch (IOException ex) {
                        System.out.println("Error reading file: " + ex.getMessage());
                    }
                } else {
                    System.out.println("File is null!");
                }
            } else {
                System.out.println("File either doesn't exist, or couldn't be opened!");
            }
        }
    }
}
