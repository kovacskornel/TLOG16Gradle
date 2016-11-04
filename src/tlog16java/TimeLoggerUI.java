package tlog16java;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Scanner;


/**
 * @author precognox
 */

public class TimeLoggerUI {

public void listMonths(TimeLogger tl)
{
    WorkMonth WM = new WorkMonth();
            if(tl.getMonths().isEmpty())
            {
                System.out.println("No months available");
            }
            else
            {
                for(int i=0;i<tl.getMonths().size();i++)
                {
                    System.out.println((i+1) + "\t" + tl.months.get(i).date);
                }   
            }
}
    
public void createDay(int month, int day, long workh, TimeLogger tl)
{
    WorkMonth WM = tl.getMonths().get(month-1);
    WorkDay wd = new WorkDay();
    wd.requiredMinPerDay = workh;
    wd.setActualDay(LocalDate.of(WM.date.getYear(), WM.date.getMonth(), day));
    WM.addWorkDay(wd, true);
}

public void listDays(int Month, TimeLogger tl)
{
        WorkMonth WM = tl.getMonths().get(Month-1);
            if(WM.getDays() == null)
            {
                System.out.println("No days available");
            }
            else
            {
                System.out.println("Workdays of the selected month:");
                for(int i=0;i<WM.days.size();i++)
                {                        
                            System.out.println(WM.getDays().get(i).getActualDay().getDayOfMonth());
                }   
            }
}

public void addMonth(int y, int m,TimeLogger tl)
{
                WorkMonth WM = new WorkMonth();
                WM.date = YearMonth.of(y, m);
                tl.addMonth(WM, tl);
}

public void listTask(int month, int day)
{
            WorkDay WorkDay = new WorkDay();
            if(WorkDay.tasks.isEmpty())
            {
                System.out.println("No tasks available");
            }
            else
            {
                for(int i=0;i<WorkDay.tasks.size();i++)
                {
                if(WorkDay.actualDay.getDayOfMonth() == day && WorkDay.actualDay.getMonthValue() == month) System.out.println(WorkDay.tasks.get(i));
                }   
           }   
}

public void finishTask(int m, int d, String end)
{
    
}

public void startTask(int m, int d, String taskid, String comment)
{
    
}

public void delTask(int m, int d, String tid)
{
    
}

public void menuSelect(TimeLogger tl)
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
            listMonths(tl);
            menu(tl);
            break;
        }
        case 2:
        {
            listMonths(tl);
            System.out.println("Please select a month (row number) ");
            int selected = user_input.nextInt();
            listDays(selected, tl);
            menu(tl);
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
            menu(tl);
            break;
        }
        case 5:
        {
            listMonths(tl);
            if(!tl.getMonths().isEmpty())
            {
            System.out.println("Please give me the month's row number");
            int month = user_input.nextInt();
            System.out.println("Please give me the day you want to add");
            int day = user_input.nextInt();
            System.out.println("Please tell me the required working hours in minutes");
            long workh = user_input.nextLong();
            if(workh == 0) workh = 450;
            createDay(month, day, workh, tl);
            }
            menu(tl);
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
            startTask(m,d,taskid,comment);
            break;
        }
        case 7:
        {
            int m,d;
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
             System.out.println("Please tell me the day");           
            d = user_input.nextInt();
            listTask(m,d);
            System.out.println("Please tell me the end time (HH:MM)");
            String end = user_input.next();
            finishTask(m,d,end);
            break;
        }
        case 8:
        {
            int m,d;
            String conf;
            boolean cont = false;
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
             System.out.println("Please tell me the day");           
            d = user_input.nextInt();
            listTask(m,d);
            System.out.println("Please select a task by task ID");
            String tid = user_input.next();
            do{
            System.out.println("Are you sure you want to delete \"" + tid + "\" task? (yes/no)");
            conf = user_input.next();
                switch (conf) {
                    case "yes":
                        delTask(m,d,tid);
                        cont = true;
                        break;
                    case "no":
                        menu(tl);
                        cont=true;
                        break;
                    default:
                        System.out.println("Please choose yes or no");
                        break;
                }
            }while(cont != true);
            break;
        }
        case 9:
        {
            Task task;
            int m,d,sh,sm,eh,em;
            String conf, nc;
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
             System.out.println("Please tell me the day");           
            d = user_input.nextInt();
            listTask(m,d);
            System.out.println("Please select a task by task ID");
            String tid = user_input.next();
            task = new Task();
            System.out.println("Please set the taskID("+tid+")");
            String ntid = user_input.next();
            if(ntid == null ? tid != null : !ntid.equals(tid)) task.taskId= ntid;
            System.out.println("Please set the Start Time ("+task.startTime.startHour + ":"+ task.startTime.startMin+")( enter hour than minutes )");
            sh = user_input.nextInt();
            if(sh != task.startTime.startHour) task.startTime.startHour = sh;
            sm = user_input.nextInt();
            if(sm != task.startTime.startMin) task.startTime.startMin = sm;
            System.out.println("Please set the End Time ("+task.endTime.endHour+ ":" + task.endTime.endMin +")( enter hour than minutes )");
            eh = user_input.nextInt();
            if(sh != task.endTime.endHour) task.endTime.endHour = eh;
            em = user_input.nextInt();
            if(sm != task.endTime.endMin) task.endTime.endMin = em;
            System.out.println("Please set the comment("+task.comment+")");
            nc = user_input.next();
            if (nc.equals(task.comment)) task.comment = nc;
            break;
        }
        case 10:
        {
            int m;
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
            break;
        }
        
    }
}    
    
    public void menu(TimeLogger tl)
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
