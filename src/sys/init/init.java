package sys.init;

import com.formdev.flatlaf.FlatDarculaLaf;
import page.preSignUp.welcome.*;
import javax.swing.*;

public class init {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new welcomePage();
    }
}