package tlog16java;

import java.util.List;

import java.util.ArrayList;
/**
 *
 * @author precognox
 */
public class TimeLogger{
    public List<WorkMonth> months = new ArrayList<>();
    
    public List<WorkMonth> getMonths()
    {
        return months;
    }
    

    public boolean isNewMonth(WorkMonth wm, TimeLogger tl) {
        boolean isnew = true;
        int i;
        for (i = 0; i < tl.getMonths().size(); i++) {
            if (tl.getMonths().get(i).date.equals(wm.date)) {
                isnew = false;
                break;
            }
        }
        return isnew;
    }
    
    public void addMonth(WorkMonth wm, TimeLogger tl) {
        if (isNewMonth(wm, tl)) {
            if (getMonths().add(wm)) {
                System.out.println("Successfully added a WorkMonth");
            } else {   
                System.out.println("Not added!");
            }
        } else {
            System.out.println("This month is already existing!");
        }
    }
    
}
