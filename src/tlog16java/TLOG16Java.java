package tlog16java;
import java.time.*;

/*
 * @author precognox kkovacs
 */

public class TLOG16Java {








   
    public static void main(String[] args) {
        TimeString h = new TimeString();
        boolean y=isWeekDay();
        h.H1 = "1";
        h.H2 = "3";
        h.M1 = "4";
        h.M2 = "9";
        LocalTime t = stringToLocalTime(h);
        String a = t.toString();
        System.out.println(a);
        if(y)  System.out.println("Weekday");
    
    }
}
