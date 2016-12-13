package tlog16java;

import java.util.List;

import java.util.ArrayList;
import tlog16java.Exceptions.NotNewMonthException;
/**
 *
 * @author precognox
 */
public class TimeLogger{
    private final List<WorkMonth> months = new ArrayList<>();
    
    public List<WorkMonth> getMonths()
    {
        return months;
    }
    

    public boolean isNewMonth(WorkMonth wm) {
        boolean isnew = true;
        int i;
        for (i = 0; i < getMonths().size(); i++) {
            if (getMonths().get(i).date.equals(wm.date)) {
                isnew = false;
                break;
            }
        }
        return isnew;
    }
    
    public void addMonth(WorkMonth wm) {
        if (isNewMonth(wm)) {
            if (getMonths().add(wm)) {
                System.out.println("Successfully added a WorkMonth");
            } else {   
                System.out.println("Not added!");
            }
        } else {
            throw new NotNewMonthException();
        }
    }
    
}