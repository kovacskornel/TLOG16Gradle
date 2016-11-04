package tlog16java;

import java.time.LocalTime;

/**
 *
 * @author precognox
 */
public final class Task{
    
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
 
    public LocalTime stringToLocalTime(String a){
    int h, m;
    String[] parts = a.split(":");
    h = Integer.parseInt(parts[0]);
    m = Integer.parseInt(parts[1]);
    LocalTime x = LocalTime.of(h, m);
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
    
    public void setStartTime(LocalTime time)
    {        
            startTime.startHour = time.getHour();
            startTime.startMin = time.getMinute();
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
    public void setEndTime(LocalTime time)
    {        
            endTime.endHour = time.getHour();
            endTime.endMin = time.getMinute();
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
