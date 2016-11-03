/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author precognox
 */
public class WorkDay{
    
    List tasks;
    long requiredMinPerDay=450;
    LocalDate actualDay = LocalDate.now();
    long sumPerDay;
    
    public boolean isWeekDay()
    {
        LocalDate today;
        DayOfWeek x;
        today = LocalDate.now();
        x = today.getDayOfWeek();
        return !((x == DayOfWeek.SATURDAY) || (x == DayOfWeek.SUNDAY));
    }
    
    public long getExtraMinPerDay(long sum,long req)
    {
        return (sum - req);
    }
    
    public long getRequired()
    {
        return requiredMinPerDay;
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
    }
    
}
