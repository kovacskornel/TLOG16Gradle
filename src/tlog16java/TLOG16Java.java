package tlog16java;
import java.time.*;
import java.util.List;


/*
 * @author precognox kkovacs
 */

public class TLOG16Java {
   
class Task{
    String TaskId;
    LocalTime startTime;
    LocalTime endTime;
    String Comment;
}

class WorkDay{
    List tasks;
    long requiredMinPerDay;
    LocalDate actualDay = LocalDate.now();
    long sumPerDay;
}

class WorkMonth{
    List days;
    YearMonth date;
    long sumPerMonth;
    long requiredMinPerMonth;
}

class TimeLogger{
    List months;
}
    
    public static void main(String[] args) {
        
    }
   

}


