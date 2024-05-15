import InputArea.TextInput;
import SideBar.SideBar;
import SideBar.CreateFile;
import SideBar.OpenFile;
import SideBar.SaveButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Main {

    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("FrostByte");
        frame.setSize(new Dimension(1280, 720));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        TextInput textField = new TextInput();
        SideBar sideBarPanel = new SideBar(textField);

        JPanel textFieldPanel = new JPanel(new BorderLayout());
        textFieldPanel.setBackground(new Color(100, 0, 0));

        CreateFile createFile = new CreateFile(sideBarPanel);
        OpenFile openFile = new OpenFile(sideBarPanel, textField);

        SaveButton saveButton = new SaveButton(sideBarPanel, textField);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.15;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(sideBarPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.85;
        textFieldPanel.add(textField, BorderLayout.CENTER);
        frame.add(textFieldPanel, gbc);

        JMenuBar menuBar = getMenuBar(createFile, openFile, saveButton);
        frame.setJMenuBar(menuBar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JMenuBar getMenuBar(CreateFile createFile, OpenFile openFile, SaveButton saveButton) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        newMenuItem.addActionListener(e -> createFile.doClick());
        openMenuItem.addActionListener(e -> openFile.doClick());
        saveMenuItem.addActionListener(e -> saveButton.doClick());

        exitMenuItem.addActionListener(e -> {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        return menuBar;
    }
}
