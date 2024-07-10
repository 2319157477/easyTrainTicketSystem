package page.preSignUp.SignUp;

import sql.DBUtil;

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
public class signUpPage extends JDialog {
	String upUsername = "";
	String upUserPhoneNumber = "";
	String pwd1 = "";
	String pwd2 = "";
	String upUserPwd = "";
	int upGender = 2;
	public signUpPage(JFrame owner) {
        super(owner, "注册新用户", true);
		initComponents();
        setVisible(true);
	}


	private void usernameFocusLost(FocusEvent e) {
		upUsername = textField1.getText();
	}

	private void userPhoneNumberFocusLost(FocusEvent e) {
		String c_phone = textField2.getText();
		boolean isSame = false;
		try {
			Statement usernameState = DBUtil.getStatement();
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
				PreparedStatement pstmt = DBUtil.getPstmt(sql);;

				pstmt.setString(1, upUserPhoneNumber);
				pstmt.setString(2, upUserPwd);
				pstmt.setString(3, upUsername);
				pstmt.setInt(4, 0);
				pstmt.setInt(5, upGender);

				int rowsAffected = pstmt.executeUpdate();
				System.out.println("Inserted " + rowsAffected + " row(s) into the table.");
                dispose();
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
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("\u6ce8\u518c\u65b0\u7528\u6237");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(35, 30, label1.getPreferredSize().width, 30);

        //---- label2 ----
        label2.setText("\u7535\u8bdd\u53f7\u7801");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(35, 75, label2.getPreferredSize().width, 30);

        //---- label3 ----
        label3.setText("\u5bc6\u7801");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 5f));
        contentPane.add(label3);
        label3.setBounds(35, 120, label3.getPreferredSize().width, 30);

        //---- label4 ----
        label4.setText("\u786e\u8ba4\u5bc6\u7801");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 5f));
        contentPane.add(label4);
        label4.setBounds(35, 165, label4.getPreferredSize().width, 30);

        //---- button1 ----
        button1.setText("\u786e\u5b9a\u521b\u5efa");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> CreateUser(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(30, 255), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> exit(e));
        contentPane.add(button2);
        button2.setBounds(205, 255, 113, button2.getPreferredSize().height);

        //---- textField1 ----
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                usernameFocusLost(e);
            }
        });
        contentPane.add(textField1);
        textField1.setBounds(120, 35, 200, 30);

        //---- textField2 ----
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                userPhoneNumberFocusLost(e);
            }
        });
        contentPane.add(textField2);
        textField2.setBounds(120, 80, 200, textField2.getPreferredSize().height);

        //---- passwordField1 ----
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                passwordField1FocusLost(e);
            }
        });
        contentPane.add(passwordField1);
        passwordField1.setBounds(120, 120, 200, 30);

        //---- passwordField2 ----
        passwordField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                passwordFieldRepeatFocusLost(e);
            }
        });
        contentPane.add(passwordField2);
        passwordField2.setBounds(120, 165, 200, 30);
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(330, 50), label5.getPreferredSize()));

        //---- userPhoneErr ----
        userPhoneErr.setText("\u5df2\u5b58\u5728\uff01");
        userPhoneErr.setFont(userPhoneErr.getFont().deriveFont(userPhoneErr.getFont().getStyle() | Font.BOLD));
        userPhoneErr.setVisible(false);
        contentPane.add(userPhoneErr);
        userPhoneErr.setBounds(335, 85, 85, userPhoneErr.getPreferredSize().height);

        //---- userPwdErr ----
        userPwdErr.setText("\u4e24\u6b21\u8f93\u5165\u4e0d\u540c\uff01");
        userPwdErr.setFont(userPwdErr.getFont().deriveFont(Font.BOLD));
        userPwdErr.setVisible(false);
        contentPane.add(userPwdErr);
        userPwdErr.setBounds(335, 170, 85, userPwdErr.getPreferredSize().height);

        //---- radioButton1 ----
        radioButton1.setText("\u7537");
        radioButton1.setFont(radioButton1.getFont().deriveFont(radioButton1.getFont().getStyle() | Font.BOLD, radioButton1.getFont().getSize() + 5f));
        radioButton1.addActionListener(e -> Man(e));
        contentPane.add(radioButton1);
        radioButton1.setBounds(new Rectangle(new Point(160, 210), radioButton1.getPreferredSize()));

        //---- radioButton2 ----
        radioButton2.setText("\u5973");
        radioButton2.setFont(radioButton2.getFont().deriveFont(radioButton2.getFont().getStyle() | Font.BOLD, radioButton2.getFont().getSize() + 5f));
        radioButton2.addActionListener(e -> Woman(e));
        contentPane.add(radioButton2);
        radioButton2.setBounds(new Rectangle(new Point(235, 210), radioButton2.getPreferredSize()));

        //---- label8 ----
        label8.setText("\u6027\u522b");
        label8.setFont(label8.getFont().deriveFont(label8.getFont().getStyle() | Font.BOLD, label8.getFont().getSize() + 5f));
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(35, 210), label8.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(445, 320));
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
