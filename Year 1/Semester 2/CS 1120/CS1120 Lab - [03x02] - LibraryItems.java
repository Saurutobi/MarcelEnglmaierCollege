package la3_englmaier;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Marcel Englmaier
 */
public class LibraryItems
{
    protected String callNumber;
    protected String title;
    
    public boolean callNumberExists(String number)
    {
        if(number.equalsIgnoreCase(callNumber))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}