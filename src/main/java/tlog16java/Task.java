package tlog16java;

import java.time.LocalTime;
import tlog16java.Exceptions.EmptyTimeFieldException;
import tlog16java.Exceptions.NegativeMinutesOfWorkException;
import tlog16java.Exceptions.NoTaskIDException;
import tlog16java.Exceptions.NotExpectedTimeOrderException;
import tlog16java.Exceptions.InvalidTaskIDException;
import tlog16java.Exceptions.NotMultipleQuarterHourException;
/**
 * <h1>Task Class</h1>
 * This is the task class<br>
 * It contains the required informations of a task
 * @author Kovács Kornél
 * @version 0.1.0
 * @since 2016-11-03
 */
@lombok.Getter
public  class Task{
    
    private String taskId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comment;

    /**
     * <h3>isValidRedmineTaskId</h3>
     * Check if a Task's Id only contains 4 numbers
     * 
     * @param ID Gets the ID from user input
     * @return boolean<br>true if it is a valid redmine ID<br>false if it is not a valid redmine
     */
    public final boolean isValidRedmineTaskId(String ID)
    {
        return ID.matches("\\d{4}");
    }
    /**
     * <h3>isValidLTTaskId</h3>
     * Check if a Task's Id only contains "LT-" and 4 numbers
     * 
     * @param ID Gets the ID from user input
     * @return boolean<br>true if it is a valid LT task ID<br>false if it is not a valid LT task
     */    
    public final boolean isValidLTTaskId(String ID)
    {
        return ID.matches("LT-\\d{4}");
    }
    
    /**
     * <h3>isValidTaskID</h3>
     * check if the ID got from user input is an LT or redmine task
     * @param ID Gets the ID from user input
     * @return boolean<br>true if it is a valid redmine or LT task ID<br>false if it is not a valid redmine or LT task
     */
    public boolean isValidTaskID(String ID)
    {
        return isValidRedmineTaskId(ID) || isValidLTTaskId(ID);
    }
 
    /**
     *<h3>stringToLocalTime</h3>
     * Converts a string to LocalTime<br>Format: (HH:MM)
     * @param a Gets a string from user input
     * @return LocalTime<br>Hour from HH<br>Minutes from MM
     */
    public final LocalTime stringToLocalTime(String a){
    String[] parts = a.split(":");
    LocalTime x = LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    return x;
    }

    /**
     * <h3>GetMinPerTask</h3>
     * Gets working minutes of the task
     * @return long - the working minutes of the task
     * @throws NotExpectedTimeOrderException if a tasks ends before it starts
     */
    
    
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

    /**
     * Sets the Task's ID
     * @param taskId the Task's ID from user input
     * @throws InvalidTaskIDException if the Task's ID is invalid
     */
    public void setTaskId(String taskId) {
        if(isValidLTTaskId(taskId) || isValidRedmineTaskId(taskId))
        {
        this.taskId = taskId;
        }
        else throw new InvalidTaskIDException("Not a valid task ID!");
        
    }

    /**
     * Sets the starting time of a task
     * @param startTime Starting time from user input
     * @exception EmptyTimeFieldException if the user leaves the time field empty
     */
    public final void setStartTime(LocalTime startTime) {
        if(startTime.getHour() !=0 || startTime.getMinute() != 0 && isMultipleQuarterHour(startTime.getMinute()))
        {
            this.startTime = startTime;
        } else throw new EmptyTimeFieldException("Empty start time!");
    }
    /**
     * Sets the finishing time of a task
     * @param endTime Finishing time from user input
     * @exception EmptyTimeFieldException if the user leaves the time field empty
     */
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
    
    /**
     * String input version of {@link setEndTime(LocalTime endTime)}
     * @param startTime
     */
    public final void setStartTime(String startTime) {
        setStartTime(stringToLocalTime(startTime));
    }
    /**
     * String input version of {@link setStartTime(LocalTime endTime)}
     * @param endTime
     */
    public final void setEndTime(String endTime) {
        setEndTime(stringToLocalTime(endTime));
    }

    /**
     *Sets the comment of a task
     * @param comment string from user input
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @param min Minutes of a time
     * @return boolean<br>true if it is the multiple of quarter hour
     * <br>false if it is not the multiple of quarter hour
     */
    public final boolean isMultipleQuarterHour(long min)
    {
        if(min%15!=0) throw new NotMultipleQuarterHourException();
        else return true;
    } 
}
