package Util;

import java.awt.*;
import javax.swing.*;

class DrawText extends JLabel {
    public DrawText(JFrame frame, String text, int fontSize, Color textColor, boolean bold) {
        setText(text);

        if (bold) {
            setFont(new Font("Arial", Font.BOLD, fontSize));
        } else {
            setFont(new Font("Arial", Font.PLAIN, fontSize));
        }

        setForeground(textColor);

        frame.add(this);
    }
}
