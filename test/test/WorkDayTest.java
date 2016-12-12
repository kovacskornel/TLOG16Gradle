/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import tlog16java.WorkDay;
import tlog16java.Task;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author precognox
 */
public class WorkDayTest {
    
    private Task TaskWith75Mins()
    {
        return new Task("07:30","08:45");
    }
    
    private WorkDay req75()
    {
        WorkDay a = new WorkDay(null,75);
        a.addTask(TaskWith75Mins());
        return a;
    }
    
    private WorkDay reqDef()
    {
        WorkDay a = new WorkDay(null);
        a.addTask(TaskWith75Mins());
        return a;
    }
    
    private WorkDay req60()
    {
        WorkDay a = new WorkDay(null,60);
        a.addTask(TaskWith75Mins());
        return a;
    }
    
    private WorkDay emptyWorkDay()
    {
        return new WorkDay(null);
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

}