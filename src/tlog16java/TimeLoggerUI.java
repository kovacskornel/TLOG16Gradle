package tlog16java;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;


/**
 * @author precognox
 */

public class TimeLoggerUI {

public void listMonths()
{
    TimeLogger TimeLogger = new TimeLogger();
    WorkMonth WM;
            if(TimeLogger.getMonths().isEmpty())
            {
                System.out.println("No months available");
            }
            else
            {
                for(int i=0;i<TimeLogger.getMonths().size();i++)
                {
                    System.out.print(i + " " + TimeLogger.months.get(i));
                }   
            }
}
    
public void createDay(int month, int day, long workh)
{
    WorkMonth workM = new WorkMonth(YearMonth.now().getYear(),month);
    WorkDay wd = new WorkDay();
    TimeLogger tl = new TimeLogger();
    wd.requiredMinPerDay = workh;
    wd.setActualDay(LocalDate.of(YearMonth.now().getYear(), month, day));
    workM.addWorkDay(wd, false);
}

public void listDays(int Month)
{
        WorkMonth WorkMonth = new WorkMonth(YearMonth.now().getYear(),Month);
        TimeLogger tl = new TimeLogger();
            if(WorkMonth.getDays() == null)
            {
                System.out.println("No days available");
            }
            else
            {
                for(int i=0;i<WorkMonth.getDays().size();i++)
                {                        
                            System.out.println(WorkMonth.getDays().get(i));
                }   
            }
}

public void addMonth(int y, int m, TimeLogger tl)
{
        WorkMonth WM = new WorkMonth(y,m);
        if (WM.date != null)
        {
            if(y != 0 && m != 0)
            {
                WM.date.year = y;
                WM.date.month = m;
                tl.addMonth(WM, tl);
            }
        }
}

public void listTask(int month, int day)
{/*
            WorkDay WorkDay = new WorkDay();
            if(WorkDay.tasks<Task> == null)
            {
                System.out.println("No tasks available");
            }
            else
            {
                for(int i=0;i<WorkDay.tasks.size();i++)
                {
                if(WorkDay.actualDay.getDayOfMonth() == day && WorkDay.actualDay.getMonthValue() == month) System.out.println(WorkDay.tasks.get(i));
                }   
           }   */
}

public void menuSelect(tlog16java.TimeLogger tl)
{
    System.out.println("Please select an option");
    Scanner user_input = new Scanner(System.in);
    int selection;
    selection = user_input.nextInt();
    switch(selection)
    {
        case 0:
        {
            break;
        }
        case 1:
        {
            listMonths();
            break;
        }
        case 2:
        {
            System.out.println("Please select a month (row number) ");
            int selected = user_input.nextInt();
            listDays(selected);
            break;
        }
        case 3:
        {
            System.out.println("Please give me the month and the day");
            int month = user_input.nextInt();
            int day = user_input.nextInt();
            listTask(month, day);           
            break;
        }
        case 4:
        {
            System.out.println("Please tell me the year and the month(with numbers) you want to add:");
            int year ,month;
            year = user_input.nextInt();
            month = user_input.nextInt();
            addMonth(year, month, tl);
            break;
        }
        case 5:
        {
            listMonths();
            System.out.println("Please give me the month then the day");
            int month = user_input.nextInt();
            int day = user_input.nextInt();
            System.out.println("Please tell me the required working hours in minutes");
            long workh = user_input.nextLong();
            createDay(month, day, workh);
            break;
        }
        case 6:
        {
            int m,d;
            String taskid, comment;
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
             System.out.println("Please tell me the day");           
            d = user_input.nextInt();
            System.out.println("Please give me the Task ID");
            taskid = user_input.next();
            System.out.println("Please tell me what you do");
            comment = user_input.next();
            System.out.println("Please tell me when you start (HH:MM)");
            break;
        }
        case 7:
        {
            
            break;
        }
        case 8:
        {
            break;
        }
        case 9:
        {
            break;
        }
        case 10:
        {
            break;
        }
        
    }
}    
    
    public void menu(tlog16java.TimeLogger tl)
{
    System.out.println("0. Exit");
    System.out.println("1. List months");
    System.out.println("2. List days");
    System.out.println("3. List task for a specific day");
    System.out.println("4. Add new month");
    System.out.println("5. Add day to a specific month");
    System.out.println("6. Start a task for a day");
    System.out.println("7. Finish a specific task");
    System.out.println("8. Delete a task");
    System.out.println("9. Modify task");
    System.out.println("10. Statistics");
    menuSelect(tl);
}
}
