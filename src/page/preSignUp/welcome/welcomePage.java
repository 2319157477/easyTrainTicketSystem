package page.welcome;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import page.zhuce.*;
import page.denglu.*;
import tools.createImage;
/*
 * Created by JFormDesigner on Sat Jul 06 14:37:36 CST 2024
 */



/**
 * @author 23191
 */
public class welcomePage extends JFrame {
	public welcomePage() {
		initComponents();
	}

	private void zhuceUser(ActionEvent e) {
		new zhucePage();
	}

	private void dengluUser(ActionEvent e) {
		new denluPage();
	}

	private void forgetPwd(ActionEvent e) {
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        denglu = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        zhuce = new JButton();

        //======== this ========
        setTitle("\u6b22\u8fce\u4f7f\u7528\u706b\u8f66\u7968\u7ba1\u7406\u7cfb\u7edf\uff01");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- denglu ----
        denglu.setText("\u7528\u6237\u767b\u5f55");
        denglu.addActionListener(e -> dengluUser(e));
        contentPane.add(denglu);
        denglu.setBounds(new Rectangle(new Point(225, 250), denglu.getPreferredSize()));
        contentPane.add(label1);
        label1.setBounds(35, 20, 310, 215);

        //---- label2 ----
        new createImage(label1, "zhongtie.png");
        label2.setEnabled(false);
        label2.setVisible(false);
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(20, 20), label2.getPreferredSize()));

        //---- zhuce ----
        zhuce.setText("\u6ce8\u518c\u7528\u6237");
        zhuce.addActionListener(e -> zhuceUser(e));
        contentPane.add(zhuce);
        zhuce.setBounds(new Rectangle(new Point(60, 250), zhuce.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(380, 300));
        pack();
        setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton denglu;
    private JLabel label1;
    private JLabel label2;
    private JButton zhuce;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
