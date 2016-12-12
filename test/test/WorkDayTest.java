/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.time.LocalDate;
import java.time.Month;
import tlog16java.WorkDay;
import tlog16java.Task;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import tlog16java.Exceptions.NegativeMinutesOfWorkException;
import tlog16java.Exceptions.FutureWorkException;

/**
 *
 * @author precognox
 */
public class WorkDayTest {
    
    private Task TaskWith75Mins()
    {
        return new Task("07:30","08:45");
    }
    
    private Task NextTask()
    {
        return new Task("8:45","9:45");
    }

    private Task notQuarterHour()
    {
        return new Task("07:35","08:45");
    }
    
    private Task MyTask()
    {
        return new Task("08:00","09:00");
    }
    
    private WorkDay MyDay()
    {
        WorkDay a = new WorkDay(LocalDate.now());
        a.addTask(MyTask());
        return a;
    }
    
    private WorkDay MyDayTwo()
    {
        WorkDay a = new WorkDay(LocalDate.now());
        a.addTask(notQuarterHour());
        return a;       
    }
    
    private WorkDay twoTasks()
    {
        WorkDay a = new WorkDay(LocalDate.now());
        a.addTask(TaskWith75Mins());
        a.addTask(NextTask());
        return a;
    }
    
    private WorkDay req75()
    {
        WorkDay a = new WorkDay(LocalDate.now(),75);
        a.addTask(TaskWith75Mins());
        return a;
    }
    
    private WorkDay reqDef()
    {
        WorkDay a = new WorkDay(LocalDate.now());
        a.addTask(TaskWith75Mins());
        return a;
    }
    
    private WorkDay req60()
    {
        WorkDay a = new WorkDay(LocalDate.now(),60);
        a.addTask(TaskWith75Mins());
        return a;
    }
    
    private WorkDay emptyWorkDay()
    {
        return new WorkDay(LocalDate.now());
    }
    
    private WorkDay NegativeWorkDay()
    {
        return new WorkDay(LocalDate.now(), -5);
    }
    
    private WorkDay FutureDay()
    {
        return new WorkDay(LocalDate.of(3016, 12, 12));
    }
    
    // Test 1    
    @Test
    public void getExtraTest()
    {
         assertEquals(req75().getExtraMinPerDay(),0);
    }
    
    // Test 2
    @Test
    public void getExtraTest2()
    {
        assertEquals(reqDef().getExtraMinPerDay(),-375);
    }
    
    // Test 3
    @Test
    public void getExtraTest3()
    {
        assertEquals(req60().getExtraMinPerDay(),15);
    }
    
    // Test 4
    @Test
    public void getExtraTest4()
    {
        assertEquals(emptyWorkDay().getExtraMinPerDay(),-emptyWorkDay().getRequiredMinPerDay());
    }
    
    // Test 5
    @Test(expected = NegativeMinutesOfWorkException.class)
    public void NegativeTest()
    {
        NegativeWorkDay().getRequiredMinPerDay();
    }
    
    // Test 6
    @Test(expected = NegativeMinutesOfWorkException.class)
    public void NegativeTest2()
    {
        NegativeWorkDay();
    }    
    
    // Test 7
    @Test(expected = FutureWorkException.class)
    public void FutureWork()
    {
        FutureDay().setActualDay(LocalDate.of(5000, Month.MARCH, 3));
    }  
    
    // Test 8
    @Test(expected = FutureWorkException.class)
    public void FutureWork2()
    {
        FutureDay();
    }
    
    // Test 9
    @Test
    public void sumOfTwo()
    {
        assertEquals(twoTasks().getSumPerDay(),135);
    }
    
    // Test 10
    @Test
    public void sumOfZero()
    {
        assertEquals(emptyWorkDay().getSumPerDay(),0);
    }
    
    // Test 11
    @Test
    public void equalTest()
    {
        assertEquals(MyTask().getMinPerTask(),MyDay().getSumPerDay());
    }
    
    // Test 12
    //@Test
    
}