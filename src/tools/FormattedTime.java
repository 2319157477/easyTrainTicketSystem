package tools;

public class FormattedTime {
    public String getFormattedTime(String s, String type) {
        String hour = s.split(":")[0];
        String minute = s.split(":")[1];
        if (type.equals("m")) return minute;
        else return hour;
    }

    public String getFormattedDay(String s, String type) {
        String[] format = s.split("-");
        if (type.equals("Y")) return format[0];
        else if (type.equals("M")) return format[1];
        else if (type.equals("D")) return format[2];
        else if (type.equals("P")) return format[3];
        else return format[4];
    }
}
