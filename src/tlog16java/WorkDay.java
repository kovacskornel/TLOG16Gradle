
package tlog16java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import tlog16java.Exceptions.NegativeMinutesOfWorkException;
import tlog16java.Exceptions.FutureWorkException;
import tlog16java.Exceptions.NoTaskException;

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
    
    public boolean isWeekDay(LocalDate date)
    {
        return !((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY));
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
    
    public LocalTime endTimeOfTheLastTask()
    {
        if(getTasks().isEmpty()) return null;
        return getTasks().get(getTasks().size()-1).getEndTime();
    }
    
    public boolean isSeparatedTime()
    {       
        int i,j;
        LocalTime a,b,c,d;
        boolean after = true, before = true;
        if(getTasks().isEmpty()) throw new NoTaskException();
        for (i=0;i<getTasks().size()-1;i++)
        {
            a = getTasks().get(i).getStartTime();
            b = getTasks().get(i).getEndTime();
            for(j=i+1;i<getTasks().size();i++)
            {
                    c = getTasks().get(j).getStartTime();
                    d = getTasks().get(j).getEndTime();
                    after = (a.isBefore(c) && a.isBefore(d)) ||( a.isAfter(c) && a.isAfter(d));
                    before = (b.isBefore(c) && b.isBefore(d)) || (b.isBefore(c) && b.isBefore(d));
                    if(!after || !before) return false;
            }
        }
        return true;
    }
    
}
