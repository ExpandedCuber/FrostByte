package InputArea;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextInput extends JTextArea {

    public TextInput() {
        this.setFont(new Font("Serif", Font.PLAIN, 16));
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setTabSize(1);
        this.setBackground(new Color(30, 30, 30));
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(100, 100));
        this.setCaretColor(Color.WHITE);
        this.setBorder(new EmptyBorder(10, 10, 0, 5));
    }
}
