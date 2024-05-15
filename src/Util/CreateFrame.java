package Util;

import java.awt.Color;
import javax.swing.JFrame;

public class CreateFrame extends JFrame{

    public CreateFrame() {
        this.setTitle("Hello world!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);

        this.getContentPane().setBackground(new Color(30, 30, 30));
        this.setVisible(true);
    }
}