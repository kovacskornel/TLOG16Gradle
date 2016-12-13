
package tlog16java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import tlog16java.Exceptions.NegativeMinutesOfWorkException;
import tlog16java.Exceptions.FutureWorkException;
import tlog16java.Exceptions.NoTaskException;
import tlog16java.Exceptions.NotSeparatedTimesException;

/**
 *
 * @author precognox
 */
@lombok.Getter
public class WorkDay{

    private final List<Task> tasks = new ArrayList<>();
    private long requiredMinPerDay=450;
    private LocalDate actualDay;
    private long sumPerDay;
    
    public long getExtraMinPerDay()
    {
        long x;
        x = getSumPerDay() - requiredMinPerDay;
        return x;
    }
    
    public final void setActualDay(LocalDate date)
    {
        if(date.isAfter(LocalDate.now())) throw new FutureWorkException();
        else actualDay = date;
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

    public void setRequiredMinPerDay(long requiredMinPerDay) {
        this.requiredMinPerDay = requiredMinPerDay;
    }

    public void setSumPerDay(long sumPerDay) {
        this.sumPerDay = sumPerDay;
    }
    
    
    
    public void addTask(Task t)
    {
        if(tasks.isEmpty())
        {
            tasks.add(t);
        }
        else if(!isSeparatedTime(t)) throw new NotSeparatedTimesException();
        else tasks.add(t);
        
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
    
    public WorkDay(LocalDate date)
    {
       setActualDay(date);
       requiredMinPerDay = 450;
    }
    
    public LocalTime endTimeOfTheLastTask()
    {
        if(tasks.isEmpty()) return null;
        return tasks.get(tasks.size()-1).getEndTime();
    }
    
    public final boolean isSeparatedTime(Task t)
    {       
        int i,j;
        LocalTime a,b,c,d;
        if(tasks.isEmpty()) throw new NoTaskException();

            a = t.getStartTime();
            b = t.getEndTime();
            for(j=0;j<tasks.size();j++)
            {
                    boolean after,before;
                    c = tasks.get(j).getStartTime();
                    d = tasks.get(j).getEndTime();
                    after = (a.isBefore(c) && a.isBefore(d)) ||( a.isAfter(c) && a.isAfter(d));
                    before = (b.isBefore(c) && b.isBefore(d)) || (b.isAfter(c) && b.isAfter(d));
                    if(!after || !before) {
                        if(c.equals(b) || d.equals(a)){
                        }else return false;
                    } else {
                    }
            }
        
        return true;
    }
    
}
