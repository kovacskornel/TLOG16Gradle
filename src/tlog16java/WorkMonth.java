package tlog16java;

import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author precognox
 */
public class WorkMonth {
        
    private List<WorkDay> days = new ArrayList<>();
    YearMonth date;
    private long sumPerMonth;
    private long requiredMinPerMonth;
 
    public boolean isSameMonth(WorkDay wd)
    {
        return (wd.getActualDay().getMonth() == date.getMonth());
    }
    
    public boolean IsNewDate(WorkDay x)
    {
        return x != null;
    }
    
    public void addWorkDay(WorkDay wd, boolean isWeekendEnabled) 
    {
            if (isWeekendEnabled == true) {
                days.add(wd);
            }else if(!(wd.isWeekDay()) && isWeekendEnabled == false){
                System.out.println("This is not a workday!");
        }
    }
    
    public long getExtraMinPerMonth(WorkDay wd)
    {
        long h=0;
        for(int i=1; i<YearMonth.now().lengthOfMonth();i++)
        {
            if(wd.isWeekDay())
            {
                h+=wd.getExtraMinPerDay(wd.getRequired(),wd.getSumPerDay());
            }
        }
    return h;
    }

    public List<WorkDay> getDays() {
        return days;
    }

    public void setDays(List<WorkDay> days) {
        this.days = days;
    }

    public YearMonth getDate() {
        return date;
    }

    public void setDate(YearMonth date) {
        this.date = date;
    }

    public long getSumPerMonth() {
        return sumPerMonth;
    }

    public void setSumPerMonth(long sumPerMonth) {
        this.sumPerMonth = sumPerMonth;
    }

    public long getRequiredMinPerMonth() {
        return requiredMinPerMonth;
    }

    public void setRequiredMinPerMonth(long requiredMinPerMonth) {
        this.requiredMinPerMonth = requiredMinPerMonth;
    }
    

    
}

