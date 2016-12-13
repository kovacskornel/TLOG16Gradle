package tlog16java;

import java.time.LocalTime;
import tlog16java.Exceptions.EmptyTimeFieldException;
import tlog16java.Exceptions.NegativeMinutesOfWorkException;
import tlog16java.Exceptions.NoTaskIDException;
import tlog16java.Exceptions.NotExpectedTimeOrderException;
import tlog16java.Exceptions.InvalidTaskIDException;
import tlog16java.Exceptions.NotMultipleQuarterHourException;

/**
 *
 * @author precognox
 */
@lombok.Getter
public  class Task{
    
    private String taskId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comment;
    
    public final boolean isValidRedmineTaskId(String ID)
    {
        return ID.matches("\\d{4}");
    }
    
    public final boolean isValidLTTaskId(String ID)
    {
        return ID.matches("LT-\\d{4}");
    }
    
    public boolean isValidTaskID(String ID)
    {
        return isValidRedmineTaskId(ID) || isValidLTTaskId(ID);
    }
 
    public final LocalTime stringToLocalTime(String a){
    String[] parts = a.split(":");
    LocalTime x = LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    return x;
    }

    
    
    public long getMinPerTask()
    {
        long x=0;
        if(endTime != null && endTime.getHour()*60+endTime.getMinute() > startTime.getHour()*60+startTime.getMinute())
        {
        x += (endTime.getHour()*60+endTime.getMinute())-(startTime.getHour()*60+startTime.getMinute());
        }
        else if(endTime == null) throw new EmptyTimeFieldException("Empty end time!");
        else if(endTime.getHour()*60+endTime.getMinute() > startTime.getHour()*60+startTime.getMinute()) throw new NotExpectedTimeOrderException();
        return x;
        
    }

    public Task(String taskId, LocalTime startTime, String comment) {
        if(isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId))
        {
            this.taskId = taskId;
        }else throw new InvalidTaskIDException("Invalid task ID!");
        setStartTime(startTime);
        this.comment = comment;
    }
    
     public Task(String taskId, String sstring, String comment) {
        if(isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId))
        {
            this.taskId = taskId;
        }else throw new InvalidTaskIDException("Invalid task ID!");
        this.comment = comment;
        setStartTime(sstring);
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
        if(stime.getHour()*60+stime.getMinute() < etime.getHour()*60+etime.getMinute())
        {
            if(isMultipleQuarterHour(stime.getMinute()) && isMultipleQuarterHour(etime.getMinute()))
            {
                setStartTime(startTime);
                setEndTime(endTime);
            }
        }
        else if(stime == null || etime == null) throw new EmptyTimeFieldException("Empty time field!");
        else if((stime.getHour()*60+stime.getMinute()) > (etime.getHour()*60+etime.getMinute())) throw new NotExpectedTimeOrderException();        
    }
     
    public Task(LocalTime startTime, LocalTime endTime) {
        if(startTime != null && endTime != null && startTime.getHour()*60+startTime.getMinute() < endTime.getHour()*60+endTime.getMinute())
        {
                setStartTime(startTime);
                setEndTime(endTime);
        }
        else if(startTime == null || endTime == null) throw new EmptyTimeFieldException("Empty time field!");
        else if(startTime.getHour()*60+startTime.getMinute() > endTime.getHour()*60+endTime.getMinute()) throw new NotExpectedTimeOrderException();
    }
     
    public Task(String taskId, LocalTime startTime, LocalTime endTime, String comment) {
        this.taskId = taskId;
        setStartTime(startTime);
        setEndTime(endTime);
        this.comment = comment;
    }
    
    public Task(String taskId, String startTime, String endTime, String comment) {
        this.taskId = taskId;
        setStartTime(stringToLocalTime(startTime));
        setEndTime(stringToLocalTime(endTime));
        this.comment = comment;
    }

    public Task(String taskId) {
        if(taskId == null || "".equals(taskId) || taskId.isEmpty()) throw new NoTaskIDException();
        else if(isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId) && !"".equals(taskId))
        {
            this.taskId = taskId;
        }else if(!isValidRedmineTaskId(taskId) || !isValidRedmineTaskId(taskId)) throw new InvalidTaskIDException();
    }

    public void setTaskId(String taskId) {
        if(isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId))
        {
        this.taskId = taskId;
        }
        else throw new InvalidTaskIDException("Not a valid task ID!");
        
    }

    public final void setStartTime(LocalTime startTime) {
        if(startTime.getHour() !=0 || startTime.getMinute() != 0 && isMultipleQuarterHour(startTime.getMinute()))
        {
            this.startTime = startTime;
        } else throw new EmptyTimeFieldException("Empty start time!");
    }

    public final void setEndTime(LocalTime endTime) {

        if(endTime.getHour() !=0 || endTime.getMinute() != 0 && isMultipleQuarterHour(endTime.getMinute()))
        {
        this.endTime = endTime;
        } else throw new EmptyTimeFieldException("Empty end time!");
        if(startTime.getHour()*60+startTime.getMinute() > endTime.getHour()*60+endTime.getMinute())
        {
            throw new NegativeMinutesOfWorkException();
        }
    }
    
    public final void setStartTime(String startTime) {
        setStartTime(stringToLocalTime(startTime));
    }

    public final void setEndTime(String endTime) {
        setEndTime(stringToLocalTime(endTime));
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public final boolean isMultipleQuarterHour(long min)
    {
        if(min%15!=0) throw new NotMultipleQuarterHourException();
        else return true;
    } 
}
