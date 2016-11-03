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
