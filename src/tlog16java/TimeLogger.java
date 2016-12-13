package tlog16java;

import java.util.List;

import java.util.ArrayList;
import tlog16java.Exceptions.NotNewMonthException;
/**
 *
 * @author precognox
 */
public class TimeLogger{
    @lombok.Getter
    private final List<WorkMonth> months = new ArrayList<>();
    

    public boolean isNewMonth(WorkMonth wm) {
        boolean isnew = true;
        int i;
        for (i = 0; i < months.size(); i++) {
            if (months.get(i).date.equals(wm.date)) {
                isnew = false;
                break;
            }
        }
        return isnew;
    }
    
    
    
    public void addMonth(WorkMonth wm) {
        if (isNewMonth(wm)) {
            if (months.add(wm)) {
                System.out.println("Successfully added a WorkMonth");
            } else {   
                System.out.println("Not added!");
            }
        } else {
            throw new NotNewMonthException();
        }
    }
    
}