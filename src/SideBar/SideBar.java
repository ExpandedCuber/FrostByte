package SideBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class SideBar extends JPanel {
    private ArrayList<File> projectFiles;
    private JTextArea textArea;
    private static File currentFile;

    public SideBar(JTextArea textInput) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(50, 50, 50));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        projectFiles = new ArrayList<>();
        textArea = textInput;

        update();
    }

    public void addFile(File file) {
        boolean fileAlreadyOpened = false;
        for (File existingFile : projectFiles) {
            if (file.getName().equals(existingFile.getName())) {
                System.out.println("File already opened!");
                fileAlreadyOpened = true;
                break;
            }
        }

        if (!fileAlreadyOpened) {
            System.out.println("Added file");
            projectFiles.add(file);
            update();
        }
    }

    public ActionListener changeFile(File file) {
        return e -> {
            try {
                String content = Files.readString(file.toPath());
                textArea.setText(content);
                currentFile = file;
            } catch (Exception ex) {
                System.out.println("Error reading file: " + ex.getMessage());
            }
        };
    }

    public static File getCurrentFile() {
        return currentFile;
    }

    private void addHover(JButton button) {
        Color originalColor = button.getBackground();
        Color hoverColor = new Color(45, 45, 45);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                button.setBorder(new LineBorder(Color.BLACK, 2, true));
            }
        });
    }

    public void update() {
        this.removeAll();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1, 0, 10));
        buttonPanel.setBackground(new Color(50, 50, 50));

        if (!projectFiles.isEmpty()) {
            for (File file : projectFiles) {
                JButton button = new JButton(file.getName());
                button.setBorder(new LineBorder(Color.BLACK, 3, true));
                button.setBackground(new Color(40, 40, 40));
                button.setForeground(Color.WHITE);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addHover(button);
                buttonPanel.add(button);
                button.addActionListener(changeFile(file));
            }
        } else {
            System.out.println("Array is empty!");
        }

        this.add(buttonPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public ArrayList<File> getFiles() {
        return projectFiles;
    }
}
