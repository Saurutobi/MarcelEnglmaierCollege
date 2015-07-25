package la3gui;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author #Tamwyn Eopia
 */
public class LibraryItems {

    protected String callNumber;
    protected String title;
    
    public LibraryItems()
    {
        
    }
    
    public String printSpecs()
    {
        return "";
    }
    
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
    
    public String checkOut()
    {
        return "";
    }
}