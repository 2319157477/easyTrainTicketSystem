package tools;

import javax.swing.*;
import java.awt.Image;
public class createLabelImage {
    public createLabelImage(JLabel label, String filename) {
        int width = label.getWidth();
        int height = label.getHeight();
        String file = "Images\\" + filename;
        ImageIcon image = new ImageIcon(file);
        image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT ));
        label.setIcon(image);
        label.setSize(width, height);
    }
}
