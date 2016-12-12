
package tlog16java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import tlog16java.Exceptions.NegativeMinutesOfWorkException;
import tlog16java.Exceptions.FutureWorkException;

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
        long x;
        x = getSumPerDay() - requiredMinPerDay;
        return x;
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
    
    public final void setActualDay(LocalDate date)
    {
        if(date.isAfter(LocalDate.now())) throw new FutureWorkException();
        else actualDay = date;
    }
    
    public long getRequiredMinPerDay() {
        return requiredMinPerDay;
    }

    public void setRequiredMinPerDay(long requiredMinPerDay) {
        this.requiredMinPerDay = requiredMinPerDay;
    }

    public long getSumPerDay() {
        long x=0;
        int i;
        for (i=0;i<tasks.size();i++)
        {
            x+=tasks.get(i).getMinPerTask();
        }
        return x;
    }

    public void setSumPerDay(long sumPerDay) {
        this.sumPerDay = sumPerDay;
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
       if(reqmin < 0) throw new NegativeMinutesOfWorkException();
       else requiredMinPerDay = reqmin;
    }

    public WorkDay(LocalDate date) {
        setActualDay(date);
    }
    
    
    
}
