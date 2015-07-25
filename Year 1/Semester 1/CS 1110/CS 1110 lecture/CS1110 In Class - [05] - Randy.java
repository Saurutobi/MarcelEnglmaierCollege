package randy;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Randy
{

    public static void main(String[] args)
	{
        char c;
        int i=1;                
        String m = "out of range";
        System.out.println("Month:");
        Scanner keyboard = new Scanner(System.in);
        i = keyboard.nextInt();
        switch(i)
        {
            case 1: 
				m = "Jan";
				break;
            case 2:
				m = "Feb";
				break;
            case 3:
				m = "March";
				break;
            case 4:
				m = "April";
				break;
            case 5:
				m = "May";
				break;
            case 6:
				m = "June";
				break;
            case 7:
				m = "July";
				break;
            case 8:
				m = "Aug";
				break;
            case 9:
				m = "Sept";
				break;
            case 10:
				m = "Oct";
				break;
            case 11:
				m = "Nov";
				break;
            case 12:
				m= "Dec";
				break;
        }
        System.out.println("the month is: " + m );
    }
}
