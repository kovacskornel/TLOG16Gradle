package tlog16java;

import java.time.LocalTime;

/**
 *
 * @author precognox
 */

public class Task{
    
    public boolean isValidRedmineTaskId(String ID)
    {
        return ID.matches("\\d{4}");
    }
    
    public boolean isValidLTTaskId(String ID)
    {
        return ID.matches("LT-\\d{4}");
    }    
 
    private LocalTime stringToLocalTime(String a){
    String[] parts = a.split(":");
    LocalTime x = LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
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
        endTime = LocalTime.of(1, 1);
    }
    
     public Task(String taskId, String sstring, String comment) {
        this.taskId = taskId;
        this.comment = comment;
        this.startTime = stringToLocalTime(sstring);
    }   

    public Task(String taskId, LocalTime startTime, LocalTime endTime, String comment) {
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
    }

    public Task(String taskId) {
        this.taskId = taskId;
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
    
    public void setStartTime(String startTime) {
        this.startTime = stringToLocalTime(startTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = stringToLocalTime(endTime);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isMultipleQuarterHour(long min)
    {
        return min%15==0;
    } 
    
}
