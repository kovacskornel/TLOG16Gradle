package tlog16java;
import java.util.Scanner;
/**
 *
 * @author precognox
 */
public class TimeLoggerUI {
    
public void menu()
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
    menuSelect();
}

public void menuSelect()
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
            menu();
            break;
        }
        case 2:
        {
            menu();break;
        }
        case 3:
        {
            menu();break;
        }
        case 4:
        {
            menu();break;
        }
        case 5:
        {
            menu();break;
        }
        case 6:
        {
            menu();break;
        }
        case 7:
        {
            menu();break;
        }
        case 8:
        {
            menu();break;
        }
        case 9:
        {
            menu();break;
        }
        case 10:
        {
            menu();break;
        }
        
    }
}

}