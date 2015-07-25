package la3gui;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author #Tamwyn Eopia
 */
public class Periodical extends LibraryItems{

    private String volumeNumber;
    private String issueNumber;
    private String subject;
    private boolean itemIsCheckedOut;
    private GregorianCalendar dateCheckedOut;
    private GregorianCalendar dateDue;
    
    public Periodical(String number, String titleIn, String volumeIn, String issueIn, String subjectIn)
    {
        callNumber = number;
        title = titleIn;
        volumeNumber = volumeIn;
        issueNumber = issueIn;
        subject = subjectIn;
    }
    
    @Override
    public String printSpecs()
    {
        String specs = "";
        specs = ("--------------------------------\nPeriodical Title: " + title + "\nVolume: " + volumeNumber + "\nIssue: " + issueNumber + "\nSubject: " + subject + "\nCall Number: " + callNumber + "\nChecked Out: " + itemIsCheckedOut + "\n");
        if(itemIsCheckedOut)
        {
            specs = specs + (String.format("Date Checked Out: %tD\n", dateCheckedOut)) + (String.format("Date Due: %tD\n", dateDue));
            
        }
        specs = specs + "\n--------------------------------";
        return specs;
    }
    
    @Override
    public String checkOut()
    {
        dateCheckedOut = new GregorianCalendar();
        dateDue = (GregorianCalendar)dateCheckedOut.clone();
        dateDue.add(Calendar.DAY_OF_YEAR, 7);
        itemIsCheckedOut = true;
        return printSpecs();
    }    
}