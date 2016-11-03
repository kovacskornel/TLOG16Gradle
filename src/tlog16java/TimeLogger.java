/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.util.List;

/**
 *
 * @author precognox
 */
public class TimeLogger{
    List months;
    
    public List getMonths()
    {
        return months;
    }
    
    public boolean isNewMonth(WorkMonth wm)
    {
        return months.get(wm.date.month) == null;
    }
    
    void addMonth(WorkMonth wm)
    {
        if (isNewMonth(wm))
        {
            months.add(wm.date.month);
        }
    }
    
}
