/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.time.LocalDate;
import tlog16java.WorkMonth;
import tlog16java.WorkDay;
import tlog16java.Task;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import tlog16java.Exceptions.WeekendNotEnabledException;
/**
 *
 * @author precognox
 */
public class WorkMonthTest {
    
    // Test 1
    @Test
    public void getSumTest1()
    {
        Task t = new Task("7:30","8:45");
        WorkDay wd = new WorkDay(LocalDate.of(2016, 9, 5),420);
        wd.addTask(t);
        Task t2 = new Task("8:45","9:45");
        WorkDay wd2 = new WorkDay(LocalDate.of(2016, 9, 1),420);
        wd2.addTask(t2);
        WorkMonth wm = new WorkMonth();
        wm.addWorkDay(wd);
        wm.addWorkDay(wd2);
        assertEquals(wm.getSumPerMonth(),135);      
    }
    
    // Test 2
    @Test
    public void getSumTest2()
    {
        WorkMonth wm = new WorkMonth();
        assertEquals(wm.getSumPerMonth(),0);
    }
    
    // Test 3
    @Test
    public void getExtraTest()
    {
        Task t = new Task("7:30","8:45");
        WorkDay wd = new WorkDay(LocalDate.of(2016, 9, 5),420);
        wd.addTask(t);
        Task t2 = new Task("8:45","9:45");
        WorkDay wd2 = new WorkDay(LocalDate.of(2016, 9, 1),420);
        wd2.addTask(t2);
        WorkMonth wm = new WorkMonth();
        wm.addWorkDay(wd);
        wm.addWorkDay(wd2);
        assertEquals(wm.getExtraMinPerMonth(),-705);         
    }
    
    // Test 4
    @Test
    public void getExtraTest2()
    {
        WorkMonth wm = new WorkMonth();
        assertEquals(wm.getExtraMinPerMonth(),0);
    }
    
    // Test 5
    @Test
    public void getReqTest()
    {
        WorkDay wd = new WorkDay(LocalDate.of(2016, 9, 5),420);
        WorkDay wd2 = new WorkDay(LocalDate.of(2016, 9, 1),420);
        WorkMonth wm = new WorkMonth();
        wm.addWorkDay(wd);
        wm.addWorkDay(wd2);
        assertEquals(wm.getRequiredMinPerMonth(),840);  
    }
    
    // Test 6
    @Test
    public void getReqTest2()
    {
        assertEquals(new WorkMonth().getRequiredMinPerMonth(),0);
    }
    
    // Test 7
    @Test
    public void sumTest()
    {
        Task t = new Task("7:30","8:45");
        WorkDay wd = new WorkDay(LocalDate.of(2016,9,9));
        wd.addTask(t);
        WorkMonth wm = new WorkMonth();
        wm.addWorkDay(wd);
        assertEquals(wd.getSumPerDay(),wm.getSumPerMonth());
    }
    
    // Test 8
    @Test
    public void weekendTest()
    {
        Task t = new Task("7:30","8:45");
        WorkDay wd = new WorkDay(LocalDate.of(2016,8,28));
        wd.addTask(t);
        WorkMonth wm = new WorkMonth();
        wm.setIsWeekendEnabled(true);
        wm.addWorkDay(wd);
        assertEquals(wd.getSumPerDay(),wm.getSumPerMonth() );
    }
    
    // Test 9
    @Test(expected = WeekendNotEnabledException.class)
    public void weekendTest2()
    {
        Task t = new Task("7:30","8:45");
        WorkDay wd = new WorkDay(LocalDate.of(2016,8,28));
        wd.addTask(t);
        WorkMonth wm = new WorkMonth();
        wm.addWorkDay(wd);
    }
    
    // Test 10
    @Test
    public void isSameTest()
    {
        WorkDay wd = new WorkDay(LocalDate.of(2016, 9, 1));
        WorkDay wd2 = new WorkDay(LocalDate.of(2016, 9, 2));
        WorkMonth wm = new WorkMonth();
        wm.addWorkDay(wd);
        assertEquals(wm.isSameMonth(wd2), true);
    }
}
