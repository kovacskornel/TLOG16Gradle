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
 
    public LocalTime stringToLocalTime(String a){
    int h, m;
    String[] parts = a.split(":");
    h = Integer.parseInt(parts[0]);
    m = Integer.parseInt(parts[1]);
    LocalTime x = LocalTime.of(h, m);
    return x;
    }
    
    private String taskId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comment;


    public Task(String taskId, LocalTime startTime, String comment) {
        this.taskId = taskId;
        this.startTime = startTime;
        this.comment = comment;
    }
    
     public Task(String taskId, String sstring, String comment) {
        this.taskId = taskId;
        this.comment = comment;
        this.startTime = stringToLocalTime(sstring);
    }   

    public String getTaskId() {
        return taskId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    


    public boolean isMultipleQuarterHour(long min)
    {
        return min%15==0;
    } 
    
}
