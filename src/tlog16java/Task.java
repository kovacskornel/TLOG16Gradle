package tlog16java;

import java.time.LocalTime;
import tlog16java.Exceptions.EmptyTimeFieldException;
import tlog16java.Exceptions.NegativeMinutesOfWorkException;
import tlog16java.Exceptions.NoTaskIDException;
import tlog16java.Exceptions.NotExpectedTimeOrderException;
import tlog16java.Exceptions.InvalidTaskIDException;

/**
 *
 * @author precognox
 */

public  class Task{
    
    public final boolean isValidRedmineTaskId(String ID)
    {
        return ID.matches("\\d{4}");
    }
    
    public final boolean isValidLTTaskId(String ID)
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

    public long getMinPerTask()
    {
        long x=0;
        if(endTime != null && getEndTime().getHour()*60+getEndTime().getMinute() > getStartTime().getHour()*60+getStartTime().getMinute())
        {
        x += (getEndTime().getHour()*60+getEndTime().getMinute())-(getStartTime().getHour()*60+getStartTime().getMinute());
        }
        else if(endTime == null) throw new EmptyTimeFieldException("Empty end time!");
        else if(getEndTime().getHour()*60+getEndTime().getMinute() > getStartTime().getHour()*60+getStartTime().getMinute()) throw new NotExpectedTimeOrderException();
        return x;
        
    }

    public Task(String taskId, LocalTime startTime, String comment) {
        if(isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId))
        {
            this.taskId = taskId;
        }else throw new InvalidTaskIDException("Invalid task ID!");
        this.startTime = startTime;
        this.comment = comment;
    }
    
     public Task(String taskId, String sstring, String comment) {
        if(isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId))
        {
            this.taskId = taskId;
        }else throw new InvalidTaskIDException("Invalid task ID!");
        this.comment = comment;
        this.startTime = stringToLocalTime(sstring);
    }   

    public Task(String startTime, String endTime)
    {
        LocalTime etime,stime;
        if(startTime != null && endTime != null)
        {
            etime = stringToLocalTime(endTime);
            stime = stringToLocalTime(startTime);
        }
        else throw new EmptyTimeFieldException("Empty time field!");
        if(stime != null && etime != null && stime.getHour()*60+stime.getMinute() < etime.getHour()*60+etime.getMinute())
        {
        this.startTime = stime;
        this.endTime = etime;
        }
        else if(stime == null || etime == null) throw new EmptyTimeFieldException("Empty time field!");
        else if((stime.getHour()*60+stime.getMinute()) > (etime.getHour()*60+etime.getMinute())) throw new NotExpectedTimeOrderException();        
    }
     
    public Task(LocalTime startTime, LocalTime endTime) {
        if(startTime != null && endTime != null && startTime.getHour()*60+startTime.getMinute() < endTime.getHour()*60+endTime.getMinute())
        {
        this.startTime = startTime;
        this.endTime = endTime;
        }
        else if(startTime == null || endTime == null) throw new EmptyTimeFieldException("Empty time field!");
        else if(startTime.getHour()*60+startTime.getMinute() > endTime.getHour()*60+endTime.getMinute()) throw new NotExpectedTimeOrderException();
    }
     
    public Task(String taskId, LocalTime startTime, LocalTime endTime, String comment) {
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
    }

    public Task(String taskId) {
        if(taskId != null && isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId))
        {
            this.taskId = taskId;
        }else if(taskId == null) throw new NoTaskIDException("No task ID!");
        else if(!isValidRedmineTaskId(taskId) || !isValidRedmineTaskId(taskId)) throw new InvalidTaskIDException("Not valid Task ID!");
    }
     
    public String getTaskId() {
        return taskId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        if(endTime != null)
        {
        return endTime;
        }
        else throw new EmptyTimeFieldException();
    }

    public String getComment() {
        return comment;
    }

    public void setTaskId(String taskId) {
        if(isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId))
        {
        this.taskId = taskId;
        }
        else throw new InvalidTaskIDException("Not a valid task ID!");
        
    }

    public void setStartTime(LocalTime startTime) {
        if(startTime.getHour() !=0 || startTime.getMinute() != 0)
        {
        this.startTime = startTime;
        } else throw new EmptyTimeFieldException("Empty start time!");
    }

    public void setEndTime(LocalTime endTime) {

        if(endTime.getHour() !=0 || endTime.getMinute() != 0)
        {
        this.endTime = endTime;
        } else throw new EmptyTimeFieldException("Empty end time!");
        if(startTime.getHour()*60+startTime.getMinute() > endTime.getHour()*60+endTime.getMinute())
        {
            throw new NegativeMinutesOfWorkException();
        }
    }
    
    public void setStartTime(String startTime) {
        setStartTime(stringToLocalTime(startTime));
    }

    public void setEndTime(String endTime) {
        setEndTime(stringToLocalTime(endTime));
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isMultipleQuarterHour(long min)
    {
        return min%15==0;
    } 
}
