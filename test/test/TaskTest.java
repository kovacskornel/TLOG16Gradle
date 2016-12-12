package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import tlog16java.Task;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import tlog16java.Exceptions.EmptyTimeFieldException;
import tlog16java.Exceptions.NotExpectedTimeOrderException;

/**
 *
 * @author precognox
 */
public class TaskTest {
    
    private Task WrongTimeOrder()
    {
     return new Task("08:45", "07:30");
    }
    
    private Task NoEndTime()
    {
        return new Task("08:00", null);
    }
    
    private Task getMinTaskGood()
    {
        return new Task("07:30","08:45");
    }
    
    private Task validRedmineTask()
    {
        return new Task("1548");
    }
    
    @Test(expected = NotExpectedTimeOrderException.class)
    public void isWrongTimeOrderTest()
    {
         WrongTimeOrder().getMinPerTask();
    }
    
    @Test(expected = EmptyTimeFieldException.class)
    public void isEmptyTimeFieldTest()
    {
        NoEndTime().getEndTime();
    }
    
    @Test
    public void isGetMinPerTaskOK()
    {
        assertEquals(getMinTaskGood().getMinPerTask(),75);
    }
    
    @Test
    public void isValidRedmineTask()
    {
        assertEquals(validRedmineTask().isValidRedmineTaskId(validRedmineTask().getTaskId()),true);
    }
    
}