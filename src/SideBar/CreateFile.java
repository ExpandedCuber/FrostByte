package SideBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CreateFile extends JButton {

    private final SideBar sideBar;
    private File selectedFile;

    public CreateFile(SideBar sideBarPanel) {
        sideBar = sideBarPanel;

        this.addActionListener(new NewActionListener());
    }

    class NewActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();

                try {
                    boolean created = selectedFile.createNewFile();
                    if(created) {
                        System.out.println("File created " + selectedFile);

                        if(selectedFile != null) {
                            sideBar.addFile(selectedFile);

                            sideBar.update();
                        } else {
                            System.out.println("File is null!");
                        }
                    } else {
                        System.out.println("File either already exists, or couldn't be created!");
                    }
                } catch (IOException ex) {
                    System.err.println("Error creating file: " + selectedFile);
                }
            }
        }
    }
}
