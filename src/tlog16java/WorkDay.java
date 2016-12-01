
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
    
    private final List<Task> tasks = new ArrayList<>();
    private long requiredMinPerDay=450;
    private LocalDate actualDay;
    private long sumPerDay;
    
    public LocalDate getActualDay()
    {
        return actualDay;
    }
    
    public long getExtraMinPerDay()
    {
        return (sumPerDay-requiredMinPerDay);
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
    
    public long getRequiredMinPerDay() {
        return requiredMinPerDay;
    }

    public void setRequiredMinPerDay(long requiredMinPerDay) {
        this.requiredMinPerDay = requiredMinPerDay;
    }

    public long getSumPerDay() {
        return sumPerDay;
    }

    public void setSumPerDay(long sumPerDay) {
        this.sumPerDay = sumPerDay;
    }
    
    
    
    public long getMinPerTask(Task t)
    {
        return (t.getEndTime().getHour()*60+t.getEndTime().getMinute())-(t.getStartTime().getHour()*60+t.getStartTime().getMinute());
    }
    
    public void addTask(Task t)
    {
        tasks.add(t);
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

    public WorkDay(LocalDate actualDay) {
        this.actualDay = actualDay;
    }
    
    
    
}
