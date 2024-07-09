package page.preSignUp.zhuce;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/*
 * Created by JFormDesigner on Sat Jul 06 14:39:50 CST 2024
 */



/**
 * @author 23191
 */
public class zhucePage extends JFrame {
	String url = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
	String user = "root";
	String password = "lbc041103";
	String upUsername = "";
	String upUserPhoneNumber = "";
	String pwd1 = "";
	String pwd2 = "";
	String upUserPwd = "";
	int upGender = 2;

	Connection zhuce_con;
	public zhucePage() {
		try {
			zhuce_con = DriverManager.getConnection(url, user, password);
		} catch (SQLException var10) {
			throw new RuntimeException(var10);
		}
		initComponents();
	}


	private void usernameFocusLost(FocusEvent e) {
		upUsername = textField1.getText();
	}

	private void userPhoneNumberFocusLost(FocusEvent e) {
		String c_phone = textField2.getText();
		boolean isSame = false;
		try {
			Statement usernameState = zhuce_con.createStatement();
			ResultSet rs = usernameState.executeQuery("SELECT * FROM user");
			System.out.println("成功连接！");
			while (rs.next()) {
				String e_phone = rs.getNString(1);
				System.out.println(e_phone);
				if (c_phone.equals(e_phone)) {
					isSame = true;
					break;
				}
			}
			if (isSame) {
				userPhoneErr.setVisible(true);
				System.out.println("phoneErr!");
				repaint();
			}
			else {
                userPhoneErr.setVisible(false);
				upUserPhoneNumber = c_phone;
                repaint();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void passwordField1FocusLost(FocusEvent e) {
		pwd1 = new String(passwordField1.getPassword());
		System.out.println(pwd1);
	}

	private void passwordFieldRepeatFocusLost(FocusEvent e) {
		pwd2 = new String(passwordField2.getPassword());
		System.out.println(pwd2);
		if (!pwd1.equals(pwd2)) {
			userPwdErr.setVisible(true);
			//System.out.println("pwdErr!");
			repaint();
		}
		else {
            userPwdErr.setVisible(false);
			upUserPwd = pwd2;
		}
	}

	private void Man(ActionEvent e) {
		upGender = 1;
	}

	private void Woman(ActionEvent e) {
		upGender = 0;
	}

	private void CreateUser(ActionEvent e) {
		if ((upGender != 2) && (!upUsername.isEmpty()) && (!upUserPwd.isEmpty()) && (!upUserPhoneNumber.isEmpty())) {
			String sql = "INSERT INTO user (user_phone, user_pwd, user_name, user_type, user_gender) VALUES (?, ?, ?, ?, ?)";
			try {
				PreparedStatement pstmt = zhuce_con.prepareStatement(sql);

				pstmt.setString(1, upUserPhoneNumber);
				pstmt.setString(2, upUserPwd);
				pstmt.setString(3, upUsername);
				pstmt.setInt(4, 0);
				pstmt.setInt(5, upGender);

				int rowsAffected = pstmt.executeUpdate();
				System.out.println("Inserted " + rowsAffected + " row(s) into the table.");
				zhuce_con.close();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		}
		else {
			JOptionPane.showMessageDialog(this,"请完善个人信息","警告！", JOptionPane.ERROR_MESSAGE);
		}
		
	}

    private void exit(ActionEvent e) {
        this.dispose();
    }
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        textField1 = new JTextField();
        textField2 = new JTextField();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        label5 = new JLabel();
        userPhoneErr = new JLabel();
        userPwdErr = new JLabel();
        ButtonGroup group = new ButtonGroup();
        radioButton1 = new JRadioButton();
        group.add(radioButton1);
        radioButton2 = new JRadioButton();
        group.add(radioButton2);
        label8 = new JLabel();

        //======== this ========
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d");
        contentPane.add(label1);
        label1.setBounds(40, 45, label1.getPreferredSize().width, 30);

        //---- label2 ----
        label2.setText("\u7535\u8bdd\u53f7\u7801");
        contentPane.add(label2);
        label2.setBounds(40, 85, label2.getPreferredSize().width, 30);

        //---- label3 ----
        label3.setText("\u5bc6\u7801");
        contentPane.add(label3);
        label3.setBounds(40, 125, label3.getPreferredSize().width, 30);

        //---- label4 ----
        label4.setText("\u786e\u8ba4\u5bc6\u7801");
        contentPane.add(label4);
        label4.setBounds(40, 165, label4.getPreferredSize().width, 30);

        //---- button1 ----
        button1.setText("\u786e\u5b9a\u521b\u5efa");
        button1.addActionListener(e -> CreateUser(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(45, 240), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.addActionListener(e -> exit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(225, 240), button2.getPreferredSize()));

        //---- textField1 ----
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                usernameFocusLost(e);
            }
        });
        contentPane.add(textField1);
        textField1.setBounds(110, 45, 200, 30);

        //---- textField2 ----
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                userPhoneNumberFocusLost(e);
            }
        });
        contentPane.add(textField2);
        textField2.setBounds(110, 85, 200, textField2.getPreferredSize().height);

        //---- passwordField1 ----
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                passwordField1FocusLost(e);
            }
        });
        contentPane.add(passwordField1);
        passwordField1.setBounds(110, 125, 200, 30);

        //---- passwordField2 ----
        passwordField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                passwordFieldRepeatFocusLost(e);
            }
        });
        contentPane.add(passwordField2);
        passwordField2.setBounds(110, 165, 200, 30);
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(330, 50), label5.getPreferredSize()));

        //---- userPhoneErr ----
        userPhoneErr.setText("\u5df2\u5b58\u5728\uff01");
        userPhoneErr.setVisible(false);
        contentPane.add(userPhoneErr);
        userPhoneErr.setBounds(335, 90, 85, userPhoneErr.getPreferredSize().height);

        //---- userPwdErr ----
        userPwdErr.setText("\u4e24\u6b21\u8f93\u5165\u4e0d\u540c\uff01");
        userPwdErr.setVisible(false);
        contentPane.add(userPwdErr);
        userPwdErr.setBounds(335, 170, 85, userPwdErr.getPreferredSize().height);

        //---- radioButton1 ----
        radioButton1.setText("\u7537");
        radioButton1.addActionListener(e -> Man(e));
        contentPane.add(radioButton1);
        radioButton1.setBounds(new Rectangle(new Point(115, 210), radioButton1.getPreferredSize()));

        //---- radioButton2 ----
        radioButton2.setText("\u5973");
        radioButton2.addActionListener(e -> Woman(e));
        contentPane.add(radioButton2);
        radioButton2.setBounds(new Rectangle(new Point(230, 210), radioButton2.getPreferredSize()));

        //---- label8 ----
        label8.setText("\u6027\u522b");
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(40, 210), label8.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(465, 320));
        pack();
        setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel label5;
    private JLabel userPhoneErr;
    private JLabel userPwdErr;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JLabel label8;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
