package page.Actions.Train.AdminActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Objects;

public class DateSystem extends JDialog {
    private JComboBox<String> hourComboBox_1, hourComboBox_2;
    private JComboBox<String> minuteComboBox_1, minuteComboBox_2;
    private JButton[] dayButtons;
    private JButton previousButton, nextButton;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private String currentHour_1, currentHour_2;
    private String currentMinute_1, currentMinute_2;
    private int currentWeekday;
    private JLabel monthLabel;
    private final Calendar calendar, cld_For_Weekday, cld_For_Select;

    public DateSystem(JDialog parent) {
        super(parent, "选择日期", true);
        calendar = Calendar.getInstance();
        cld_For_Weekday = Calendar.getInstance();
        cld_For_Select = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        //currentHour_1 = calendar.get(Calendar.HOUR_OF_DAY);
        //currentMinute_1 = calendar.get(Calendar.MINUTE);
        cld_For_Weekday.set(currentYear, currentMonth, 1);
        currentWeekday = cld_For_Weekday.get(Calendar.DAY_OF_WEEK) - 1;
        //initializeUI();
    }

    public void initializeUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel topPanelUp = new JPanel(new FlowLayout());

        cld_For_Select.set(Calendar.YEAR, currentYear);
        cld_For_Select.set(Calendar.MONTH, currentMonth);
        cld_For_Select.add(Calendar.MONTH, -1);
        String pre = (cld_For_Select.get(Calendar.YEAR) + "年" + cld_For_Select.get(Calendar.MONTH) + "月");
        cld_For_Select.add(Calendar.MONTH, 2);
        String next = (cld_For_Select.get(Calendar.YEAR) + "年" + cld_For_Select.get(Calendar.MONTH) + "月");

        previousButton = new JButton(pre);
        previousButton.addActionListener(e -> changeMonth(-1));

        nextButton = new JButton(next);
        nextButton.addActionListener(e -> changeMonth(1));

        JPanel weekDay = new JPanel(new GridLayout(1, 7));
        JLabel[] weekdays = new JLabel[7];
        for (int i = 0; i < 7; i ++) {
            weekdays[i] = new JLabel();
            weekdays[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
        weekdays[0].setText("日");
        weekdays[1].setText("一");
        weekdays[2].setText("二");
        weekdays[3].setText("三");
        weekdays[4].setText("四");
        weekdays[5].setText("五");
        weekdays[6].setText("六");
        for (int i = 0; i < 7; i ++) weekDay.add(weekdays[i]);

        monthLabel = new JLabel(getMonthString(currentMonth) + " " + currentYear);

        topPanelUp.add(previousButton);
        topPanelUp.add(monthLabel);
        topPanelUp.add(nextButton);

        topPanel.add(topPanelUp, BorderLayout.NORTH);
        topPanel.add(weekDay, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(0, 7));
        dayButtons = new JButton[37];
        for (int i = 0; i < dayButtons.length; i++) {
            final int day = i + 1 - currentWeekday;
            dayButtons[i] = new JButton(String.valueOf(day));
            dayButtons[i].addActionListener(e -> selectDay(day));
            int selectButton = i;
            dayButtons[i].addActionListener(e -> changeColor(dayButtons[selectButton]));
            mainPanel.add(dayButtons[i]);
        }
        add(mainPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        hourComboBox_1 = new JComboBox<>();
        hourComboBox_2 = new JComboBox<>();
        minuteComboBox_1 = new JComboBox<>();
        minuteComboBox_2 = new JComboBox<>();
        for (int i = 0; i < 24; i++) {
            String timeString = String.format("%02d", i);
            hourComboBox_1.addItem(timeString);
            hourComboBox_2.addItem(timeString);
        }

        for (int i = 0; i < 60; i += 10) {
            String numberString = String.format("%02d", i);
            minuteComboBox_1.addItem(numberString);
            minuteComboBox_2.addItem(numberString);
        }

        JButton submitButton = new JButton("确定");
        submitButton.addActionListener(e -> submitDateTime());

        bottomPanel.add(new JLabel("开始时间 - 小时:"));
        bottomPanel.add(hourComboBox_1);
        bottomPanel.add(new JLabel("分钟:"));
        bottomPanel.add(minuteComboBox_1);
        bottomPanel.add(new JLabel("结束时间 - 小时:"));
        bottomPanel.add(hourComboBox_2);
        bottomPanel.add(new JLabel("分钟:"));
        bottomPanel.add(minuteComboBox_2);
        bottomPanel.add(submitButton);

        add(bottomPanel, BorderLayout.SOUTH);
        previousButton.doClick();
        nextButton.doClick();
        repaint();
        revalidate();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void changeMonth(int delta) {
        calendar.add(Calendar.MONTH, delta);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        cld_For_Weekday.set(currentYear, currentMonth, 1);
        currentWeekday = cld_For_Weekday.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0; i < 37; i ++) {
            dayButtons[i].setBackground(null);
        }
        updateCalendar();
    }

    private void selectDay(int day) {
        currentDay = day;
    }

    private void submitDateTime() {
        currentHour_1 = (String) Objects.requireNonNull(hourComboBox_1.getSelectedItem());
        currentMinute_1 = (String) Objects.requireNonNull(minuteComboBox_1.getSelectedItem());
        currentHour_2 = (String) Objects.requireNonNull(hourComboBox_2.getSelectedItem());
        currentMinute_2 = (String) Objects.requireNonNull(minuteComboBox_2.getSelectedItem());
        Object[] options = {"确定", "取消"};
        String msg = "选择日期和时间: " +
                currentYear + "-" + (currentMonth + 1) + "-" + currentDay + " " +
                currentHour_1 + ":" + currentMinute_1 + "~" + currentHour_2 + ":" + currentMinute_2;
        int result = JOptionPane.showOptionDialog(
                this,
                msg, // 示例日期和时间
                "选择日期和时间",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (result == JOptionPane.OK_OPTION) {
            this.dispose();
        }
    }

    private void updateCalendar() {

        int crtm;
        cld_For_Select.set(Calendar.YEAR, currentYear);
        cld_For_Select.set(Calendar.MONTH, currentMonth);
        cld_For_Select.add(Calendar.MONTH, -1);
        crtm = cld_For_Select.get(Calendar.MONTH) + 1;
        String pre = (cld_For_Select.get(Calendar.YEAR) + "年" + crtm + "月");
        cld_For_Select.add(Calendar.MONTH, 2);
        crtm = cld_For_Select.get(Calendar.MONTH) + 1;
        String next = (cld_For_Select.get(Calendar.YEAR) + "年" + crtm + "月");
        previousButton.setText(pre);
        nextButton.setText(next);

        crtm = currentMonth + 1;
        monthLabel.setText(currentYear + "年" + crtm + "月");
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + currentWeekday;
        for (int i = 0; i < dayButtons.length; i++) {
            final int day = i + 1 - currentWeekday;
            dayButtons[i].setVisible(i < maxDay);
            dayButtons[i].setEnabled(i < maxDay);
            if (i < maxDay && day > 0) {
                dayButtons[i].setText(String.valueOf(day));
                for (ActionListener al : dayButtons[i].getActionListeners()) {
                    dayButtons[i].removeActionListener(al);//去除所有现存监视器
                }
                dayButtons[i].addActionListener(e -> selectDay(day));
                int selectButton = i;
                dayButtons[i].addActionListener(e -> changeColor(dayButtons[selectButton]));
            } else {
                dayButtons[i].setVisible(false);
                dayButtons[i].setEnabled(false);
            }
        }
        repaint();
    }

    private void changeColor(JButton jButton) {
        for (int i = 0; i < 37; i ++ ) {
            dayButtons[i].setBackground(null);
        }
        jButton.setBackground(new Color(100,149,237));
    }


    private String getMonthString(int month) {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return months[month];
    }

    private String[] generateNumbers(int end) {
        String[] numbers = new String[end + 1];
        for (int i = 0; i <= end; i++) {
            numbers[i] = String.format("%02d", i);
        }
        return numbers;
    }

    public String getFormattedDateTime() {
        String Month = String.format("%02d", (currentMonth + 1));
        return currentYear + "-" + Month + "-" + currentDay + "-" +
                currentHour_1 + ":" + currentMinute_1 + "-" + currentHour_2 + ":" + currentMinute_2;
    }

}
