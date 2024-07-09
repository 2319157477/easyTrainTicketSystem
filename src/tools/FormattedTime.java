package tools;

import java.util.Arrays;

public class FormattedTime {
    public String getFormattedTime(String s, String type) {
        //System.out.println(Arrays.toString(s.split(":")));
        String hour = s.split(":")[0];
        String minute = s.split(":")[1];
        if (type.equals("m")) return minute;
        else return hour;
    }
}
