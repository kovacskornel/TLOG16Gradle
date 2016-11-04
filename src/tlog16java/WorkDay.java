
package tlog16java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author precognox
 */
public class WorkDay{
    
    public List<Task> tasks = new ArrayList<>();
    public long requiredMinPerDay=450;
    LocalDate actualDay;
    public long sumPerDay;
    
    public LocalDate getActualDay()
    {
        return actualDay;
    }
    
    public long getExtraMinPerDay(long sum,long req)
    {
        return (sum - req);
    }
    
    public List<Task> getTasks()
    {
        return tasks;
    }
    
    public long getRequired()
    {
        return requiredMinPerDay;
    }
    
    public void setRequired(long required)
    {
        if(required != 0) requiredMinPerDay = required;
    }
    
    private void setActualDay(LocalDate date)
    {
        actualDay = date;
    }
    
    public long getSum()
    {
        return sumPerDay;
    }
    
    public long getMinPerTask(Task t)
    {
        long x ;
        x = 60*t.endTime.endHour+t.endTime.endMin-(60*t.startTime.startHour+t.startTime.startMin);
        return x; 
    }
    
    public void addTask(Task t)
    {
        if(t.isMultipleQuarterHour(getMinPerTask(t)))
        {
            System.out.println("Invalid length");
        }
        else tasks.add(t);
    }
    
    public boolean isWeekDay()
    {
        LocalDate today;
        DayOfWeek x;
        today = LocalDate.now();
        x = today.getDayOfWeek();
        return !((x == DayOfWeek.SATURDAY) || (x == DayOfWeek.SUNDAY));
    }
    
    public WorkDay(LocalDate date, long reqmin)
    {
       setActualDay(date);
       requiredMinPerDay = reqmin; 
    }
}
