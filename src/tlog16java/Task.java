package tlog16java;

import java.time.LocalTime;

/**
 *
 * @author precognox
 */
public class Task{
    
    public boolean isValidRedmineTaskId(String ID)
    {
        return ID.matches("[0-9]+") && ID.length() == 4;
    }
    
    public boolean isValidLTTaskId(String ID)
    {
        String[] half = ID.split("-");
        return "LT-".equals(half[0]) && half[1].matches("[0-9]+") && half[1].length() == 4;
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
    
    public void setTaskId(String taskId)
    {
        if(taskId != null && taskId.length() > 0) this.taskId = taskId;
    }
    
    public StartTime getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(int h, int m)
    {
        if(m > 0 || h > 0)
        {
            startTime.startHour = h;
            startTime.startMin = m;
        }
    }
    
    public void setStartTime(TimeString time)
    {        
            startTime.startHour = stringToLocalTime(time).getHour();
            startTime.startMin = stringToLocalTime(time).getMinute();
    }
    
    public EndTime getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(int h, int m)
    {
        if(m > 0 || h > 0)
        {
            endTime.endHour = h;
            endTime.endMin = m;
        }
    }
    public void setEndTime(TimeString time)
    {        
            endTime.endHour = stringToLocalTime(time).getHour();
            endTime.endMin = stringToLocalTime(time).getMinute();
    }    
    public String getComment()
    {
        return comment;
    }
    
    public void setComment(String comment)
    {
        if(comment != null && comment.length() > 0) this.comment = comment;
    }
    
    public boolean isMultipleQuarterHour(long min)
    {
        return min%15==0;
    } 
}
