package tlog16java;

import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;
import tlog16java.Exceptions.WeekendNotEnabledException;

/**
 *
 * @author precognox
 */
public class WorkMonth {
        
    private List<WorkDay> days = new ArrayList<>();
    YearMonth date;
    private long sumPerMonth;
    private long requiredMinPerMonth;
    private boolean isWeekendEnabled = false;
    
    public boolean IsNewDate(WorkDay x)
    {
        return x != null;
    }
    
    public void addWorkDay(WorkDay wd, boolean isWeekendEnabled) 
    {
            if (isWeekendEnabled == true) {
                days.add(wd);
            }else if((!wd.isWeekDay(wd.getActualDay())) && isWeekendEnabled == false){
                throw new WeekendNotEnabledException();
        }
            else if(isWeekendEnabled == false && wd.isWeekDay(wd.getActualDay()))
            {
                days.add(wd);
            }
    }
    
    public void addWorkDay(WorkDay wd) 
    {
        addWorkDay(wd,isWeekendEnabled);
    }
    
    public long getExtraMinPerMonth()
    {
        int i;
        long h=0;
        for(i=0;i<days.size();i++)
        {
            h += days.get(i).getExtraMinPerDay();
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
        long spm = 0;
        int i;
            for(i=0;i<days.size();i++)
            {
                spm += days.get(i).getSumPerDay();
            }
        return spm;
    }

    public void setSumPerMonth(long sumPerMonth) {
        this.sumPerMonth = sumPerMonth;
    }

    public long getRequiredMinPerMonth() {
        long rpm = 0;
        int i;
            for(i=0;i<days.size();i++)
            {
                rpm += days.get(i).getRequiredMinPerDay();
            }
        this.requiredMinPerMonth = rpm;
        return rpm;
    }

    public void setIsWeekendEnabled(boolean a)
    {
        this.isWeekendEnabled = a;
    }
    
    public void setRequiredMinPerMonth(long requiredMinPerMonth) {
        this.requiredMinPerMonth = requiredMinPerMonth;
    }

    public WorkMonth(YearMonth date) {
        this.date = date;
    }
    
    public boolean isSameMonth(WorkDay wd)
    {
        return (wd.getActualDay().getMonth().equals(date.getMonth()) && wd.getActualDay().getYear() == date.getYear() );
    }
    
}

