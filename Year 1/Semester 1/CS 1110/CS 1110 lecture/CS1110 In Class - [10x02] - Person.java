package inclass10;

import java.util.*;

/**
 *
 * @author Marcel Englmaier
 */
public class Person
{
    private String _Name;
    private Date _birthDate;

    Person(String Name, Date birthDate)
	{
        _Name = Name;
        _birthDate = (Date) birthDate.clone();
    }

    Person(String Name, int Year, int Month, int Date)
	{
        _Name = Name;
        if(Year > 1900)
		{
            Year = Year - 1900;
        }
        if(Month > 11)
		{
            Month = 11;
        }
        if(Date < 1)
		{
            Date = 1;
        }
        if(Date > 32)
		{
            Date = 31;
        }
        _birthDate = new Date(Year, Month, Date);
    }

    @Override
    public String toString()
	{
        String retval = "";
        retval = "Name: " + _Name + "\n";
        retval = "BirthDate: " + _birthDate;
        return retval;
    }
}
