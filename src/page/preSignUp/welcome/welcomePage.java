package page.preSignUp.welcome;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import page.preSignUp.SignUp.*;
import page.preSignUp.logIn.*;
import sql.DBUtil;
import tools.createLabelImage;
import sql.testLink;
/*
 * Created by JFormDesigner on Sat Jul 06 14:37:36 CST 2024
 */



/**
 * @author 23191
 */
public class welcomePage extends JFrame {
    boolean isC;
	public welcomePage() {
        try {
            FileReader fileReader = new FileReader("src/sql/mysqlLogin.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String info = reader.readLine();
            if (info != null) {
                String user = info.split(":")[0];
                String pwd = info.split(":")[1];
                testLink test = new testLink();
                isC = test.test_Link(user, pwd);
                if (isC)
                    DBUtil.getInfo(user, pwd);
            }
            else isC = false;

            if (isC) {
                initComponents();
            }
            else {
                loginDB loginDB1 = new loginDB(this);
                if (loginDB1.getStat())
                    initComponents();
                else System.exit(0);
            }
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
	}
	private void signUpUser(ActionEvent e) {
		new signUpPage(this);
	}
	private void logInUser(ActionEvent e) {
		new logInPage(this);
	}
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        LogIn = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        SignUp = new JButton();

        //======== this ========
        setTitle("\u6b22\u8fce\u4f7f\u7528\u706b\u8f66\u7968\u7ba1\u7406\u7cfb\u7edf\uff01");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- LogIn ----
        LogIn.setText("\u7528\u6237\u767b\u5f55");
        LogIn.setFont(LogIn.getFont().deriveFont(LogIn.getFont().getStyle() | Font.BOLD, LogIn.getFont().getSize() + 10f));
        LogIn.addActionListener(e -> logInUser(e));
        contentPane.add(LogIn);
        LogIn.setBounds(210, 270, 130, 40);
        contentPane.add(label1);
        label1.setBounds(30, 15, 310, 240);

        //---- label2 ----
        new createLabelImage(label1, "zhongtie.png");
        label2.setEnabled(false);
        label2.setVisible(false);
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(20, 20), label2.getPreferredSize()));

        //---- SignUp ----
        SignUp.setText("\u6ce8\u518c\u7528\u6237");
        SignUp.setFont(SignUp.getFont().deriveFont(SignUp.getFont().getStyle() | Font.BOLD, SignUp.getFont().getSize() + 10f));
        SignUp.addActionListener(e -> signUpUser(e));
        contentPane.add(SignUp);
        SignUp.setBounds(30, 270, 130, 40);

        contentPane.setPreferredSize(new Dimension(375, 325));
        pack();
        setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton LogIn;
    private JLabel label1;
    private JLabel label2;
    private JButton SignUp;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
