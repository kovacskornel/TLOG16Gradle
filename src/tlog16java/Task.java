package tlog16java;

import java.time.LocalTime;

/**
 *
 * @author precognox
 */
public class Task{
    
    public boolean isValidTaskId(String ID)
    {
        if (ID.matches("[0-9]+") && ID.length() == 4) return true;
        else return (ID.matches("[0-9]+")) && (ID.startsWith("LT-")) && (ID.length() == 7 );
    }
    
    public class StartTime{
          int startHour;
          int startMin;
    }
    public class EndTime{
          int endHour;
          int endMin;
    }
   
    public class TimeString{
        String H1;
        String H2;
        String M1;
        String M2;
    }
 
    public LocalTime stringToLocalTime(TimeString a){
    LocalTime x;
    String v = a.H1 + a.H2 + ":" +a.M1+a.M2;
    x = LocalTime.parse(v);
    return x;
    }
    
    String taskId;
    StartTime startTime;
    EndTime endTime;
    String comment;
    
    public String getTaskId()
    {
        return taskId;        
    }
    
    public StartTime getStartTime()
    {
        return startTime;
    }
    
    public EndTime getEndTime()
    {
        return endTime;
    }
    
    public String getComment()
    {
        return comment;
    }
    
    public boolean isMultipleQuarterHour(long min)
    {
        return min%15==0;
    } 
}
