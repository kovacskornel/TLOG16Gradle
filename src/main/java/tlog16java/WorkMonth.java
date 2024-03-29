package tlog16java;



import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;
import tlog16java.Exceptions.WeekendNotEnabledException;
import tlog16java.Exceptions.NotNewDateException;
import tlog16java.Exceptions.NotTheSameMonthException;

 /**
 * <h1>WorkMonth</h1>
 * This class contains methods and
 * variables to work with WorkDays
 * 
 * @author Kovács Kornél
 * @version 0.1.0
 * @since 2016-11-03
 */
@lombok.Getter
public class WorkMonth {

    private final List<WorkDay> days = new ArrayList<>();
    YearMonth date;
    private long sumPerMonth;
    private long requiredMinPerMonth;
    private boolean isWeekendEnabled = false;
    
    /**
     *
     * @param x A workday to check
     * @return boolean<br>true if the month not contains this workday
     * <br>false if the month already contains this workday
     */
    public boolean IsNewDate(WorkDay x)
    {
        int i;
        boolean a=false;
        if(days.isEmpty()) return true;
        for(i=0;i<days.size();i++)
        {
            int day1 = days.get(i).getActualDay().getDayOfMonth();
            int day2 = x.getActualDay().getDayOfMonth(); 
            if(day1!=day2) a = true;
        }
        return a;
    }
    /**
     *
     * @param isWeekendEnabled boolean - Sets the weekend work
     * <br> true - Weekend work enabled
     * <br> false - Weekend work disabled
     */
    public void setIsWeekendEnabled(boolean isWeekendEnabled) {
        this.isWeekendEnabled = isWeekendEnabled;
    }
    /**
     *Sets the minutes of working hours this month
     * @param sumPerMonth minutes of working hours this month
     */
    public void setSumPerMonth(long sumPerMonth) {
        this.sumPerMonth = sumPerMonth;
    }
    /**
     *Sets the required minutes to work this month
     * @param requiredMinPerMonth required minutes to work this month
     */
    public void setRequiredMinPerMonth(long requiredMinPerMonth) {
        this.requiredMinPerMonth = requiredMinPerMonth;
    }
    
    /**
     * Creates a {@link WorkDay}
     * @param wd {@link WorkDay}
     * @param isWeekendEnabled boolean true if you want to add a weekend day
     * <br>false (default) You can only add weekdays
     * @exception NotTheSameMonthException if the {@link WorkDay} not fits to the month
     * @exception NotNewDateException if the {@link WorkDay} already exists
     */
    public void addWorkDay(WorkDay wd, boolean isWeekendEnabled) 
    {
    if(IsNewDate(wd))
    {
        if(isSameMonth(wd))
        {
            if (isWeekendEnabled == true) {
            days.add(wd);
            }
                else if((!wd.isWeekDay(wd.getActualDay())) && isWeekendEnabled == false){
                    throw new WeekendNotEnabledException();
                }
                else if(isWeekendEnabled == false && wd.isWeekDay(wd.getActualDay()))
                {
                    days.add(wd);
                }
        }else throw new NotTheSameMonthException();
    }else throw new NotNewDateException();
    }
    
    /**
     * The same as {@link addWorkDay(WorkDay wd, boolean isWeekendEnabled) addWorkDay} but isWeekendEnabled has a default false value
     * @param wd {@link WorkDay}
     */
    public void addWorkDay(WorkDay wd) 
    {
        addWorkDay(wd,isWeekendEnabled);
    }
    
    /**
     *
     * @return Gets the extra minutes worked this month
     */
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

    /**
     *
     * @return List of the WorkDays in this month
     */
    public List<WorkDay> getDays() {
        return days;
    }
 
    /**
     *
     * @return The date of this month
     */
    public YearMonth getDate() {        
        return date;
    }

    /**
     *
     * @param date The date of this month
     */
    public void setDate(YearMonth date) {
        this.date = date;
    }

    /**
     *
     * @return The minutes worked this WorkMonth
     */
    public long getSumPerMonth() {
        long spm = 0;
        int i;
            for(i=0;i<days.size();i++)
            {
                spm += days.get(i).getSumPerDay();
            }
        return spm;
    }

    /**
     *
     * @return The required minutes to work this WorkMonth
     */
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

    public WorkMonth(YearMonth date) {
        this.date = date;
    }
    
    /**
     *
     * @param wd {@link WorkDay}
     * @return boolean<br>true if the workday fits to this month<br>false if it is not
     */
    public boolean isSameMonth(WorkDay wd)
    {
        return (wd.getActualDay().getMonth().equals(date.getMonth()) && wd.getActualDay().getYear() == date.getYear());
    }
    
}