package tlog16java;

import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author precognox
 */
public class WorkMonth {
    
    class thisYearMonth
    {
        int year = YearMonth.now().getYear();
        int month = YearMonth.now().getMonthValue();
     
        public void setDate(int y, int m)
        {
            year = y;
            month = m;
        }
    }
    

    
    List<WorkDay> days = new ArrayList<>();
    thisYearMonth date;
    long sumPerMonth;
    long requiredMinPerMonth;
 
    public boolean isSameMonth(WorkDay wd)
    {
        return (wd.actualDay.getMonthValue()) == date.month;
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
        }
}
    
    public long getExtraMinPerMonth(WorkDay wd)
    {
        long h=0;
        for(int i=1; i<YearMonth.now().lengthOfMonth();i++)
        {
            if(wd.isWeekDay())
            {
                h+=wd.getExtraMinPerDay(wd.requiredMinPerDay,wd.sumPerDay);
            }
        }
    return h;
    }
    
    public List getDays()
    {
        return days;
    }
    
    public thisYearMonth getYearMonth()
    {
        return date;
    }
    
    public long getSumPerMonth()
    {
        return sumPerMonth;
    }
    
    public long getReqMinPerMonth()
    {
        return requiredMinPerMonth;
    }
    
}

